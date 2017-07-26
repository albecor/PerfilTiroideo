package com.spring.medical.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.medical.converter.RoleConverter;
import com.spring.medical.model.Exam;
import com.spring.medical.model.PasswordResetToken;
import com.spring.medical.model.Role;
import com.spring.medical.model.User;
import com.spring.medical.service.EmailService;
import com.spring.medical.service.ExamService;
import com.spring.medical.service.RoleService;
import com.spring.medical.service.UserService;

@Controller
@SessionAttributes({ "loggedinuser", "roleList" })
public class MainController {

	@Autowired
	EmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	ExamService examService;

	@Autowired
	RoleConverter roleConverter;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	String IpServer;

	/**
	 * 
	 * @param model
	 * @return index.jsp
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String init(ModelMap model) {

		if (roleService.countAdministrators() == 0) {
			Authentication auth = new UsernamePasswordAuthenticationToken(null, null,
					Arrays.asList(new SimpleGrantedAuthority("ROLE_TEMPORAL")));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		}

		for (GrantedAuthority grantedAuthority : getAuthorities()) {
			if (grantedAuthority.getAuthority().equals("CHANGE_PASSWORD_PRIVILEGE")) {
				return "redirect:/logout";
			}
		}

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("roleList", roleService.selectAll());

		for (GrantedAuthority grantedAuthority : getAuthorities()) {
			if (grantedAuthority.getAuthority().equals("ROLE_TEMPORAL")) {
				return "redirect:/te/new-admin";
			}
		}
		model.addAttribute("user", new User());

		return "index";
	}

	/**
	 * 
	 * @param model
	 * @return resetPassword.jsp
	 */
	@RequestMapping(value = { "/re/resetPassword" }, method = RequestMethod.GET)
	public String resetPassword(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "resetPassword";
	}

	/**
	 * post method to send email a new token to reset password
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return home (/)
	 */
	@RequestMapping(value = { "/re/resetPassword" }, method = RequestMethod.POST)
	public String resetPassword(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "resetPassword";
		}

		User entity = userService.selectByNdivalue(user.getNdivalue());
		if (entity.getNdivalue() == null) {

			FieldError ssoError = new FieldError("user", "ndivalue",
					messageSource.getMessage("error.notExistUser", new String[] { user.getNdivalue() }, locale));
			result.addError(ssoError);
			return "resetPassword";
		}

		userService.deletePasswordResetTokenByUser(entity.getId());
		userService.deleteExpiredPasswordResetToken();
		emailService.sendResetPasswordToken(getAppUrl(request), locale, entity);

		redirectAttributes.addFlashAttribute("notifyMesage", "reset-password-notify");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		return "redirect:/";
	}

	/**
	 * show page to redirect user with credentials for change password
	 * 
	 * @param locale
	 * @param model
	 * @param id
	 * @param token
	 * @param redirectAttributes
	 * @return login.jsp
	 */
	@RequestMapping(value = "/re/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage(Locale locale, Model model, @RequestParam("id") Integer id,
			@RequestParam("token") String token, RedirectAttributes redirectAttributes) {

		String result = userService.validatePasswordResetToken(id, token);

		if (result != null) {
			redirectAttributes.addFlashAttribute("notifyMesage", "expired-resetPasswordToken");
			redirectAttributes.addFlashAttribute("notifyType", "warning");
			return "redirect:/login?lang=" + locale.getLanguage();
		}
		return "redirect:/ch/savePassword?lang=" + locale.getLanguage();
	}

	/**
	 * show form page to save the new password
	 * 
	 * @param model
	 * @return updatePassword.jsp
	 */

	@RequestMapping(value = { "/ch/savePassword" }, method = RequestMethod.GET)
	public String updatePassword(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "forms/updatePassword";
	}

	/**
	 * post method to save the new password
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return login.jsp
	 */
	@RequestMapping(value = { "/ch/savePassword" }, method = RequestMethod.POST)
	public String updatePassword(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "forms/updatePassword";
		}

		User principal = getPrincipal();
		User entity = userService.selectByNdivalue(user.getNdivalue());
		if (!entity.getNdivalue().equals(principal.getNdivalue()) || entity.getNdivalue() == null) {

			FieldError ssoError = new FieldError("user", "ndivalue",
					messageSource.getMessage("error.document", new String[] {}, locale));
			result.addError(ssoError);
			return "forms/updatePassword";
		}
		user.setId(entity.getId());
		userService.updatePassword(user);

		redirectAttributes.addFlashAttribute("notifyMesage", "update-password-notify");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		nullAuthentication(request, response);
		return "redirect:/login?logout";
	}

	/**
	 * To validate if the token has not expired or is invalid
	 * 
	 * @param id
	 * @param token
	 * @return String to validate token
	 */
	public String validatePasswordResetToken(long id, String token) {
		PasswordResetToken passToken = userService.selectByToken(token);
		if ((passToken == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		Calendar cal = Calendar.getInstance();
		if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "expired";
		}

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

	/**
	 * to get the url where the server is located
	 * 
	 * @param request
	 * @return url server
	 */
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + IpServer + ":" + request.getServerPort() + request.getContextPath();
	}

	/**
	 * show page to update user yourself
	 * 
	 * @param model
	 * @return forms/user.jsp
	 */
	@RequestMapping(value = { "/updateYourself" }, method = RequestMethod.GET)
	public String updateYourself(ModelMap model) {
		User user = getPrincipal();
		user.setPassword("");
		model.addAttribute("user", user);
		return "forms/user";
	}

	/**
	 * post method to update user yourself
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return login.jsp
	 */
	@RequestMapping(value = { "/updateYourself" }, method = RequestMethod.POST)
	public String updateYourself(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "forms/user";
		}

		User entity = userService.selectByNdivalue(user.getNdivalue());
		if ((entity.getNdivalue() != null) && (entity.getId() != user.getId())) {

			FieldError ssoError = new FieldError("user", "ndivalue",
					messageSource.getMessage("notify.duplicateUser", new String[] { user.getNdivalue() }, locale));
			result.addError(ssoError);
			return "forms/user";
		}

		if (!checkAdmin(entity, user)) {
			FieldError ssoError = new FieldError("user", "roles",
					messageSource.getMessage("quit-admin-error-message", new String[] { user.getNdivalue() }, locale));
			result.addError(ssoError);
			return "forms/user";
		}

		userService.update(user);
		redirectAttributes.addFlashAttribute("notifyMesage", "notify.updated-user-yourself");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		nullAuthentication(request, response);
		return "redirect:/login?logout";
	}

	/**
	 * true if user is administrator and not exist other administrator in
	 * platform
	 * 
	 * @param oldUser
	 * @param updateUser
	 * @return boolean
	 */
	private boolean checkAdmin(User oldUser, User updateUser) {
		boolean oldUserIsAdmin = false;
		boolean updateUserIsAdmin = false;

		for (Role role : oldUser.getRoles()) {
			if (role.getId() == 1)
				oldUserIsAdmin = true;
		}

		for (Role role : updateUser.getRoles()) {
			if (role.getId() == 1)
				updateUserIsAdmin = true;
		}

		if (oldUserIsAdmin && !updateUserIsAdmin) {
			if (roleService.countAdministrators() <= 1)
				return false;
		}
		return true;
	}

	/**
	 * show page to access denied
	 * 
	 * @param model
	 * @return accessDenied.jsp
	 */

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}

	/**
	 * show page to delete user
	 * 
	 * @param ndivalue
	 * @param model
	 * @param redirectAttributes
	 * @return forms/deleteUser.jsp
	 */
	@RequestMapping(value = "/ad/delete-user-{ndivalue}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("ndivalue") String ndivalue, Model model,
			RedirectAttributes redirectAttributes) {

		User user = userService.selectByNdivalue(ndivalue);

		for (Role role : user.getRoles()) {
			if (role.getType().equals("ADMIN")) {
				redirectAttributes.addFlashAttribute("notifyMesage", "notify.not-delete-user-admin");
				redirectAttributes.addFlashAttribute("notifyType", "danger");
				return "redirect:/";
			}
		}

		model.addAttribute("user", user);
		model.addAttribute("userPrincipal", new User());
		return "forms/deleteUser";
	}

	/**
	 * post method to delete user
	 * 
	 * @param userPrincipal
	 * @param ndivalue
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return redirect to home (/)
	 */
	@RequestMapping(value = { "/ad/delete-user-{ndivalue}" }, method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("userPrincipal") User userPrincipal,
			@PathVariable("ndivalue") String ndivalue, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "redirect:/";
		}

		User principal = getPrincipal();

		if (!passwordEncoder.matches(userPrincipal.getPassword(), principal.getPassword())) {
			FieldError ssoError = new FieldError("userPrincipal", "password",
					messageSource.getMessage("incorrect-password", new String[] {}, locale));
			result.addError(ssoError);
			User user = userService.selectByNdivalue(ndivalue);

			model.addAttribute("user", user);
			return "forms/deleteUser";
		}

		if (!(principal.getNdivalue().equals(userPrincipal.getNdivalue()))) {
			FieldError ssoError = new FieldError("userPrincipal", "ndivalue",
					messageSource.getMessage("incorrect-document", new String[] {}, locale));
			result.addError(ssoError);
			User user = userService.selectByNdivalue(ndivalue);

			model.addAttribute("user", user);
			return "forms/deleteUser";
		}

		User user = userService.selectByNdivalue(ndivalue);
		// delete exams of user
		Set<Exam> userExams = examService.selectByUser(user.getId());
		for (Exam exam : userExams) {
			examService.deleteExam(exam.getOrder());
		}

		// delete user
		userService.delete(user);

		redirectAttributes.addFlashAttribute("notifyMesage", "notify.user-deleted");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		return "redirect:/";
	}

	/**
	 * show form to update user
	 * 
	 * @param ndivalue
	 * @param model
	 * @return forms/user.jsp
	 */
	@RequestMapping(value = "/ad/update-user/{ndivalue}", method = RequestMethod.GET)
	public String updateUser(@PathVariable("ndivalue") String ndivalue, Model model) {

		if ((getPrincipal().getNdivalue()).equals(ndivalue)) {
			return "redirect:/updateYourself";
		}
		User user = userService.selectByNdivalue(ndivalue);
		user.setPassword("");

		model.addAttribute("user", user);
		return "forms/user";
	}

	/**
	 * post mehotd to update user
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return redirect to home (/)
	 */
	@RequestMapping(value = { "/ad/update-user" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "redirect:/";
		}
		User entity = userService.selectByNdivalue(user.getNdivalue());
		if ((entity.getNdivalue() != null) && (entity.getId() != user.getId())) {
			FieldError ssoError = new FieldError("user", "ndivalue",
					messageSource.getMessage("notify.duplicateUser", new String[] { user.getNdivalue() }, locale));
			result.addError(ssoError);

			return "forms/user";
		}
		userService.update(user);
		redirectAttributes.addFlashAttribute("notifyMesage", "notify.updated-user");
		redirectAttributes.addFlashAttribute("notifyType", "success");

		User loggedinuser = (User) request.getSession().getAttribute("loggedinuser");
		if (user.getId() == loggedinuser.getId()) {
			nullAuthentication(request, response);
			redirectAttributes.addFlashAttribute("notifyMesage", "notify.updated-user-yourself");
			return "redirect:/login?logout";

		}
		return "redirect:/";
	}

	/**
	 * redirect to consultUser controller
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return redirect to consultUser()
	 */
	@RequestMapping(value = "/ad/consult-user", method = RequestMethod.POST)
	public String consultUser(@Valid User user, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (!userService.find(user.getNdivalue())) {
			redirectAttributes.addFlashAttribute("notExistUser", true);
			return "redirect:/";
		}
		return "redirect:/ad/" + user.getNdivalue();
	}

	/**
	 * show user page
	 * 
	 * @param ndivalue
	 * @param model
	 * @return shows/user.jsp
	 */
	@RequestMapping(value = { "/pe/{ndivalue}", "/ad/{ndivalue}", "/pa/{ndivalue}" }, method = RequestMethod.GET)
	public String consultUser(@PathVariable("ndivalue") String ndivalue, Model model) {
		consultUser(userService.selectByNdivalue(ndivalue), model);
		return "shows/user";
	}

	/**
	 * shows user page to user logged
	 * 
	 * @param model
	 * @return shows user exams
	 */
	@RequestMapping(value = "/pa/exams", method = RequestMethod.GET)
	public String consultYourselfExam(Model model) {
		consultUser(getPrincipal(), model);
		return "shows/user";
	}

	/**
	 * set to attributes to model for consult user
	 * 
	 * @param user
	 * @param model
	 */
	public void consultUser(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("age", age(user.getBirthDate()));
		model.addAttribute("examForm", new Exam());
		model.addAttribute("exams", examService.selectByUser(user.getId()));
		model.addAttribute("listExam", examService.listPanelExam());
	}

	/**
	 * post method to insert exam by personal
	 * 
	 * @param exam
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return redirect to consultUser controller
	 */
	@RequestMapping(value = "/pe/authorize-exam", method = RequestMethod.POST)
	public String authorizeExam(@ModelAttribute("examForm") Exam exam, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes, Locale locale) {

		exam.setIssued(LocalDateTime.now().toString());
		exam = examService.getInitialExamParameters(exam);
		examService.insert(exam);
		return "redirect:/pe/" + exam.getReferenceSubject();
	}

	/**
	 * method to delete order exam
	 * 
	 * @param order
	 * @param ndivalue
	 * @param model
	 * @param redirectAttributes
	 * @return redirect to consultUser()
	 */
	@RequestMapping(value = "/pe/delete-exam-{order}/{patientNdiValue}", method = RequestMethod.GET)
	public String deleteExam(@PathVariable("order") Integer order, @PathVariable("patientNdiValue") String ndivalue,
			Model model, RedirectAttributes redirectAttributes) {

		User user = userService.selectByNdivalue(ndivalue);
		Exam exam = examService.selectByOrder(order);

		if (!user.getNdivalue().equals(exam.getReferenceSubject()) || exam.getDone() == (byte) 1) {
			redirectAttributes.addFlashAttribute("notifyMesage", "unknown-error");
			redirectAttributes.addFlashAttribute("notifyType", "danger");
			return "redirect:/pe/" + ndivalue;
		}

		examService.deleteExam(order);
		redirectAttributes.addFlashAttribute("notifyMesage", "deleted-exam");
		redirectAttributes.addFlashAttribute("notifyType", "success");

		return "redirect:/pe/" + ndivalue;
	}

	/**
	 * show to list exam for laboratorist user
	 * 
	 * @param model
	 * @return shows/labList.jsp
	 */
	@RequestMapping(value = "/la/exams", method = RequestMethod.GET)
	public String exams(Model model) {

		model.addAttribute("exams", examService.selectNotDone());
		model.addAttribute("user", userService.selectById(1));
		return "shows/labList";
	}

	/**
	 * show order exams
	 * 
	 * @param order
	 * @param model
	 * @return shows/order.jsp
	 */
	@RequestMapping(value = "/pp/exam-{order}", method = RequestMethod.GET)
	public String exam(@PathVariable("order") Integer order, Model model) {
		model.addAttribute("exam", examService.selectByOrder(order));
		return "shows/order";
	}

	/**
	 * show page to perform exam for laboratorist
	 * 
	 * @param order
	 * @param model
	 * @return forms/order.jsp
	 */
	@RequestMapping(value = "/la/perform-exam-{order}", method = RequestMethod.GET)
	public String performExams(@PathVariable("order") Integer order, Model model) {
		Exam exam = examService.getInitialExamParameters(examService.selectByOrder(order));
		model.addAttribute("examForm", exam);
		return "forms/order";
	}

	/**
	 * post method to perform exam for laboratorist
	 * 
	 * @param exam
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return exams to exams controller
	 */
	@RequestMapping(value = "/la/perform-exam", method = RequestMethod.POST)
	public String performExams(@Valid Exam exam, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "forms/order";
		}
		exam.setIssued(LocalDateTime.now().toString());
		exam.setDone((byte) 1);
		examService.update(exam);
		redirectAttributes.addFlashAttribute("notifyMesage", "realized-exam");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		return "redirect:/la/exams";
	}

	/**
	 * post method for set comments into exam for personal
	 * 
	 * @param exam
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return redirect to exam controller
	 */
	@RequestMapping(value = "/pe/perform-exam", method = RequestMethod.POST)
	public String updatePerfomerCommentsExam(@Valid Exam exam, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "shows/order";
		}
		Exam exam2 = examService.selectByOrder(exam.getOrder());
		exam2.setPerformerComments(exam.getPerformerComments());
		examService.update(exam2);
		redirectAttributes.addFlashAttribute("notifyMesage", "performerComments-exam");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		return "redirect:/pp/exam-" + exam.getOrder();
	}

	/**
	 * redirect to consultUser controller when exist patient
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/pe/consult-patient", method = RequestMethod.POST)
	public String consulPatient(@Valid User user, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		User entity = userService.selectByNdivalue(user.getNdivalue());

		if (entity.getNdivalue() == null) {
			redirectAttributes.addFlashAttribute("notExistPatient", true);
			return "redirect:/";
		}

		for (Role role : entity.getRoles()) {
			if (role.getType().equals("PATIENT"))
				return "redirect:/pe/" + user.getNdivalue();
		}
		redirectAttributes.addFlashAttribute("notExistPatient", true);
		return "redirect:/";
	}

	/**
	 * return years from birthdate
	 * 
	 * @param birthdate
	 * @return years
	 */
	private Integer age(String birthdate) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Period p = Period.between(birthday, today);
		return p.getYears();
	}

	/**
	 * show set new user page
	 * 
	 * @param model
	 * @return forms/user.jsp
	 */
	@RequestMapping(value = "/ad/new-user", method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "forms/user";
	}

	/**
	 * post method to new user
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/ad/new-user" }, method = RequestMethod.POST)
	public String newUser(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "forms/user";
		}
		if (userService.find(user.getNdivalue())) {
			FieldError ssoError = new FieldError("user", "ndivalue",
					messageSource.getMessage("notify.duplicateUser", new String[] { user.getNdivalue() }, locale));
			result.addError(ssoError);

			return "forms/user";
		}

		userService.insert(user);
		redirectAttributes.addFlashAttribute("notifyMesage", "notify.created-user");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		return "redirect:/";
	}

	/**
	 * redirect to login page
	 * 
	 * @param error
	 * @param logout
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Model model) {

		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		}

		if (roleService.countAdministrators() == 0) {
			Authentication auth = new UsernamePasswordAuthenticationToken(null, null,
					Arrays.asList(new SimpleGrantedAuthority("ROLE_TEMPORAL")));
			SecurityContextHolder.getContext().setAuthentication(auth);
			return "redirect:/te/new-admin";
		}

		return "redirect:/";

	}

	/**
	 * null authentication for session
	 * 
	 * @param request
	 * @param response
	 */
	private void nullAuthentication(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			try {
				request.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

	/**
	 * logout user in session
	 * 
	 * @param request
	 * @param response
	 * @return login.jsp
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		nullAuthentication(request, response);
		return "redirect:/login?logout";
	}

	/**
	 * 
	 * @return user logged
	 */
	private User getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof User)
			return (User) principal;
		else if (principal instanceof UserDetails)
			return userService.selectByNdivalue(((UserDetails) principal).getUsername());
		else
			return new User();
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/**
	 * 
	 * @return authorities from user logged
	 */
	private Collection<? extends GrantedAuthority> getAuthorities() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities();
	}

	/**
	 * show form user page to new administrator
	 * 
	 * @param model
	 * @return forms/user.jsp
	 */
	@RequestMapping(value = "/te/new-admin", method = RequestMethod.GET)
	public String newAdmin(ModelMap model) {

		User user = new User();
		model.addAttribute("user", user);
		return "forms/user";
	}

	/**
	 * post method to insert new administrator
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @param response
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = { "/te/new-admin" }, method = RequestMethod.POST)
	public String newAdmin(@Valid User user, BindingResult result, HttpServletRequest request,
			HttpServletResponse response, Model model, Locale locale, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "forms/user";
		}
		userService.insert(user);

		redirectAttributes.addFlashAttribute("notifyMesage", "login.msg.temporal");
		redirectAttributes.addFlashAttribute("notifyType", "success");
		nullAuthentication(request, response);
		return "redirect:/login?logout";
	}

}