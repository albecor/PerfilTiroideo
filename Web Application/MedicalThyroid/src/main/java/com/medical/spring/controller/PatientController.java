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
import com.medical.spring.model.Patient;
import com.medical.spring.model.utilities.DefaultModel;
import com.medical.spring.services.ExamService;
import com.medical.spring.services.PatientService;
import com.medical.spring.validator.PatientFormValidator;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Controller
public class PatientController {

	@Autowired
	PatientFormValidator patientFormValidator;

	@Autowired
	private ExamService examService;

	/**
	 * @param binder - binder to user patient form
	 */
	@InitBinder("patientForm")
	protected void initPatientBinder(WebDataBinder binder) {
		binder.setValidator(patientFormValidator);
	}

	@Autowired
	private PatientService patientService;

	private final Logger logger = LoggerFactory.getLogger(PatientController.class);

	/**
	 * show information page from patient
	 * @param model
	 * @return - show information patient page
	 */
	@RequestMapping(value = { "/patient" }, method = RequestMethod.GET)
	public String Welcome(Model model) {

		logger.debug("patient()");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addAttribute("roleType", "ROLE_PATIENT");
		model.addAttribute("patient", patientService.select(userDetail.getUsername()));
		model.addAttribute("exams", examService.selectAll(userDetail.getUsername()));
		return "/shows/patient";
	}

	
	/**
	 * show patient form page
	 * @param model
	 * @return - patient form page
	 */
	@RequestMapping(value = "/patient/ActualizarPaciente", method = RequestMethod.GET)
	public String showUpdatePatient(Model model) {

		logger.debug("showUpdatePatient() : {}");
		// extract to username

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addAttribute("patientForm", patientService.select(userDetail.getUsername()));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("roleType", "ROLE_PATIENT");
		model.addAttribute("edit", "patient");
		defaultModelPatient(model);

		return "forms/patient";

	}

	
	/**
	 * update patient function post
	 * @param patient - patient object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index patient page
	 */
	@RequestMapping(value = "/patient/ActualizarPaciente", method = RequestMethod.POST)
	public String updatePatient(@ModelAttribute("patientForm") @Validated Patient patient, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("updatePatient() : {}", patient);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_PATIENT");
			model.addAttribute("edit", "patient");
			defaultModelPatient(model);
			return "forms/patient";

			// if ndivalue will be updated
		} else if (!patientService.selectNdivalueById(patient.getId()).equals(patient.getNdivalue())) {

			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg",
					"No se puede actualizar el No. de documento. Solicitalo al administrador");
			return "redirect:/patient";

		}
		patientService.edit(patient);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el paciente!");

		return "redirect:/patient";
	}

	
	/**
	 * show information order from patient
	 * @param order - order number
	 * @param model
	 * @return - exam page
	 */
	@RequestMapping(value = "/patient/{order}/examen", method = RequestMethod.GET)
	public String consultExam(@PathVariable("order") String order, Model model) {

		logger.debug("consultExam() : {}");

		Exam exam = examService.selectByOrder(order);

		model.addAttribute("exam", exam);
		model.addAttribute("roleType", "ROLE_PATIENT");
		return "shows/exam";
	}

	
	/**
	 * default model of patient
	 * @param model
	 */
	private void defaultModelPatient(Model model) {
		model.addAttribute("ndiList", new DefaultModel().ndiList());
		model.addAttribute("genderList", new DefaultModel().genderList());
		model.addAttribute("maritalStatusList", new DefaultModel().maritalStatusList());
		model.addAttribute("relationshipList", new DefaultModel().relationshipList());
		model.addAttribute("bloodList", new DefaultModel().bloodList());
	}
}