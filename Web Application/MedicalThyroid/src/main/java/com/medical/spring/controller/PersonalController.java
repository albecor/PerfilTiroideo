package com.medical.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.spring.model.Exam;
import com.medical.spring.model.Personal;
import com.medical.spring.model.User;
import com.medical.spring.model.utilities.DefaultModel;
import com.medical.spring.services.ExamService;
import com.medical.spring.services.PatientService;
import com.medical.spring.services.PersonalService;
import com.medical.spring.validator.PersonalFormValidator;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Controller
public class PersonalController {

	@Autowired
	PersonalFormValidator personalFormValidator;

	/**
	 * @param binder - binder to user personal form
	 */
	@InitBinder("personalForm")
	protected void initPersonalBinder(WebDataBinder binder) {
		binder.setValidator(personalFormValidator);
	}

	@Autowired
	private PatientService patientService;

	@Autowired
	private PersonalService personalService;

	@Autowired
	private ExamService examService;

	private final Logger logger = LoggerFactory.getLogger(PersonalController.class);

	/**
	 * index personal page
	 * @param model
	 * @return - redirect consult patient page
	 */
	@RequestMapping(value = { "/personal" }, method = RequestMethod.GET)
	public String Welcome(Model model) {

		logger.debug("personal()");
		return "redirect:/personal/consulta/paciente";
	}

	
	/**
	 * show consult patient page
	 * @param model
	 * @return - consult page
	 */
	@RequestMapping(value = "/personal/consulta/paciente", method = RequestMethod.GET)
	public String consultUpdatePatient(Model model) {

		logger.debug("consultPatient() : {}");

		model.addAttribute("userform", new User());
		model.addAttribute("action", "paciente");
		model.addAttribute("msgTitle", "Consultar paciente");
		model.addAttribute("msgContent", "Por favor escribe el número de documento del paciente");
		model.addAttribute("roleType", "ROLE_PERSONAL");

		return "consult";
	}

	
	/**
	 * consult patient function post
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - show information patient page, or consult page if has errors
	 */
	@RequestMapping(value = "/personal/consulta/paciente", method = RequestMethod.POST)
	public String consultUpdatePatient(@ModelAttribute("userform") User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("consultUpdatePatient() : {}", user.getUsername());

		if (patientService.find(user.getUsername())) {

			return "redirect:/personal/" + user.getUsername() + "/paciente";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El paciente no existe");
		return "redirect:/personal/consulta/paciente";
	}

	
	/**
	 * show information patient page
	 * @param ndivalue
	 * @param model
	 * @return - show information patient page
	 */
	@RequestMapping(value = "/personal/{ndivalue}/paciente", method = RequestMethod.GET)
	public String consultPatient(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("consultPatient() : {}");

		model.addAttribute("examForm", new Exam());
		model.addAttribute("thyroidProfileList", new DefaultModel().thyroidProfileList());
		model.addAttribute("patient", patientService.select(ndivalue));
		model.addAttribute("exams", examService.selectAll(ndivalue));

		model.addAttribute("action", "paciente");
		model.addAttribute("roleType", "ROLE_PERSONAL");

		return "shows/patient";
	}

	
	/**
	 * delete order function get
	 * @param ndivalue - patient's ndivalue
	 * @param order - order number
	 * @param model
	 * @param redirectAttributes
	 * @return - redirect to information patient page
	 */
	@RequestMapping(value = "/personal/{order}/{ndivalue}/EliminarOrden", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable("ndivalue") String ndivalue, @PathVariable("order") String order,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("consultPatient() : {}");

		if (!examService.find(ndivalue, order)) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "La orden no existe para el paciente");

			return "redirect:/personal/" + ndivalue + "/paciente";
		}

		if (examService.select(ndivalue, order).getValueQuantity() != null) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "La orden ya fue realizada");
			return "redirect:/personal/" + ndivalue + "/paciente";
		}

		examService.delete(ndivalue, order);
		return "redirect:/personal/" + ndivalue + "/paciente";
	}

	
	/**
	 * autorize exam function post
	 * @param exam - exam object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - redirect to information patient page
	 */
	@RequestMapping(value = "/personal/autorizarExamen", method = RequestMethod.POST)
	public String autorizeExam(@ModelAttribute("examForm") Exam exam, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		// User details session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Personal personal = personalService.select(userDetail.getUsername());
		exam.setDisplayPerformer(personal.getGiven() + " " + personal.getFamily());
		exam.setReferencePerfomer(personal.getNdivalue());

		examService.insert(exam);
		logger.debug("autorizeExam() : {}", exam.getCode());
		return "redirect:/personal/" + exam.getReferenceSubject() + "/paciente";
	}

	
	/**
	 * show information exam page
	 * @param order - order number
	 * @param model
	 * @return - show information exam page
	 */
	@RequestMapping(value = "/personal/{order}/examen", method = RequestMethod.GET)
	public String consultExam(@PathVariable("order") String order, Model model) {

		logger.debug("consultExam() : {}");

		Exam exam = examService.selectByOrder(order);

		model.addAttribute("exam", exam);
		model.addAttribute("roleType", "ROLE_PERSONAL");
		return "shows/exam";
	}

	
	/**
	 * update personal form page
	 * @param model
	 * @return - personal form
	 */
	@RequestMapping(value = "/personal/ActualizarPersonal", method = RequestMethod.GET)
	public String showUpdatePersonal(Model model) {

		logger.debug("showUpdatePersonalForm() : {}");

		// extract to username
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		model.addAttribute("personalForm", personalService.select(userDetail.getUsername()));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("edit", "personal");
		model.addAttribute("roleType", "ROLE_PERSONAL");
		defaultModelPersonal(model);

		return "forms/personal";
	}

	
	/**
	 * update personal function post
	 * @param personal - personal object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - consult patient page, or personal form page if has errors
	 */
	@RequestMapping(value = "/personal/ActualizarPersonal", method = RequestMethod.POST)
	public String updatePersonal(@ModelAttribute("personalForm") @Validated Personal personal, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("updatePersonal() : {}", personal);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_PERSONAL");
			defaultModelPersonal(model);
			return "forms/personal";

			// if ndivalue will be updated
		} else if (!personalService.selectNdivalueById(personal.getId()).equals(personal.getNdivalue())) {

			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg",
					"No se puede actualizar el No. de documento. Solicitalo al administrador");
			return "redirect:/personal/consulta/paciente";

		}

		personalService.edit(personal);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el usuario personal!");

		return "redirect:/personal/consulta/paciente";

	}

	
	/**
	 * default model personal
	 * @param model
	 */
	private void defaultModelPersonal(Model model) {
		model.addAttribute("ndiList", new DefaultModel().ndiList());
		model.addAttribute("genderList", new DefaultModel().genderList());
		model.addAttribute("roleList", new DefaultModel().roleList());
	}

}