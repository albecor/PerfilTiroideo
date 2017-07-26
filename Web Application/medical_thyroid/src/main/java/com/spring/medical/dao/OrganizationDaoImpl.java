package com.spring.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.medical.model.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	DAO dao;

	@Override
	public void insert(Organization organization) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Organization organization) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Organization> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization selectById(Integer id) {
		String sql = " SELECT * FROM organization WHERE id = " + id;

		Organization organization = null;
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				organization = getResulset(resulset);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return organization;
	}

	private Organization getResulset(ResultSet resulset) throws SQLException {

		Organization organization = new Organization();
		organization.setAddress(resulset.getString("address"));
		organization.setEmail(resulset.getString("email"));
		organization.setName(resulset.getString("name"));
		organization.setPhone(resulset.getString("phone"));
		organization.setId(resulset.getInt("id"));

		return organization;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.spring.medical.dao.OrganizationDao#find(com.spring.medical.model.
	 * Organization)
	 */
	@Override
	public void find(Organization organization) {
		// TODO Auto-generated method stub

	}

}
