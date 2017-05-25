package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.Personal;
import com.medical.spring.model.json.JsonDataPersonal;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository("personalDao")
public class PersonalDaoImpl implements PersonalDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#insert(com.medical.spring.model.Personal)
	 */
	@Override
	public void insert(Personal personal) {

		JsonDataPersonal jsonDataPersonal = new JsonDataPersonal(personal);

		String SQL_INSERT = "INSERT INTO personal "
				+ "(id, identifier, name, gender, birthDate, practitionerRole, telecom, address) " + "values " + "('"
				+ jsonDataPersonal.getId() + "', '" + jsonDataPersonal.getIdentifier() + "', '"
				+ jsonDataPersonal.getName() + "', '" + jsonDataPersonal.getGender() + "', '"
				+ jsonDataPersonal.getBirthDate() + "', '" + jsonDataPersonal.getPractitionerRole() + "', '"
				+ jsonDataPersonal.getTelecom() + "', '" + jsonDataPersonal.getAddress() + "')";

		try {
			dao.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#edit(com.medical.spring.model.Personal)
	 */
	@Override
	public void edit(Personal personal) {

		JsonDataPersonal jsonDataPersonal = new JsonDataPersonal(personal);

		String SQL_UPDATE = "UPDATE personal SET identifier='" + jsonDataPersonal.getIdentifier() + "', name='"
				+ jsonDataPersonal.getName() + "', gender='" + jsonDataPersonal.getGender() + "', birthDate='"
				+ jsonDataPersonal.getBirthDate() + "', practitionerRole='" + jsonDataPersonal.getPractitionerRole()
				+ "', telecom='" + jsonDataPersonal.getTelecom() + "', address='" + jsonDataPersonal.getAddress()
				+ "' WHERE id=" + jsonDataPersonal.getId() + "";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String ndivalue) {
		String SQL_DELETE = "DELETE FROM personal WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";
		try {
			dao.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#select(java.lang.String)
	 */
	@Override
	public Personal select(String ndivalue) {

		String SQL_SELECT_BY_ID = "SELECT " + "CONVERT(JSON_UNQUOTE(name->'$.name[0].given[0]') USING utf8) AS given,"
				+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].family[0]') USING utf8) AS family,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].display[0]') USING utf8) AS ndi," + "gender,"
				+ "birthDate," + "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[0].value[0]') USING utf8) AS telmobile,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[1].value[0]') USING utf8) AS telwork,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[2].value[0]') USING utf8) AS email,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].line[0]') USING utf8) AS line,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].city[0]') USING utf8) AS city,"
				+ "CONVERT(JSON_UNQUOTE(practitionerRole->'$.practitionerRole[0].managingOrganization[0]') USING utf8) AS managingOrganization,"
				+ "CONVERT(JSON_UNQUOTE(practitionerRole->'$.practitionerRole[0].role[0].coding[0].code[0]') USING utf8) AS role,"
				+ "id " + " FROM personal WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";

		Personal personal = new Personal();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SQL_SELECT_BY_ID).executeQuery();
			while (resultset.next()) {
				personal.setGiven(resultset.getString("given"));
				personal.setFamily(resultset.getString("family"));
				personal.setNdi(resultset.getString("ndi"));
				personal.setNdivalue("" + ndivalue);
				personal.setBirthDate(resultset.getString("birthDate"));
				personal.setTelmobile(resultset.getString("telmobile"));
				personal.setTelwork(resultset.getString("telwork"));
				personal.setEmail(resultset.getString("email"));
				personal.setLine(resultset.getString("line"));
				personal.setCity(resultset.getString("city"));
				personal.setManagingOrganization(resultset.getString("managingOrganization"));
				personal.setId(resultset.getInt("id"));

				// gender to spanish
				switch (resultset.getString("gender")) {
				case "male":
					personal.setGender("Hombre");
					break;
				case "female":
					personal.setGender("Mujer");
					break;
				case "other":
					personal.setGender("Otro");
					break;
				}

				// role to spanish
				switch (resultset.getString("role")) {
				case "61894003":
					personal.setRole("Médico(a) Endocrinólogo(a)");
					break;
				case "59058001":
					personal.setRole("Médico(a) General");
					break;
				case "398130009":
					personal.setRole("Estudiante de medicina");
					break;
				case "309446002":
					personal.setRole("Enfermero(a) Jefe");
					break;
				case "310182000":
					personal.setRole("Enfermero(a)");
					break;
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personal;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#selectAll()
	 */
	@Override
	public List<Personal> selectAll() {
		String SQL_SELECT_ALL = "SELECT " + "CONVERT(JSON_UNQUOTE(name->'$.name[0].given[0]') USING utf8) AS given,"
				+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].family[0]') USING utf8) AS family,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].display[0]') USING utf8) AS ndi,"
				+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndiValue," + "gender,"
				+ "birthDate," + "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[0].value[0]') USING utf8) AS telmobile,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[1].value[0]') USING utf8) AS telwork,"
				+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[2].value[0]') USING utf8) AS email,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].line[0]') USING utf8) AS line,"
				+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].city[0]') USING utf8) AS city,"
				+ "CONVERT(JSON_UNQUOTE(practitionerRole->'$.practitionerRole[0].managingOrganization[0]') USING utf8) AS managingOrganization,"
				+ "CONVERT(JSON_UNQUOTE(practitionerRole->'$.practitionerRole[0].role[0].coding[0].display[0]') USING utf8) AS role,"
				+ "id " + " FROM personal order by id";

		List<Personal> list = new ArrayList<Personal>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SQL_SELECT_ALL).executeQuery();
			while (resultset.next()) {
				Personal personal = new Personal();

				personal.setGiven(resultset.getString("given"));
				personal.setFamily(resultset.getString("family"));
				personal.setNdi(resultset.getString("ndi"));
				personal.setNdivalue(resultset.getString("ndiValue"));
				personal.setBirthDate(resultset.getString("birthDate"));
				personal.setTelmobile(resultset.getString("telmobile"));
				personal.setTelwork(resultset.getString("telwork"));
				personal.setEmail(resultset.getString("email"));
				personal.setLine(resultset.getString("line"));
				personal.setCity(resultset.getString("city"));
				personal.setManagingOrganization(resultset.getString("managingOrganization"));

				personal.setId(resultset.getInt("id"));

				// gender to spanish
				switch (resultset.getString("gender")) {
				case "male":
					personal.setGender("Hombre");
					break;
				case "female":
					personal.setGender("Mujer");
					break;
				case "other":
					personal.setGender("Otro");
					break;
				}

				// role to spanish
				switch (resultset.getString("role")) {
				case "61894003":
					personal.setRole("Médico(a) Endocrinólogo(a)");
					break;
				case "59058001":
					personal.setRole("Médico(a) General");
					break;
				case "398130009":
					personal.setRole("Estudiante de medicina");
					break;
				case "309446002":
					personal.setRole("Enfermero(a) Jefe");
					break;
				case "310182000":
					personal.setRole("Enfermero(a)");
					break;
				}

				list.add(personal);
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String ndivalue) {
		String SELECT = "SELECT CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndiValue FROM personal where identifier->'$.identifier[0].value'='"
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
	 * @see com.medical.spring.dao.PersonalDao#editNdivalue(java.lang.String, java.lang.String)
	 */
	@Override
	public void editNdivalue(String oldNdivalue, String newNdivalue) {

		String SQL_UPDATE = "UPDATE personal SET ndivalue = '" + newNdivalue + "'  WHERE ndivalue = '" + oldNdivalue
				+ "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.PersonalDao#selectNdivalueById(int)
	 */
	@Override
	public String selectNdivalueById(int id) {
		String SELECT = "SELECT CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value[0]') USING utf8) AS ndivalue FROM personal WHERE id ="
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
