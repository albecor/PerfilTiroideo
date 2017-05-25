package com.medical.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.medical.spring.model.Admin;
import com.medical.spring.model.Lab;
import com.medical.spring.model.Patient;
import com.medical.spring.model.Personal;
import com.medical.spring.model.User;
import com.medical.spring.model.utilities.DefaultModel;
import com.medical.spring.services.AdminService;
import com.medical.spring.services.LabService;
import com.medical.spring.services.PatientService;
import com.medical.spring.services.PersonalService;
import com.medical.spring.services.UserService;
import com.medical.spring.validator.AdminFormValidator;
import com.medical.spring.validator.LabFormValidator;
import com.medical.spring.validator.PatientFormValidator;
import com.medical.spring.validator.PersonalFormValidator;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Controller
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminFormValidator adminFormValidator;

	@Autowired
	PersonalFormValidator personalFormValidator;

	@Autowired
	PatientFormValidator patientFormValidator;

	@Autowired
	LabFormValidator labFormValidator;

	/**
	 * @param binder - binder to lab user form
	 */
	@InitBinder("labForm")
	protected void initLabBinder(WebDataBinder binder) {
		binder.setValidator(labFormValidator);
	}

	/**
	 * @param binder - binder to admin user form
	 */
	@InitBinder("adminForm")
	protected void initAdminBinder(WebDataBinder binder) {
		binder.setValidator(adminFormValidator);
	}

	/**
	 * @param binder - binder to personal user form
	 */
	@InitBinder("personalForm")
	protected void initPersonalBinder(WebDataBinder binder) {
		binder.setValidator(personalFormValidator);
	}

	/**
	 * @param binder - binder to patient user form
	 */
	@InitBinder("patientForm")
	protected void initPatientBinder(WebDataBinder binder) {
		binder.setValidator(patientFormValidator);
	}

	@Autowired
	private AdminService adminService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private PersonalService personalService;

	@Autowired
	private LabService labService;

	@Autowired
	private UserService userservice;

	/**
	 * @param model
	 * @return - admin index page
	 */
	@RequestMapping(value = { "/admins" }, method = RequestMethod.GET)
	public String Welcome(Model model) {

		logger.debug("showAllUsers()");
		model.addAttribute("admin", adminService.selectById(1));
		return "admins/index";
	}

	/*
	 * -------------------------------------------------------------------------
	 * - ADMIN CONTROL
	 * -------------------------------------------------------------------------
	 * -
	 */

	
	/**
	 * insert admin page
	 * @param model
	 * @return - show admin form page to insert admin
	 */
	@RequestMapping(value = "/init/InsertarAdministrador", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		if (adminService.findById(1)) {
			return "init/show";
		}

		Admin admin = new Admin();

	/*	admin.setNdivalue("1");
		admin.setName("Marina ca");
		admin.setPhone("8635959");
		admin.setEmail("marina@hotmail.com");
		admin.setAddress("marinaramos");
		admin.setNdi("C.C");
		
		*/

		model.addAttribute("action", "Insertar");
		model.addAttribute("folder", "init");
		model.addAttribute("adminForm", admin);

		model.addAttribute("ndiList", new DefaultModel().ndiList());

		return "forms/admin";

	}


	/**
	 * insert admin function post
	 * @param admin - admin object
	 * @param result - bindinresult validated
	 * @param model - model
	 * @param redirectAttributes - redirectAttributes
	 * @return - init show page or insert admin page if has errors
	 */
	@RequestMapping(value = "/init/InsertarAdministrador", method = RequestMethod.POST)
	public String saveAdmin(@ModelAttribute("adminForm") @Validated Admin admin, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("saveAdmin() : {}", admin);

		if (result.hasErrors()) { // if there are errors in adminForm

			model.addAttribute("action", "Insertar");
			model.addAttribute("folder", "init");
			model.addAttribute("ndiList", new DefaultModel().ndiList());
			return "forms/admin";

		} else {

			adminService.insert(admin); // add admin to database
			logger.debug("insert Admin : {}", admin);

			model.addAttribute("name", admin.getName());
			return "init/show";
		}
	}

	
	/**
	 * update user function post
	 * @param admin - admin object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page or admin form page if has errors and user exists
	 */
	@RequestMapping(value = "/admins/ActualizarAdministrador", method = RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("adminForm") @Validated Admin admin, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("UpdateAdmin() : {}", admin);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("ndiList", new DefaultModel().ndiList());
			model.addAttribute("action", "Actualizar");
			model.addAttribute("folder", "admins");
			return "forms/admin";

		} else {
			if (!adminService.selectNdivalueById(admin.getId()).equals(admin.getNdivalue())) {
				if (userservice.find(admin.getNdivalue())) {
					model.addAttribute("css", "danger");
					model.addAttribute("msg", "Ya existe un usuario con el documento No. " + admin.getNdivalue());
					model.addAttribute("ndiList", new DefaultModel().ndiList());
					model.addAttribute("action", "Actualizar");
					return "forms/admin";
				}
			}
			adminService.edit(admin);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Se han actualizado los datos del administrador!");
		}
		return "redirect:/admins";
	}

	
	/**
	 * show update admin page
	 * @param model
	 * @return - update admin form
	 */
	@RequestMapping(value = "/admins/ActualizarAdministrador", method = RequestMethod.GET)
	public String showUpdateAdmin(Model model) {

		logger.debug("showUpdateAdminForm() : {}");

		Admin admin = adminService.selectById(1);

		model.addAttribute("adminForm", admin);
		model.addAttribute("ndiList", new DefaultModel().ndiList());
		model.addAttribute("action", "Actualizar");
		model.addAttribute("folder", "admins");

		return "forms/admin";
	}

	/*
	 * -------------------------------------------------------------------------
	 * - PERSONAL CONTROL
	 * -------------------------------------------------------------------------
	 * 
	 */
	
	/**
	 * show insert personal page
	 * @param model
	 * @return - personal form page
	 */
	@RequestMapping(value = "/admins/InsertarPersonal", method = RequestMethod.GET)
	public String showAddPersonal(Model model) {

		logger.debug("showAddPersonalForm() : {}");

		model.addAttribute("action", "Insertar");

		//Personal personal = new Personal();

		/*personal.setBirthDate("01-05-2017");
		personal.setCity("Neiva");
		personal.setEmail("tucorreo@gmail.com");
		personal.setFamily("Hernandez");
		personal.setGender("male");
		personal.setGiven("Sonia");
		personal.setLine("linea");
		personal.setManagingOrganization("Cafesalud");
		personal.setNdi("C.C");
		personal.setNdivalue("12");
		personal.setRole("59058001");
		personal.setTelmobile("185");
		personal.setTelwork("963");
		model.addAttribute("personalForm", personal);*/

		model.addAttribute("personalForm",new Personal());
		
		model.addAttribute("roleType", "ROLE_ADMIN");
		defaultModelPersonal(model);
		return "forms/personal";
	}

	
	/**
	 * insert personal function post
	 * @param personal - personal object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page or admin form page if has errors
	 */
	@RequestMapping(value = "/admins/InsertarPersonal", method = RequestMethod.POST)
	public String addPersonals(@ModelAttribute("personalForm") @Validated Personal personal, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("addPersonal() : {}", personal);

		if (personalService.find(personal.getNdivalue())) {
			result.rejectValue("ndivalue", "error.UserExist",
					"Ya existe un paciente con el documento No. " + personal.getNdivalue());
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPersonal(model);
			return "forms/personal";
		}

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPersonal(model);
			return "forms/personal";

		} else {
			personalService.insert(personal);

			model.addAttribute("css", "success");
			model.addAttribute("msg", "Se agregado el usuario personal!");
		}
		return "admins/index";
	}

	
	/**
	 * consult personal page
	 * @param model
	 * @return - consult form page
	 */
	@RequestMapping(value = "/admins/consulta/personal", method = RequestMethod.GET)
	public String consultUpdatePersonal(Model model) {

		logger.debug("consultUpdatePersonal() : {}");

		model.addAttribute("userform", new User());
		model.addAttribute("action", "personal");
		model.addAttribute("msgTitle", "Consultar personal");
		model.addAttribute("msgContent", "Por favor escribe el número de documento del usuario del personal");
		model.addAttribute("roleType", "ROLE_ADMIN");

		return "consult";
	}

	
	/**
	 * consult personal function post
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - show info personal page or personal form page if has errors
	 */
	@RequestMapping(value = "/admins/consulta/personal", method = RequestMethod.POST)
	public String consultUpdatePersonal(@ModelAttribute("userform") User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("consultUpdatePersonal() : {}", user.getUsername());

		if (personalService.find(user.getUsername())) {

			return "redirect:/admins/" + user.getUsername() + "/personal";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El usuario del personal no existe");
		return "redirect:/admins/consulta/personal";
	}

	
	/**
	 * show personal user page
	 * @param ndivalue
	 * @param model
	 * @return - show info personal page 
	 */
	@RequestMapping(value = "/admins/{ndivalue}/personal", method = RequestMethod.GET)
	public String consultPersonal(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("consultPersonal() : {}");

		model.addAttribute("personal", personalService.select(ndivalue));
		model.addAttribute("action", "personal");
		return "shows/personal";
	}

	
	/**
	 * update personal form page
	 * @param ndivalue
	 * @param model
	 * @return - show personal form page
	 */
	@RequestMapping(value = "/admins/{ndivalue}/ActualizarPersonal", method = RequestMethod.GET)
	public String showUpdatePersonal(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("showUpdatePersonalForm() : {}", ndivalue);

		model.addAttribute("personalForm", personalService.select(ndivalue));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("roleType", "ROLE_ADMIN");
		defaultModelPersonal(model);

		return "forms/personal";
	}

	
	/**
	 * update personal function post 
	 * @param personal - personal object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - redirect index admin page, or personal form page if has errors
	 */
	@RequestMapping(value = "/admins/ActualizarPersonal", method = RequestMethod.POST)
	public String updatePersonal(@ModelAttribute("personalForm") @Validated Personal personal, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("updatePersonal() : {}", personal);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPersonal(model);
			return "forms/personal";

			// if ndivalue will be updated
		} else if (!personalService.selectNdivalueById(personal.getId()).equals(personal.getNdivalue())) {

			// if exist one user with that new ndivalue
			if (userservice.find(personal.getNdivalue())) {
				result.rejectValue("ndivalue", "error.UserExist",
						"Ya existe un paciente con el documento No. " + personal.getNdivalue());
				model.addAttribute("action", "Actualizar");
				model.addAttribute("roleType", "ROLE_ADMIN");
				defaultModelPersonal(model);
				return "forms/personal";
			}
		}
		personalService.edit(personal);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el usuario personal!");

		return "redirect:/admins";
	}

	
	/**
	 * delete personal function get
	 * @param ndivalue - user personal's  ndivalue 
	 * @param model
	 * @param redirectAttributes
	 * @return -  index admin page, or consult personal if has errors
	 */
	@RequestMapping(value = "/admins/{ndivalue}/EliminarPersonal", method = RequestMethod.GET)
	public String deletePersonal(@PathVariable("ndivalue") String ndivalue, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("deletePersonal() : {}", ndivalue);

		if (personalService.find(ndivalue)) {

			personalService.delete(ndivalue);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El usuario fué eliminado del personal!");
			return "redirect:/admins";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El usuario del personal no existe");		
		return "redirect:/admins/consulta/personal";
	}

	/*
	 * -------------------------------------------------------------------------
	 * - LAB CONTROL
	 * -------------------------------------------------------------------------
	 * -
	 */

	
	/**
	 * insert user lab page
	 * @param model
	 * @return - lab form page
	 */
	@RequestMapping(value = "/admins/InsertarLaboratorio", method = RequestMethod.GET)
	public String showInsertLab(Model model) {

		logger.debug("showInsertLabForm() : {}");

		Lab lab = new Lab();

		/*lab.setNdivalue("1");
		lab.setName("Marina ca");
		lab.setPhone("8635959");
		lab.setEmail("marina@hotmail.com");
		lab.setAddress("marinaramos");
		lab.setNdi("C.C");
		lab.setEntitylab("cafesalud");*/

		model.addAttribute("action", "Insertar");
		model.addAttribute("roleType", "ROLE_ADMIN");
		model.addAttribute("labForm", lab);

		model.addAttribute("ndiList", new DefaultModel().ndiList());

		return "forms/lab";
	}

	
	/**
	 * insert lab function post
	 * @param lab
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or lab form if has errors
	 */
	@RequestMapping(value = "/admins/InsertarLaboratorio", method = RequestMethod.POST)
	public String insertLab(@ModelAttribute("labForm") @Validated Lab lab, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("insertLab() : {}", lab);

		if (labService.find(lab.getNdivalue())) {
			result.rejectValue("ndivalue", "error.UserExist",
					"Ya existe un laboratorista con el documento No. " + lab.getNdivalue());
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			model.addAttribute("ndiList", new DefaultModel().ndiList());
			return "forms/lab";
		}

		if (result.hasErrors()) { // if there are errors in labForm
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			model.addAttribute("ndiList", new DefaultModel().ndiList());
			return "forms/lab";

		} else {
			labService.insert(lab);

			model.addAttribute("css", "success");
			model.addAttribute("msg", "Se ha agregado el laboratorio!");
		}
		return "admins/index";
	}

	
	/**
	 *  consult user lab page
	 * @param model
	 * @return - consult page
	 */
	@RequestMapping(value = "/admins/consulta/lab", method = RequestMethod.GET)
	public String consultUpdateLab(Model model) {

		logger.debug("consultUpdateLab() : {}");

		model.addAttribute("userform", new User());
		model.addAttribute("action", "lab");
		model.addAttribute("msgTitle", "Consultar laboratorio");
		model.addAttribute("msgContent", "Por favor escribe el número de documento del usuario del laboratorio");
		model.addAttribute("roleType", "ROLE_ADMIN");

		return "consult";
	}

	
	/**
	 * consult user function post
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - show info user lab page, or consult page if has errors
	 */
	@RequestMapping(value = "/admins/consulta/lab", method = RequestMethod.POST)
	public String consultUpdateLab(@ModelAttribute("userform") User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("consultUpdateLab() : {}", user.getUsername());

		if (labService.find(user.getUsername())) {

			return "redirect:/admins/" + user.getUsername() + "/lab";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El usuario del laboratorio no existe");
		return "redirect:/admins/consulta/lab";
	}

	
	/**
	 * show info user lab page
	 * @param ndivalue - user lab's ndivalue
	 * @param model
	 * @return - show info user lab page
	 */
	@RequestMapping(value = "/admins/{ndivalue}/lab", method = RequestMethod.GET)
	public String consultlab(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("consultlab() : {}");

		model.addAttribute("lab", labService.select(ndivalue));
		model.addAttribute("action", "lab");
		return "shows/lab";
	}

	
	/**
	 * show user lab form page
	 * @param ndivalue
	 * @param model
	 * @return - lab form page
	 */
	@RequestMapping(value = "/admins/{ndivalue}/ActualizarLaboratorio", method = RequestMethod.GET)
	public String showUpdateLab(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("showUpdateLab() : {}", ndivalue);

		model.addAttribute("labForm", labService.select(ndivalue));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("roleType", "ROLE_ADMIN");
		model.addAttribute("ndiList", new DefaultModel().ndiList());

		return "forms/lab";
	}

	
	/**
	 * update user lab function post
	 * @param lab - user lab object
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or lab form if has errors
	 */
	@RequestMapping(value = "/admins/ActualizarLaboratorio", method = RequestMethod.POST)
	public String updateLab(@ModelAttribute("labForm") @Validated Lab lab, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("updateLab() : {}", lab);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			model.addAttribute("ndiList", new DefaultModel().ndiList());
			return "forms/lab";

			// if ndivalue will be updated
		} else if (!labService.selectNdivalueById(lab.getId()).equals(lab.getNdivalue())) {

			// if exist one user with that new ndivalue
			if (userservice.find(lab.getNdivalue())) {
				result.rejectValue("ndivalue", "error.UserExist",
						"Ya existe un laboratorista con el documento No. " + lab.getNdivalue());
				model.addAttribute("action", "Actualizar");
				model.addAttribute("roleType", "ROLE_ADMIN");
				model.addAttribute("ndiList", new DefaultModel().ndiList());
				return "forms/lab";
			}
		}
		labService.edit(lab);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el usuario de laboratorio!");

		return "redirect:/admins";

	}

	
	/**
	 *  delete user lab function get
	 * @param ndivalue - user lab's ndivalue
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or consult page if has errors
	 */
	@RequestMapping(value = "/admins/{ndivalue}/EliminarLaboratorio", method = RequestMethod.GET)
	public String deleteLab(@PathVariable("ndivalue") String ndivalue, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("deleteLab() : {}", ndivalue);

		if (labService.find(ndivalue)) {

			labService.delete(ndivalue);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El usuario fué eliminado del personal de laboratorio!");

			return "redirect:/admins";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El usuario del laboratorio no existe");		
		return "redirect:/admins/consulta/lab";
	}

	/*
	 * -------------------------------------------------------------------------
	 * - PATIENT CONTROL
	 * -------------------------------------------------------------------------
	 * -
	 */

	
	/**
	 * show user patient form page
	 * @param model
	 * @return - user patient form page
	 */
	@RequestMapping(value = "/admins/InsertarPaciente", method = RequestMethod.GET)
	public String showAddPatientForm(Model model) {

		logger.debug("showAddPatientForm() : {}");

		model.addAttribute("action", "Insertar");

/*		Patient patient = new Patient();

patient.setBirthDate("01-05-2017");
		patient.setCity("Neiva");
		patient.setEmail("tucorreo@gmail.com");
		patient.setFamily("Hernandez");
		patient.setGender("male");
		patient.setGiven("Sonia");
		patient.setLine("linea");
		patient.setManagingOrganization("Cafesalud");
		patient.setNdi("C.C");
		patient.setNdivalue("12");
		patient.setTelmobile("185");
		patient.setTelwork("963");
		patient.setBlood("A+");
		patient.setFamilyc("Paz");
		patient.setGivenc("Lorna");
		patient.setMaritalStatus("W");
		patient.setRelationship("family");
		patient.setTelc("852");
		model.addAttribute("patientForm", patient);
*/
		model.addAttribute("patientForm", new Patient());
		defaultModelPatient(model);
		model.addAttribute("roleType", "ROLE_ADMIN");

		return "forms/patient";
	}

	
	/**
	 * insert patient function post
	 * @param patient
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or user patient form if has errors
	 */
	@RequestMapping(value = "/admins/InsertarPaciente", method = RequestMethod.POST)
	public String addPatient(@ModelAttribute("patientForm") @Validated Patient patient, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("addPatient() : {}", patient);

		if (patientService.find(patient.getNdivalue())) {
			result.rejectValue("ndivalue", "error.UserExist",
					"Ya existe un paciente con el documento No. " + patient.getNdivalue());
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPatient(model);
			return "forms/patient";
		}

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Insertar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPatient(model);
			return "forms/patient";

		} else {
			patientService.insert(patient);

			model.addAttribute("css", "success");
			model.addAttribute("msg", "Se ha agregado el paciente!");
		}
		return "admins/index";
	}

	
	/**
	 * consult user patient page
	 * @param model
	 * @return - consult page
	 */
	@RequestMapping(value = "/admins/consulta/paciente", method = RequestMethod.GET)
	public String consultUpdatePatient(Model model) {

		logger.debug("consultUpdatePatient() : {}");

		model.addAttribute("userform", new User());
		model.addAttribute("action", "paciente");
		model.addAttribute("msgTitle", "Consultar paciente");
		model.addAttribute("msgContent", "Por favor escribe el número de documento del paciente");
		model.addAttribute("roleType", "ROLE_ADMIN");

		return "consult";
	}

	
	/**
	 * show user information patient function post
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - show user info patient page, or consul page if has errors
	 */
	@RequestMapping(value = "/admins/consulta/paciente", method = RequestMethod.POST)
	public String consultUpdatePatient(@ModelAttribute("userform") User user, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("consultUpdatePatient() : {}", user.getUsername());

		if (patientService.find(user.getUsername())) {

			return "redirect:/admins/" + user.getUsername() + "/paciente";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El paciente no existe");
		return "redirect:/admins/consulta/paciente";
	}

	
	/**
	 * user information patient page
	 * @param ndivalue
	 * @param model
	 * @return - show user information patient page
	 */
	@RequestMapping(value = "/admins/{ndivalue}/paciente", method = RequestMethod.GET)
	public String consultPatient(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("consultPatient() : {}");

		model.addAttribute("patient", patientService.select(ndivalue));
		model.addAttribute("action", "paciente");
		model.addAttribute("roleType", "ROLE_ADMIN");
		return "shows/patient";
	}

	
	/**
	 * update patient form page
	 * @param ndivalue - user patien's ndivalue
	 * @param model
	 * @return - patient form page
	 */
	@RequestMapping(value = "/admins/{ndivalue}/ActualizarPaciente", method = RequestMethod.GET)
	public String showUpdatePatient(@PathVariable("ndivalue") String ndivalue, Model model) {

		logger.debug("showUpdatePatientForm() : {}", ndivalue);
		// logger.debug("showUpdatePatientForm() : {}",
		// patientService.select(ndivalue));

		model.addAttribute("patientForm", patientService.select(ndivalue));
		model.addAttribute("action", "Actualizar");
		model.addAttribute("roleType", "ROLE_ADMIN");
		defaultModelPatient(model);

		return "forms/patient";
	}

	
	/**
	 * update admin form function post
	 * @param patient
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or patient form if has errors
	 */
	@RequestMapping(value = "/admins/ActualizarPaciente", method = RequestMethod.POST)
	public String updatePersonal(@ModelAttribute("patientForm") @Validated Patient patient, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("updatePatient() : {}", patient);

		if (result.hasErrors()) { // if there are errors in adminForm
			model.addAttribute("action", "Actualizar");
			model.addAttribute("roleType", "ROLE_ADMIN");
			defaultModelPatient(model);
			return "forms/patient";

			// if ndivalue will be updated
		} else if (!patientService.selectNdivalueById(patient.getId()).equals(patient.getNdivalue())) {

			// if exist one user with that new ndivalue
			if (userservice.find(patient.getNdivalue())) {
				result.rejectValue("ndivalue", "error.UserExist",
						"Ya existe un paciente con el documento No. " + patient.getNdivalue());
				model.addAttribute("action", "Actualizar");
				model.addAttribute("roleType", "ROLE_ADMIN");
				defaultModelPatient(model);
				return "forms/patient";
			}
		}
		patientService.edit(patient);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Se ha actualizado el paciente!");

		return "redirect:/admins";

	}

	/**
	 * delete patient function get
	 * @param ndivalue
	 * @param model
	 * @param redirectAttributes
	 * @return - index admin page, or consult page if has errors
	 */
	@RequestMapping(value = "/admins/{ndivalue}/EliminarPaciente", method = RequestMethod.GET)
	public String deletePatient(@PathVariable("ndivalue") String ndivalue, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("deletePatient() : {}", ndivalue);

		if (patientService.find(ndivalue)) {

			patientService.delete(ndivalue);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "El paciente fué eliminado!");

			return "redirect:/admins";
		}

		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "El paciente no existe");
		return "redirect:/admins/consulta/paciente";
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

	
	/**
	 * default model patient
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