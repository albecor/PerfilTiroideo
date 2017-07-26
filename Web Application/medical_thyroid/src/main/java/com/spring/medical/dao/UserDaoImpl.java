package com.spring.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.medical.json.JsonUser;
import com.spring.medical.model.PasswordResetToken;
import com.spring.medical.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	DAO dao;

	String SQL_SELECT = "SELECT " + "birthdate," + "bloodtype,"
			+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].city[0]') USING utf8) AS city,"
			+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[3].value') USING utf8) AS email,"
			+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].family[0]') USING utf8) AS family,"
			+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].family[0]') USING utf8) AS familyc," + "gender,"
			+ "CONVERT(JSON_UNQUOTE(name->'$.name[0].given[0]') USING utf8) AS given,"
			+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].name[0].given[0]') USING utf8) AS givenc," + "id,"
			+ "CONVERT(JSON_UNQUOTE(address->'$.address[0].line[0]') USING utf8) AS line," + "managingOrganization,"
			+ "CONVERT(JSON_UNQUOTE(maritalStatus->'$.maritalStatus[0].coding[0].code') USING utf8) AS maritalStatus,"
			+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].display') USING utf8) AS ndi,"
			+ "CONVERT(JSON_UNQUOTE(identifier->'$.identifier[0].value') USING utf8) AS ndivalue," + "password,"
			+ "CONVERT(JSON_UNQUOTE(practitionerRole->'$.practitionerRole[0].role[0].coding[0].code') USING utf8) AS practitionerRole,"
			+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].relationship[0].coding[0].code') USING utf8) AS relationship,"
			+ "CONVERT(JSON_UNQUOTE(contact->'$.contact[0].telecom[0].value') USING utf8) AS telc,"
			+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[1].value') USING utf8) AS telhome,"
			+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[0].value') USING utf8) AS telmobile,"
			+ "CONVERT(JSON_UNQUOTE(telecom->'$.telecom[2].value') USING utf8) AS telwork " + "FROM user ";

	@Override
	public User selectByNdivalue(String ndivalue) {
		User user = new User();

		String sql = SQL_SELECT + "WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				user = getResulset(resulset);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User selectById(Integer id) {
		String sql = SQL_SELECT + "WHERE id=" + id;

		User user = new User();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				user = getResulset(resulset);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void insert(User user) {
		JsonUser jsonUser = new JsonUser(user);

		String sql = "INSERT INTO user SET " + SqlSentence(jsonUser);

		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		JsonUser jsonUser = new JsonUser(user);

		String sql = "UPDATE user SET " + SqlSentence(jsonUser) + " WHERE id=" + user.getId();

		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(User user) {
		String sql = "DELETE FROM user WHERE id= " + user.getId();
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean find(String ndivalue) {

		String SELECT = "SELECT id FROM user WHERE identifier->'$.identifier[0].value'='" + ndivalue + "'";

		boolean find = false;

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SELECT).executeQuery();
			while (resultset.next()) {

				if (resultset != null) {
					find = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return find;

	}

	@Override
	public List<User> selectAll() {
		String sql = SQL_SELECT + " ORDER BY id DESC";
		List<User> list = new ArrayList<User>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				User user = getResulset(resulset);
				list.add(user);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private String SqlSentence(JsonUser jsonUser) {

		String sql = "";

		if (jsonUser.getId() != null)
			sql += " id=" + jsonUser.getId() + ", ";

		if (jsonUser.getPassword() != null)
			sql += "password='" + jsonUser.getPassword() + "', ";

		if (jsonUser.getIdentifier() != null)
			sql += "identifier='" + jsonUser.getIdentifier() + "', ";

		if (jsonUser.getName() != null)
			sql += "name='" + jsonUser.getName() + "', ";

		if (jsonUser.getTelecom() != null)
			sql += "telecom='" + jsonUser.getTelecom() + "', ";

		if (jsonUser.getGender() != null)
			sql += "gender='" + jsonUser.getGender() + "', ";

		if (jsonUser.getBirthdate() != null)
			sql += "birthdate='" + jsonUser.getBirthdate() + "', ";

		if (jsonUser.getAddress() != null)
			sql += "address='" + jsonUser.getAddress() + "', ";

		if (jsonUser.getMaritalStatus() != null)
			sql += "maritalStatus='" + jsonUser.getMaritalStatus() + "', ";

		if (jsonUser.getContact() != null)
			sql += "contact='" + jsonUser.getContact() + "', ";

		if (jsonUser.getCommunication() != null)
			sql += "communication='" + jsonUser.getCommunication() + "', ";

		if (jsonUser.getManagingOrganization() != null)
			sql += "managingOrganization='" + jsonUser.getManagingOrganization() + "', ";

		if (jsonUser.getBloodtype() != null)
			sql += "bloodtype='" + jsonUser.getBloodtype() + "', ";

		if (jsonUser.getPractitionerRole() != null)
			sql += "practitionerRole='" + jsonUser.getPractitionerRole() + "'";

		return sql;

	}

	private User getResulset(ResultSet resulset) throws SQLException {
		User user = new User();
		user.setBirthDate(resulset.getString("birthdate"));
		user.setBloodtype(resulset.getString("bloodtype"));
		user.setCity(resulset.getString("city"));
		user.setEmail(resulset.getString("email"));
		user.setFamily(resulset.getString("family"));
		user.setFamilyc(resulset.getString("familyc"));
		user.setGender(resulset.getString("gender"));
		user.setGiven(resulset.getString("given"));
		user.setGivenc(resulset.getString("givenc"));
		user.setId(resulset.getInt("id"));
		user.setLine(resulset.getString("line"));
		user.setManagingOrganization(resulset.getString("managingOrganization"));
		user.setMaritalStatus(resulset.getString("maritalStatus"));
		user.setNdi(resulset.getString("ndi"));
		user.setNdivalue(resulset.getString("ndivalue"));
		user.setPassword(resulset.getString("password"));
		user.setPractitionerRole(resulset.getString("practitionerRole"));
		user.setRelationship(resulset.getString("relationship"));
		user.setTelc(resulset.getString("telc"));
		user.setTelhome(resulset.getString("telhome"));
		user.setTelmobile(resulset.getString("telmobile"));
		user.setTelwork(resulset.getString("telwork"));
		return user;
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String sql = "INSERT INTO password_reset_token SET token='" + token + "', user_id=" + user.getId() + ", date='"
				+ dateFormat.format(cal.getTime()) + "'";

		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PasswordResetToken selectByToken(String token) {
		String SELECT = "SELECT * FROM password_reset_token WHERE token='" + token + "';";

		PasswordResetToken passwordResetToken = new PasswordResetToken();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SELECT).executeQuery();
			while (resultset.next()) {
				passwordResetToken.setId(resultset.getInt("id"));
				passwordResetToken.setExpiryDate(resultset.getTimestamp("date"));
				passwordResetToken.setToken(resultset.getString("token"));
				passwordResetToken.setUser(selectById(resultset.getInt("user_id")));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return passwordResetToken;
	}

	@Override
	public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		String sql = "DELETE FROM password_reset_token WHERE id= " + passwordResetToken.getId();
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updatePassword(User user) {
		String sql = "UPDATE user SET password='" + user.getPassword() + "' WHERE id=" + user.getId();

		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePasswordResetTokenByUser(Integer user_id) {
		String sql = "DELETE FROM password_reset_token WHERE user_id= " + user_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteExpiredPasswordResetToken() {
		// Remove token if 24 hours have passed
		String sql = "SET SQL_SAFE_UPDATES = 0; DELETE FROM password_reset_token where date <= NOW(); SET SQL_SAFE_UPDATES = 1;";
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
