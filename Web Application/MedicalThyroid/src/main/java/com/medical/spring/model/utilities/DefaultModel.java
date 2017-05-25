package com.medical.spring.model.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultModel {

	public Map<String, String> ndiList() {

		Map<String, String> ndi = new LinkedHashMap<String, String>();
		ndi.put("C.C", "Cédula de ciudadania");
		ndi.put("T.I", "Tarjeta de identidad");
		ndi.put("R.C", "Registro Civil");

		return ndi;
	}
	
	public Map<String, String> thyroidProfileList() {

		Map<String, String> thyroidProfile = new LinkedHashMap<String, String>();
		thyroidProfile.put("3024-7", "T4 LIBRE TIROXINA");
		thyroidProfile.put("3026-2", "T4 TIROXINA");
		thyroidProfile.put("3051-0", "T3 LIBRE TRIYODOTIRONINA");
		thyroidProfile.put("3053-6", "T3 TRIYODOTIRONINA");
		thyroidProfile.put("3016-3", "TIROTROPINA");
		thyroidProfile.put("3013-0", "TIROGLOBULINA");

		return thyroidProfile;
	}

	public Map<String, String> genderList() {

		Map<String, String> gender = new LinkedHashMap<String, String>();
		gender.put("Hombre", "Hombre");
		gender.put("Mujer", "Mujer");
		gender.put("Otro", "Otro");
		return gender;
	}

	public Map<String, String> roleList() {

		Map<String, String> role = new LinkedHashMap<String, String>();
		role.put("Médico(a) Endocrinólogo(a)", "Médico(a) Endocrinólogo(a)");
		role.put("Médico(a) General", "Médico(a) General");
		role.put("Estudiante de medicina", "Estudiante de medicina");
		role.put("Enfermero(a) Jefe", "Enfermero(a) Jefe");
		role.put("Enfermero(a)", "Enfermero(a)");

		return role;
	}

	public Map<String, String> bloodList() {

		Map<String, String> blood = new LinkedHashMap<String, String>();
		blood.put("A+", "A+");
		blood.put("A-", "A-");
		blood.put("B+", "B+");
		blood.put("B-", "B-");
		blood.put("AB+", "AB+");
		blood.put("AB-", "AB-");
		blood.put("O+", "O+");
		blood.put("O-", "O-");

		return blood;
	}

	public Map<String, String> relationshipList() {

		Map<String, String> relationship = new LinkedHashMap<String, String>();
		relationship.put("Familia", "Familia");
		relationship.put("Pareja", "Pareja");
		relationship.put("Amigo(a)", "Amigo(a)");
		return relationship;
	}

	public Map<String, String> maritalStatusList() {

		Map<String, String> maritalStatus = new LinkedHashMap<String, String>();
		maritalStatus.put("Casado(a)", "Casado(a)");
		maritalStatus.put("Soltero(a)", "Soltero(a)");
		maritalStatus.put("Unión libre", "Unión libre");
		maritalStatus.put("Separado(a)", "Separado(a)");
		maritalStatus.put("Viudo(a)", "Viudo(a)");

		return maritalStatus;
	}

}
