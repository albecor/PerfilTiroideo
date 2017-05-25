
package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.Patient;
import com.medical.spring.model.json.JsonDataPatient;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository("patientDao")
public class PatientDaoImpl implements PatientDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#insert(com.medical.spring.model.Patient)
	 */
	@Override
	public void insert(Patient patient) {

		JsonDataPatient jsonDataPatient = new JsonDataPatient(patient);

		String SQL_INSERT = "INSERT INTO patient "
				+ "(id,identifier,name,telecom,gender,birthDate,bloodType,address,maritalStatus,contact,communication,managingOrganization)"
				+ "VALUES ('" + jsonDataPatient.getId() + "','" + jsonDataPatient.getIdentifier() + "','"
				+ jsonDataPatient.getName() + "','" + jsonDataPatient.getTelecom() + "','" + jsonDataPatient.getGender()
				+ "','" + jsonDataPatient.getBirthDate() + "','" + jsonDataPatient.getBloodType() + "','"
				+ jsonDataPatient.getAddress() + "','" + jsonDataPatient.getMaritalStatus() + "','"
				+ jsonDataPatient.getContact() + "','" + jsonDataPatient.getCommunication() + "','"
				+ jsonDataPatient.getManagingOrganization() + "')";

		try {
			dao.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#edit(com.medical.spring.model.Patient)
	 */
	@Override
	public void edit(Patient patient) {

		JsonDataPatient jsonDataPatient = new JsonDataPatient(patient);

		String SQL_UPDATE = "UPDATE patient SET identifier='" + jsonDataPatient.getIdentifier() + "', " + "name='"
				+ jsonDataPatient.getName() + "', " + "managingOrganization='"
				+ jsonDataPatient.getManagingOrganization() + "', " + "gender='" + jsonDataPatient.getGender() + "', "
				+ "communication='" + jsonDataPatient.getCommunication() + "', " + "contact='"
				+ jsonDataPatient.getContact() + "', " + "bloodType='" + jsonDataPatient.getBloodType() + "', "
				+ "birthDate='" + jsonDataPatient.getBirthDate() + "', " + "maritalStatus='"
				+ jsonDataPatient.getMaritalStatus() + "',  " + "telecom='" + jsonDataPatient.getTelecom() + "', "
				+ "address='" + jsonDataPatient.getAddress() + "' " + "WHERE id=" + jsonDataPatient.getId() + "";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String ndivalue) {
		String SQL_DELETE = "DELETE FROM patient WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";
		try {
			dao.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#select(java.lang.String)
	 */
	@Override
	public Patient select(String ndivalue) {
		String SQL_SELECT_BY_ID = "SELECT " + "CONVERT(JSON_UNQUOTE(name->'$.name[0].given[0]') USING utf8) AS given,"
				+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].family[0]') USING utf8) AS family,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].display[0]') USING utf8) AS ndi," + "gender,"
				+ "birthDate,"
				+ "CONVERT(JSON_UNQUOTE(maritalStatus->'$.maritalStatus[0].coding[0].code[0]') USING utf8) AS maritalStatus,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[0].value[0]') USING utf8) AS telmobile,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[1].value[0]') USING utf8) AS telhome,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[2].value[0]') USING utf8) AS telwork,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[3].value[0]') USING utf8) AS email,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].line[0]') USING utf8) AS line,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].city[0]') USING utf8) AS city,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].given[0]') USING utf8) AS givenc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].family[0]') USING utf8) AS familyc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].telecom[0].value[0]') USING utf8) AS telc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].relationship[0].coding[0].code[0]') USING utf8) AS relationship,"
				+ "managingOrganization," + "bloodType," + "id "
				+ "FROM patient WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";

		Patient patient = new Patient();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_BY_ID).executeQuery();
			while (resulset.next()) {
				patient.setGiven(resulset.getString("given"));
				patient.setFamily(resulset.getString("family"));
				patient.setNdi(resulset.getString("ndi"));
				patient.setNdivalue("" + ndivalue);
				patient.setBirthDate(resulset.getString("birthDate"));
				patient.setTelmobile(resulset.getString("telmobile"));
				patient.setTelhome(resulset.getString("telhome"));
				patient.setTelwork(resulset.getString("telwork"));
				patient.setEmail(resulset.getString("email"));
				patient.setLine(resulset.getString("line"));
				patient.setCity(resulset.getString("city"));
				patient.setGivenc(resulset.getString("givenc"));
				patient.setFamilyc(resulset.getString("familyc"));
				patient.setTelc(resulset.getString("telc"));
				patient.setRelationship(resulset.getString("relationship"));
				patient.setManagingOrganization(resulset.getString("managingOrganization"));
				patient.setBlood(resulset.getString("bloodType"));
				patient.setId(resulset.getInt("id"));

				// gender to spanish
				switch (resulset.getString("gender")) {
				case "male":
					patient.setGender("Hombre");
					break;
				case "female":
					patient.setGender("Mujer");
					break;
				case "other":
					patient.setGender("Otro");
					break;
				}

				// relationship to spanish
				switch (resulset.getString("maritalStatus")) {
				case "M":
					patient.setMaritalStatus("Casado(a)");
					break;
				case "U":
					patient.setMaritalStatus("Soltero(a)");
					break;
				case "T":
					patient.setMaritalStatus("Unión libre");
					break;
				case "S":
					patient.setMaritalStatus("Separado(a)");
					break;
				case "W":
					patient.setMaritalStatus("Viudo(a)");
					break;
				}

				// relationship to spanish
				switch (resulset.getString("relationship")) {
				case "family":
					patient.setRelationship("Familia");
					break;
				case "partner":
					patient.setRelationship("Pareja");
					break;
				case "friend":
					patient.setRelationship("Amigo(a)");
					break;
				}
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#selectAll()
	 */
	@Override
	public List<Patient> selectAll() {

		String SQL_SELECT_ALL = "SELECT " + "CONVERT(JSON_UNQUOTE(name->'$.name[0].given[0]') USING utf8) AS given,"
				+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].family[0]') USING utf8) AS family,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].display[0]') USING utf8) AS ndi,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndiValue," + "gender,"
				+ "birthDate,"
				+ "CONVERT(JSON_UNQUOTE(maritalStatus->'$.maritalStatus[0].coding[0].code[0]') USING utf8) AS maritalStatus,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[0].value[0]') USING utf8) AS telmobile,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[1].value[0]') USING utf8) AS telhome,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[2].value[0]') USING utf8) AS telwork,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[3].value[0]') USING utf8) AS email,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].line[0]') USING utf8) AS line,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].city[0]') USING utf8) AS city,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].given[0]') USING utf8) AS givenc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].family[0]') USING utf8) AS familyc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].telecom[0].value[0]') USING utf8) AS telc,"
				+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].relationship[0].coding[0].code[0]') USING utf8) AS relationship,"
				+ "managingOrganization," + "bloodType," + "id " + "FROM patient order by id";

		List<Patient> list = new ArrayList<Patient>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_ALL).executeQuery();
			while (resulset.next()) {
				Patient patient = new Patient();
				patient.setGiven(resulset.getString("given"));
				patient.setFamily(resulset.getString("family"));
				patient.setNdi(resulset.getString("ndi"));
				patient.setNdivalue(resulset.getString("ndiValue"));
				patient.setBirthDate(resulset.getString("birthDate"));
				patient.setTelmobile(resulset.getString("telmobile"));
				patient.setTelhome(resulset.getString("telhome"));
				patient.setTelwork(resulset.getString("telwork"));
				patient.setEmail(resulset.getString("email"));
				patient.setLine(resulset.getString("line"));
				patient.setCity(resulset.getString("city"));
				patient.setGivenc(resulset.getString("givenc"));
				patient.setFamilyc(resulset.getString("familyc"));
				patient.setTelc(resulset.getString("telc"));
				patient.setManagingOrganization(resulset.getString("managingOrganization"));
				patient.setBlood(resulset.getString("bloodType"));
				patient.setId(resulset.getInt("id"));

				// gender to spanish
				switch (resulset.getString("gender")) {
				case "male":
					patient.setGender("Hombre");
					break;
				case "female":
					patient.setGender("Mujer");
					break;
				case "other":
					patient.setGender("Otro");
					break;
				}

				// relationship to spanish
				switch (resulset.getString("maritalStatus")) {
				case "M":
					patient.setRelationship("Casado(a)");
					break;
				case "U":
					patient.setRelationship("Soltero(a)");
					break;
				case "T":
					patient.setRelationship("Unión libre");
					break;
				case "S":
					patient.setRelationship("Separado(a)");
					break;
				case "W":
					patient.setRelationship("Viudo(a)");
					break;
				}

				// relationship to spanish
				switch (resulset.getString("relationship")) {
				case "family":
					patient.setRelationship("Familia");
					break;
				case "partner":
					patient.setRelationship("Pareja");
					break;
				case "friend":
					patient.setRelationship("Amigo(a)");
					break;
				}

				list.add(patient);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String ndivalue) {
		String SELECT = "SELECT CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndiValue FROM patient WHERE identifier->'$.identifier[0].value'='"
				+ ndivalue + "'";

		boolean find = false;

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SELECT).executeQuery();
			while (resultset.next()) {

				if (resultset.getString("ndiValue") != null) {
					find = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return find;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#editNdivalue(java.lang.String, java.lang.String)
	 */
	@Override
	public void editNdivalue(String oldNdivalue, String newNdivalue) {

		String SQL_UPDATE = "UPDATE patient SET ndivalue = '" + newNdivalue + "'  WHERE ndivalue = '" + oldNdivalue
				+ "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PatientDao#selectNdivalueById(int)
	 */
	@Override
	public String selectNdivalueById(int id) {
		String SELECT = "SELECT CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndivalue FROM patient WHERE id ="
				+ id;

		String ndivalue = "";

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SELECT).executeQuery();
			while (resultset.next()) {

				ndivalue = resultset.getString("ndivalue");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ndivalue;
	}
}
