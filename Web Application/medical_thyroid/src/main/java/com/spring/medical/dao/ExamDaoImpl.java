package com.spring.medical.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.medical.json.JsonExam;
import com.spring.medical.model.Exam;

@Repository("examDao")
public class ExamDaoImpl implements ExamDao {

	@Autowired
	DAO dao;

	@Autowired
	OrganizationDao organizationDao;

	/**
	 * Data directly extracted in STRING from the JSON database
	 */
	String SQL_SELECT = "SELECT " + " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].code') USING utf8) AS code, "
			+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].display') USING utf8) AS displayCode, "
			+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].system') USING utf8) AS systemCode, "
			+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.value') USING utf8) AS valueQuantity,"
			+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].low[0].value') USING utf8) AS low, "
			+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].unit') USING utf8) AS unit, "
			+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].value') USING utf8) AS high, "
			+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.display') USING utf8) AS displaySubject, " + " labComments, "
			+ " performerComments, " + " issued, " + " done, "
			+ " CONVERT(JSON_UNQUOTE(interpretation->'$.interpretation.coding[0].code') USING utf8) AS interpretation, "
			+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.reference') USING utf8) AS referenceSubject, "
			+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].display') USING utf8) AS displayPerformer, "
			+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].reference') USING utf8) AS referencePerformer, "
			+ " id ";

	@Override
	public void insert(Exam exam) {
		JsonExam jsonExam = new JsonExam(exam);

		String sql = "INSERT INTO exam SET " + sqlSentence(jsonExam);

		System.out.println(sql);
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Exam exam) {
		JsonExam jsonExam = new JsonExam(exam);

		String sql = "UPDATE exam SET " + sqlSentence(jsonExam) + " WHERE id = " + exam.getOrder();

		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Exam> selectAll() {
		String sql = SQL_SELECT + " FROM exam ORDER BY id DESC";
		List<Exam> list = new ArrayList<Exam>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {

				Exam exam = getResulset(resulset);

				list.add(exam);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @param resulset
	 * @return User extracted from resultset
	 * @throws SQLException
	 */
	private Exam getResulset(ResultSet resulset) throws SQLException {

		Exam exam = new Exam();

		exam.setOrder(resulset.getInt("id"));
		exam.setCode(resulset.getString("code"));
		exam.setSystemCode(resulset.getString("systemCode"));
		exam.setDisplayCode(resulset.getString("displayCode"));
		exam.setReferenceSubject(resulset.getString("referenceSubject"));
		exam.setDisplaySubject(resulset.getString("displaySubject"));
		exam.setValue((resulset.getString("valueQuantity")));
		exam.setHigh(resulset.getString("high"));
		exam.setLow(resulset.getString("low"));
		exam.setUnit(resulset.getString("unit"));
		exam.setLabComments(resulset.getString("labComments"));
		exam.setPerformerComments(resulset.getString("performerComments"));
		exam.setIssued(resulset.getString("issued"));
		exam.setReferencePerformer(resulset.getString("referencePerformer"));
		exam.setDisplayPerformer(resulset.getString("displayPerformer"));
		exam.setDone(resulset.getByte("done"));
		exam.setInterpretation(resulset.getString("interpretation"));

		return exam;
	}

	private String sqlSentence(JsonExam jsonExam) {
		String sql = "";

		if (jsonExam.getOrder() != null)
			sql += "id=" + jsonExam.getOrder() + ", ";

		if (jsonExam.getCode() != null)
			sql += "code='" + jsonExam.getCode() + "', ";

		if (jsonExam.getSubject() != null)
			sql += "subject='" + jsonExam.getSubject() + "', ";

		if (jsonExam.getPerformer() != null)
			sql += "performer='" + jsonExam.getPerformer() + "', ";

		if (jsonExam.getReferenceRange() != null)
			sql += "referenceRange='" + jsonExam.getReferenceRange() + "', ";

		if (jsonExam.getInterpretation() != null)
			sql += "interpretation='" + jsonExam.getInterpretation() + "', ";

		if (jsonExam.getValueQuantity() != null)
			sql += "valueQuantity='" + jsonExam.getValueQuantity() + "', ";

		if (jsonExam.getLabComments() != null)
			sql += "labComments='" + jsonExam.getLabComments() + "', ";

		if (jsonExam.getPerformerComments() != null)
			sql += "performerComments='" + jsonExam.getPerformerComments() + "', ";

		if (jsonExam.getIssued() != null)
			sql += "issued='" + jsonExam.getIssued() + "', ";

		if (jsonExam.getDone() == (byte) 1)
			sql += "done=" + jsonExam.getDone();

		if (sql != null && sql.length() > 0 && sql.charAt(sql.length() - 2) == ',') {
			sql = sql.substring(0, sql.length() - 2);
			System.out.println("entro  " + sql);
		}

		return sql;
	}

	@Override
	public Exam selectByOrder(Integer order) {
		String sql = SQL_SELECT + "FROM exam WHERE id = " + order;

		Exam exam = new Exam();
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				exam = getResulset(resulset);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exam;
	}

	@Override
	public void removeUserExam(Integer exam_id) {
		String sql = "DELETE FROM exams WHERE exam_id=" + exam_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setUserExam(Integer user_id, Integer exam_id) {
		String sql = "INSERT INTO exams SET user_id=" + user_id + ", exam_id=" + exam_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Integer selectIdByIssued(String issued) {
		String sql = "SELECT id FROM exam WHERE issued = '" + issued + "'";

		Integer id = null;
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				id = resulset.getInt("id");
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public Set<Exam> selectByUser(Integer id) {

		String sql = SQL_SELECT
				+ " FROM exams AS parent LEFT JOIN exam AS child ON child.id = parent.exam_id WHERE parent.user_id = "
				+ id + " ORDER BY child.id DESC";
		Set<Exam> list = new HashSet<>(0);
		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {
				Exam exam = getResulset(resulset);
				list.add(exam);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteExam(Integer exam_id) {
		String sql = "DELETE FROM exam WHERE id=" + exam_id;
		try {
			dao.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Exam> selectNotDone() {
		String sql = SQL_SELECT + " FROM exam WHERE done = 0 ORDER BY id DESC";
		List<Exam> list = new ArrayList<Exam>();

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
			while (resulset.next()) {

				Exam exam = getResulset(resulset);

				list.add(exam);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
