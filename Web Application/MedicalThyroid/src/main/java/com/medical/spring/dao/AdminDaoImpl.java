package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.Admin;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository
@Qualifier("adminDao")
public class AdminDaoImpl implements AdminDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#insert(com.medical.spring.model.Admin)
	 */
	@Override
	public void insert(Admin admin) {

		String SQL_INSERT = "INSERT INTO admin (ndi, ndivalue, name, phone, email, address) values ('" + admin.getNdi()
				+ "', '" + admin.getNdivalue() + "', '" + admin.getName() + "', '" + admin.getPhone() + "', '"
				+ admin.getEmail() + "', '" + admin.getAddress() + "') ";

		try {
			dao.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#edit(com.medical.spring.model.Admin)
	 */
	@Override
	public void edit(Admin admin) {

		String SQL_UPDATE = "UPDATE admin SET id = " + admin.getId() + ", ndi = '" + admin.getNdi() + "', ndivalue = '"
				+ admin.getNdivalue() + "', name = '" + admin.getName() + "', phone = '" + admin.getPhone()
				+ "', email = '" + admin.getEmail() + "', address = '" + admin.getAddress() + "'  WHERE id = '"
				+ admin.getId() + "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String ndivalue) {
		String SQL_DELETE = "DELETE FROM admin WHERE ndivalue = '" + ndivalue + "'";
		try {
			dao.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#select(java.lang.String)
	 */
	@Override
	public Admin select(String ndivalue) {

		String SQL_SELECT = "SELECT id,ndi,ndivalue,name,phone,email,address FROM admin WHERE ndivalue = '" + ndivalue + "'";
		Admin admin = new Admin();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT).executeQuery();
			while (resulset.next()) {				
				admin.setId(resulset.getInt("id"));
				admin.setNdi(resulset.getString("ndi"));
				admin.setNdivalue(resulset.getString("ndivalue"));
				admin.setName(resulset.getString("name"));
				admin.setPhone(resulset.getString("phone"));
				admin.setEmail(resulset.getString("email"));
				admin.setAddress(resulset.getString("address"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#selectAll()
	 */
	@Override
	public List<Admin> selectAll() {

		String SQL_SELECT_ALL = "SELECT * FROM admin";
		List<Admin> admins = new ArrayList<Admin>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_ALL).executeQuery();
			while (resulset.next()) {
				Admin admin = new Admin();
				admin.setId(resulset.getInt("id"));
				admin.setNdi(resulset.getString("ndi"));
				admin.setNdivalue(resulset.getString("ndivalue"));
				admin.setName(resulset.getString("name"));
				admin.setPhone(resulset.getString("phone"));
				admin.setEmail(resulset.getString("email"));
				admin.setAddress(resulset.getString("address"));
				admins.add(admin);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#selectById(int)
	 */
	@Override
	public Admin selectById(int id) {

		String SQL_SELECT_BY_ID = "SELECT * FROM admin WHERE id = " + id;

		Admin admin = new Admin();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(SQL_SELECT_BY_ID).executeQuery();
			while (resulset.next()) {
				admin.setId(resulset.getInt("id"));
				admin.setNdi(resulset.getString("ndi"));
				admin.setNdivalue(resulset.getString("ndivalue"));
				admin.setName(resulset.getString("name"));
				admin.setPhone(resulset.getString("phone"));
				admin.setEmail(resulset.getString("email"));
				admin.setAddress(resulset.getString("address"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String ndivalue) {
		String SELECT = "SELECT ndivalue FROM admin where ndivalue = '" + ndivalue + "'";

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
	 * @see com.medical.spring.dao.AdminDao#findById(int)
	 */
	@Override
	public boolean findById(int id) {
		String SELECT = "SELECT id FROM admin where id = '" + id + "'";

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
	 * @see com.medical.spring.dao.AdminDao#editNdivalue(java.lang.String, java.lang.String)
	 */
	@Override
	public void editNdivalue(String oldNdivalue, String newNdivalue) {

		String SQL_UPDATE = "UPDATE admin SET ndivalue = '" + newNdivalue + "'  WHERE ndivalue = '" + oldNdivalue + "'";

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.AdminDao#selectNdivalueById(int)
	 */
	@Override
	public String selectNdivalueById(int id) {
		String SELECT = "SELECT ndivalue FROM admin WHERE id =" + id;

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
