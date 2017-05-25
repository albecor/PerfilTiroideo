package com.medical.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.spring.model.Admin;
import com.medical.spring.model.Lab;
import com.medical.spring.model.Patient;
import com.medical.spring.model.Personal;
import com.medical.spring.model.User;
import com.medical.spring.resources.Age;
import com.medical.spring.resources.SendMail;
import com.medical.spring.services.AdminService;
import com.medical.spring.services.DatabaseService;
import com.medical.spring.services.LabService;
import com.medical.spring.services.PatientService;
import com.medical.spring.services.PersonalService;
import com.medical.spring.services.UserService;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */

@Controller
public class MainController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private LabService labService;
	
	@Autowired
	private SendMail sendMail;	

	@Autowired
	private UserService userService;

	@Autowired
	private DatabaseService databaseService;

	private final Logger logger = LoggerFactory.getLogger(MainController.class);

	/**
	 * start control page
	 * @return - redirect to index user page
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String defaultPage() {
		logger.debug("index()");

		// create database if not exist
		databaseService.createDatabase();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			return "redirect:/login";
		}

		// User details session
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		if (!adminService.findById(1)) {// Add admin if not exist
			logger.debug("No existe admin");
			return "redirect:/init/InsertarAdministrador";
		} else if (userDetail.getUsername().equals("root")) {// logout root
																// default if
																// exist admin
			SecurityContextHolder.getContext().setAuthentication(null);
		}

		User user = userService.select(userDetail.getUsername());

		if (user.roleCount() > 1) {
			return "/selectRole";
		}

		if (user.isPatient()) {
			return "redirect:/patient";
		}

		if (user.isPersonal()) {
			return "redirect:/personal";
		}

		if (user.isLab()) {
			return "redirect:/lab";
		}

		return "redirect:/admins";
	}


	/**
	 * login page
	 * @param error - errors in page
	 * @param logout - logout user
	 * @return - show login page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		logger.debug("Haciendo login");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Usuario o contraseña incorrecta");
		}

		if (logout != null) {
			model.addObject("msg", "Has cerrado tu sesión!");
		}
		model.setViewName("login");

		return model;
	}
	
	
		/**
		 * show recover password page
		 * @param model
		 * @return - consult page
		 */
		@RequestMapping(value = "/RecoverPassword", method = RequestMethod.GET)
		public String recoverPassword(Model model) {

			logger.debug("recoverPassword() : {}");

			model.addAttribute("userform", new User());
		
			model.addAttribute("msgTitle", "Consultar usuario");
			model.addAttribute("msgContent", "Por favor escribe el número de documento del usuario.");			

			return "consult";
		}

	
	/**
	 * recover password function post
	 * @param user - user object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - login page, or consult page if has errors
	 */
	@RequestMapping(value = "/RecoverPassword", method = RequestMethod.POST)
	public String recoverPassword(@ModelAttribute("userform") User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("consultUpdatePatient() : {}", user.getUsername());

		User user2 = userService.select(user.getUsername());
		if (user2.getUsername() == null) {
			result.rejectValue("username", "error.UserNotExist",
					"No existe un usuario con el documento No. " + user.getUsername());			
			
			model.addAttribute("msgTitle", "Consultar usuario");
			model.addAttribute("msgContent", "Por favor escribe el número de documento del usuario.");
			
			return "/consult";
		}
		
	
	 
	if(user2.isAdmin()){
		Admin admin = adminService.select(user.getUsername());
		sendMail.sendMail(admin.getEmail(), user2.getPassword(), admin.getName());
	}	

	if(user2.isPatient()){
		Patient patient = patientService.select(user.getUsername());
		sendMail.sendMail(patient.getEmail(), user2.getPassword(), patient.getGiven());
		
	}
	
	if(user2.isPersonal()){
		Personal personal = personalService.select(user.getUsername());
		sendMail.sendMail(personal.getEmail(), user2.getPassword(), personal.getGiven());
		
	}
	
	if(user2.isLab()){
		Lab lab = labService.select(user.getUsername());
		sendMail.sendMail(lab.getEmail(), user2.getPassword(), lab.getName());		
	}

	redirectAttributes.addFlashAttribute("msg", "La contraseña fué enviada al correo.");
	return "redirect:/login";
	}

	
	/**
	 * access denied page
	 * @return - model page 
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();

			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;
	}

}