package com.spring.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.medical.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	DAO dao;

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM role WHERE id= " + id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Set<Role> selectAll() {
		String sql = "SELECT * FROM role";

		Set<Role> roles = new HashSet<Role>(0);
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();

			while (resulset.next()) {
				Role role = getResulset(resulset);
				roles.add(role);
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public Set<Role> selectByUser(Integer id) {
		String sql = "SELECT role.id, role.type FROM role INNER JOIN roles ON roles.role_id = role.id WHERE roles.user_id = "
				+ id;

		Set<Role> roles = new HashSet<Role>(0);
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();

			while (resulset.next()) {
				Role role = getResulset(resulset);
				roles.add(role);
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	private Role getResulset(ResultSet resulset) throws SQLException {
		Role role = new Role();
		role.setId(resulset.getInt("id"));
		role.setType(resulset.getString("type"));
		return role;
	}

	@Override
	public void removeUserRole(Integer user_id, Integer role_id) {
		String sql = "DELETE FROM roles WHERE user_id=" + user_id + " AND role_id=" + role_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setUserRole(Integer user_id, Integer role_id) {
		String sql = "INSERT INTO roles SET user_id=" + user_id + ", role_id=" + role_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Role select(Integer id) {
		String sql = "SELECT * FROM role WHERE id=" + id;

		Role role = new Role();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();

			while (resulset.next()) {
				role = getResulset(resulset);
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public Integer countAdministrators() {
		String sql = "SELECT COUNT(*) AS count FROM roles WHERE role_id = 1";
		Integer count = null;
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();

			while (resulset.next()) {
				count = resulset.getInt("count");
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
