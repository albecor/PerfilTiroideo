package com.medical.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.medical.spring.model.Lab;
import com.medical.spring.services.ExamService;
import com.medical.spring.services.LabService;
import com.medical.spring.validator.LabFormValidator;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Controller
public class LabController {

	@Autowired
	LabFormValidator labFormValidator;

	/**
	 * @param binder - binder to user lab form
	 */
	@InitBinder("labForm")
	protected void initLabBinder(WebDataBinder binder) {
		binder.setValidator(labFormValidator);
	}

	@Autowired
	ExamService examService;

	@Autowired
	private LabService labService;

	private final Logger logger = LoggerFactory.getLogger(LabController.class);

	/**
	 * user lab index page
	 * @param model
	 * @return - index user lab page
	 */
	@RequestMapping(value = { "/lab" }, method = RequestMethod.GET)
	public String Welcome(Model model) {

		logger.debug("lab()");

		model.addAttribute("exams", examService.selectForLab());
		return "/lab/index";
	}

	
	/**
	 * make order page
	 * @param order - order number
	 * @param ndivalue - user patient's ndivalue
	 * @param model
	 * @return - order page
	 */
	@RequestMapping(value = "/lab/{order}/{ndivalue}/RealizarOrden", method = RequestMethod.GET)
	public String doOrder(@PathVariable("order") String order, @PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("doOrder() : {}");

		model.addAttribute("examForm", examService.select(ndivalue, order));

		return "lab/order";
	}

	
	/**
	 * make order function post
	 * @param exam - exma object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index lab page
	 */
	@RequestMapping(value = "/lab/{order}/{ndivalue}/RealizarOrden", method = RequestMethod.POST)
	public String autorizeExam(@ModelAttribute("examForm") Exam exam, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		exam.setIssued(hourdateFormat.format(date));
		exam.setDone(1);
		System.out.println(exam.toString());
		examService.edit(exam);

		logger.debug("autorizeExam() : {}", exam.getCode());
		return "redirect:/lab";
	}

	
	/**
	 * update user lab page
	 * @param model
	 * @return - user lab form page
	 */
	@RequestMapping(value = "/lab/ActualizarLaboratorista", method = RequestMethod.GET)
	public String showUpdateLab(Model model) {

		logger.debug("showUpdateLab() : {}");

		// extract to username
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addAttribute("labForm", labService.select(userDetail.getUsername()));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("edit", "lab");
		model.addAttribute("roleType", "ROLE_LAB");

		return "forms/lab";
	}

	
	/**
	 * update user lab function post
	 * @param lab - 
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index lab page, or user lab form if has errors
	 */
	@RequestMapping(value = "/lab/ActualizarLaboratorista", method = RequestMethod.POST)
	public String updateLab(@ModelAttribute("labForm") @Validated Lab lab, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("updateLab() : {}", lab);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_LAB");
			return "forms/lab";

			// if ndivalue will be updated
		} else if (!labService.selectNdivalueById(lab.getId()).equals(lab.getNdivalue())) {

			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg",
					"No se puede actualizar el No. de documento. Solicitalo al administrador");
			return "redirect:/lab";
		}
		labService.edit(lab);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el usuario de laboratorio!");

		return "redirect:/lab";

	}

}