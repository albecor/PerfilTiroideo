package com.spring.medical.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Data Access Object, class to handled the comunication between server and DB
 * Here we can found all SQL setences
 *
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class DAO {
	/**
	 * data of database
	 * 
	 * @param datasource
	 * @param driverClassName
	 * @param url
	 * @param database
	 * @param username
	 * @param password
	 */
	public DAO(String driverClassName, String url, String database, String username, String password,
			String scriptDatabase) {
		super();

		this.scriptDatabase = scriptDatabase;
		this.driverClassName = driverClassName;
		this.url = url;
		this.database = database;
		this.username = username;
		this.password = password;
		this.datasource = new DriverManagerDataSource();
		this.datasource.setDriverClassName(driverClassName);
		this.datasource.setUrl(url + database);
		this.datasource.setPassword(password);
		this.datasource.setUsername(username);

	}

	private DriverManagerDataSource datasource;
	private String driverClassName;
	private String url;
	private String database;
	private String username;
	private String password;
	private String scriptDatabase;

	/**
	 * @return the datasource
	 */
	public DriverManagerDataSource getDatasource() {
		return datasource;
	}

	/**
	 * @param datasource
	 *            the datasource to set
	 */
	public void setDatasource(DriverManagerDataSource datasource) {
		this.datasource = datasource;
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName
	 *            the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @param database
	 *            the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * executeUpdate function sql
	 * 
	 * @param sql
	 *            - sql sentence
	 * @throws SQLException
	 */
	public void executeUpdate(String sql) throws SQLException {

		Connection connection = this.datasource.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.executeUpdate();
		connection.close();

	}

	/**
	 * create database in mysql server if not exist
	 */
	public void createDataBase() {

		try {
			DriverManagerDataSource datasource1 = new DriverManagerDataSource();

			datasource1.setDriverClassName(driverClassName);
			datasource1.setUrl(url); // send url whithout database
			datasource1.setPassword(password);
			datasource1.setUsername(username);

			Connection conn;

			conn = datasource1.getConnection();

			ResultSet resultSet = conn.getMetaData().getCatalogs();

			boolean exist = false;
			while (resultSet.next()) {

				if (resultSet.getString(1).equals(this.database)) {
					exist = true;
				}
			}
			resultSet.close();
			conn.close();

			if (!exist) {

				conn = datasource1.getConnection();

				ScriptRunner runner = new ScriptRunner(conn);

				InputStream inputStream = getClass().getResourceAsStream(this.scriptDatabase);
				InputStreamReader reader = new InputStreamReader(inputStream);
				runner.setErrorLogWriter(null);

				runner.runScript(reader);

				reader.close();
				conn.close();
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the scriptDatabase
	 */
	public String getScriptDatabase() {
		return scriptDatabase;
	}

	/**
	 * @param scriptDatabase
	 *            the scriptDatabase to set
	 */
	public void setScriptDatabase(String scriptDatabase) {
		this.scriptDatabase = scriptDatabase;
	}

}
