package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.Lab;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository("labDao")
public class LabDaoImpl implements LabDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#insert(com.medical.spring.model.Lab)
	 */
	@Override
	public void insert(Lab lab) {

		String SQL_INSERT = "INSERT INTO lab (ndi, ndivalue, name, phone, email, address, entitylab) values ('"
				+ lab.getNdi() + "', '" + lab.getNdivalue() + "', '" + lab.getName() + "', '" + lab.getPhone() + "', '"
				+ lab.getEmail() + "', '" + lab.getAddress() + "', '" + lab.getEntitylab() + "') ";

		try {
			dao.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#edit(com.medical.spring.model.Lab)
	 */
	@Override
	public void edit(Lab lab) {

		String SQL_UPDATE = "UPDATE lab SET id = " + lab.getId() + ", ndi = '" + lab.getNdi() + "', ndivalue = '"
				+ lab.getNdivalue() + "', name = '" + lab.getName() + "', phone = '" + lab.getPhone() + "', email = '"
				+ lab.getEmail() + "', address = '" + lab.getAddress() + "', entitylab = '" + lab.getEntitylab()
				+ "'  WHERE id = '" + lab.getId() + "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String ndivalue) {
		String SQL_DELETE = "DELETE FROM lab WHERE ndivalue = '" + ndivalue + "'";
		try {
			dao.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#select(java.lang.String)
	 */
	@Override
	public Lab select(String ndivalue) {

		String SQL_SELECT = "SELECT * FROM lab WHERE ndivalue = '" + ndivalue + "'";
		Lab lab = new Lab();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT).executeQuery();
			while (resulset.next()) {
				lab.setId(resulset.getInt("id"));
				lab.setNdi(resulset.getString("ndi"));
				lab.setNdivalue(resulset.getString("ndivalue"));
				lab.setName(resulset.getString("name"));
				lab.setPhone(resulset.getString("phone"));
				lab.setEmail(resulset.getString("email"));
				lab.setAddress(resulset.getString("address"));
				lab.setEntitylab(resulset.getString("entitylab"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lab;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#selectAll()
	 */
	@Override
	public List<Lab> selectAll() {

		String SQL_SELECT_ALL = "SELECT * FROM lab";
		List<Lab> labs = new ArrayList<Lab>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_ALL).executeQuery();
			while (resulset.next()) {
				Lab lab = new Lab();
				lab.setId(resulset.getInt("id"));
				lab.setNdi(resulset.getString("ndi"));
				lab.setNdivalue(resulset.getString("ndivalue"));
				lab.setName(resulset.getString("name"));
				lab.setPhone(resulset.getString("phone"));
				lab.setEmail(resulset.getString("email"));
				lab.setAddress(resulset.getString("address"));
				labs.add(lab);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return labs;

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#selectById(int)
	 */
	@Override
	public Lab selectById(int id) {

		String SQL_SELECT_BY_ID = "SELECT * FROM lab WHERE id = " + id;

		Lab lab = new Lab();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_BY_ID).executeQuery();
			while (resulset.next()) {
				lab.setId(resulset.getInt("id"));
				lab.setNdi(resulset.getString("ndi"));
				lab.setNdivalue(resulset.getString("ndivalue"));
				lab.setName(resulset.getString("name"));
				lab.setPhone(resulset.getString("phone"));
				lab.setEmail(resulset.getString("email"));
				lab.setAddress(resulset.getString("address"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lab;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String ndivalue) {
		String SELECT = "SELECT ndivalue FROM lab where ndivalue = '" + ndivalue + "'";

		boolean find = false;

		try {

			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SELECT).executeQuery();
			while (resulset.next()) {

				if (resulset.getString("ndivalue") != null) {
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
	 * @see com.medical.spring.dao.LabDao#findById(int)
	 */
	@Override
	public boolean findById(int id) {
		String SELECT = "SELECT id FROM lab where id = '" + id + "'";

		boolean find = false;

		try {

			Connection connection = dao.getDatasource().getConnection();
			System.out.println(dao.getDatasource().getUrl());
			ResultSet resulset = connection.prepareStatement(SELECT).executeQuery();
			while (resulset.next()) {

				if (resulset.getString("id") != null) {
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
	 * @see com.medical.spring.dao.LabDao#editNdivalue(java.lang.String, java.lang.String)
	 */
	@Override
	public void editNdivalue(String oldNdivalue, String newNdivalue) {

		String SQL_UPDATE = "UPDATE lab SET ndivalue = '" + newNdivalue + "'  WHERE ndivalue = '" + oldNdivalue + "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.LabDao#selectNdivalueById(int)
	 */
	@Override
	public String selectNdivalueById(int id) {
		String SELECT = "SELECT ndivalue FROM lab WHERE id =" + id;

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
