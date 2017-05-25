package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.Exam;
import com.medical.spring.model.json.JsonDataExam;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository("examDao")
public class ExamDaoImpl implements ExamDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#insert(com.medical.spring.model.Exam)
	 */
	@Override
	public void insert(Exam exam) {

		JsonDataExam jsonDataExam = new JsonDataExam(exam);

		String SQL_INSERT = "INSERT INTO exam (code, subject, referenceRange, interpretation, valueQuantity, comments, issued, entity, performer) values ('"
				+ jsonDataExam.getCode() + "', '" + jsonDataExam.getSubject() + "', '"
				+ jsonDataExam.getReferenceRange() + "', '" + jsonDataExam.getInterpretation() + "', '"
				+ jsonDataExam.getValueQuantity() + "', '" + jsonDataExam.getComments() + "', '"
				+ jsonDataExam.getIssued() + "', '" + jsonDataExam.getEntity() + "', '" + jsonDataExam.getPerformer()
				+ "') ";

		try {
			dao.executeUpdate(SQL_INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#select(java.lang.String, java.lang.String)
	 */
	@Override
	public Exam select(String ndivalue, String order) {

		String sql = "SELECT " + " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].code') USING utf8) AS code, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].display') USING utf8) AS displayCode, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].system') USING utf8) AS systemCode, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.value') USING utf8) AS valueQuantity,"
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].low[0].value') USING utf8) AS low, "
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].value') USING utf8) AS high, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.reference') USING utf8) AS referenceSubject, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.display') USING utf8) AS displaySubject, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.unit') USING utf8) AS unit," + " comments, "
				+ " issued, " + " done, "
				+ " CONVERT(JSON_UNQUOTE(interpretation->'$.interpretation.coding[0].display') USING utf8) AS interpretation, "
				+ " entity, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].display') USING utf8) AS displayPerformer, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].reference') USING utf8) AS referencePerformer, "
				+ " id " + "FROM exam WHERE subject->'$.subject.reference'='" + ndivalue + "' AND id = " + order;

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

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#selectAll(java.lang.String)
	 */
	@Override
	public List<Exam> selectAll(String ndivalue) {

		String sql = "SELECT " + " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].code') USING utf8) AS code, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].display') USING utf8) AS displayCode, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].system') USING utf8) AS systemCode, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.value') USING utf8) AS valueQuantity,"
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].low[0].value') USING utf8) AS low, "
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].value') USING utf8) AS high, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.reference') USING utf8) AS referenceSubject, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.display') USING utf8) AS displaySubject, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.unit') USING utf8) AS unit," + " comments, "
				+ " done, " + " issued, "
				+ " CONVERT(JSON_UNQUOTE(interpretation->'$.interpretation.coding[0].display') USING utf8) AS interpretation, "
				+ " entity, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].display') USING utf8) AS displayPerformer, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].reference') USING utf8) AS referencePerformer, "
				+ " id " + "FROM exam WHERE subject->'$.subject.reference'='" + ndivalue + "' ORDER BY id DESC";

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

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#edit(com.medical.spring.model.Exam)
	 */
	@Override
	public void edit(Exam exam) {

		JsonDataExam jsonDataExam = new JsonDataExam(exam);

		String SQL_UPDATE = "UPDATE exam SET " + "code = '" + jsonDataExam.getCode() + "', subject = '"
				+ jsonDataExam.getSubject() + "', referenceRange = '" + jsonDataExam.getReferenceRange()
				+ "', interpretation = '" + jsonDataExam.getInterpretation() + "', valueQuantity = '"
				+ jsonDataExam.getValueQuantity() + "', comments = '" + jsonDataExam.getComments() + "', issued = '"
				+ jsonDataExam.getIssued() + "', entity = '" + jsonDataExam.getEntity() + "', performer = '"
				+ jsonDataExam.getPerformer() + "', done = " + jsonDataExam.getDone() + "  WHERE id = "
				+ jsonDataExam.getOrder();

		try {
			dao.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public void delete(String ndivalue, String order) {
		String SQL_DELETE = "DELETE FROM exam WHERE subject->'$.subject.reference'='" + ndivalue + "' AND id = "
				+ order;
		try {
			dao.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean find(String ndivalue, String order) {
		String sql = "SELECT id " + "FROM exam WHERE subject->'$.subject.reference'='" + ndivalue + "' AND id = "
				+ order;
		boolean find = false;

		try {

			Connection connection = dao.getDatasource().getConnection();
			ResultSet resulset = connection.prepareStatement(sql).executeQuery();
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
	 * @see com.medical.spring.dao.ExamDao#selectForLab()
	 */
	@Override
	public List<Exam> selectForLab() {

		String sql = "SELECT " + " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].code') USING utf8) AS code, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].display') USING utf8) AS displayCode, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].system') USING utf8) AS systemCode, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.value') USING utf8) AS valueQuantity,"
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].low[0].value') USING utf8) AS low, "
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].value') USING utf8) AS high, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.reference') USING utf8) AS referenceSubject, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.display') USING utf8) AS displaySubject, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.unit') USING utf8) AS unit," + " comments, "
				+ " issued, " + " done, "
				+ " CONVERT(JSON_UNQUOTE(interpretation->'$.interpretation.coding[0].display') USING utf8) AS interpretation, "
				+ " entity, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].display') USING utf8) AS displayPerformer, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].reference') USING utf8) AS referencePerformer, "
				+ " id " + "FROM exam WHERE done = 0 ORDER BY id DESC";

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

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.ExamDao#selectByOrder(java.lang.String)
	 */
	@Override
	public Exam selectByOrder(String order) {
		String sql = "SELECT " + " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].code') USING utf8) AS code, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].display') USING utf8) AS displayCode, "
				+ " CONVERT(JSON_UNQUOTE(code->'$.code.coding[0].system') USING utf8) AS systemCode, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.value') USING utf8) AS valueQuantity,"
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].low[0].value') USING utf8) AS low, "
				+ " CONVERT(JSON_UNQUOTE(referenceRange->'$.referenceRange[0].high[0].value') USING utf8) AS high, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.display') USING utf8) AS displaySubject, "
				+ " CONVERT(JSON_UNQUOTE(valueQuantity->'$.valueQuantity.unit') USING utf8) AS unit," + " comments, "
				+ " issued, " + " done, "
				+ " CONVERT(JSON_UNQUOTE(interpretation->'$.interpretation.coding[0].display') USING utf8) AS interpretation, "
				+ " entity, "
				+ " CONVERT(JSON_UNQUOTE(subject->'$.subject.reference') USING utf8) AS referenceSubject, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].display') USING utf8) AS displayPerformer, "
				+ " CONVERT(JSON_UNQUOTE(performer->'$.performer[0].reference') USING utf8) AS referencePerformer, "
				+ " id " + "FROM exam WHERE id = " + order;

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

	/**
	 * @param resulset
	 * @return
	 * @throws SQLException
	 */
	private Exam getResulset(ResultSet resulset) throws SQLException {

		Exam exam = new Exam();

		exam.setCode(resulset.getString("code"));
		exam.setDisplayCode(resulset.getString("displayCode"));
		exam.setSystemCode(resulset.getString("systemCode"));
		exam.setValueQuantity(resulset.getString("valueQuantity"));
		exam.setHigh(resulset.getString("high"));
		exam.setLow(resulset.getString("low"));
		exam.setReferenceSubject(resulset.getString("referenceSubject"));
		exam.setDisplaySubject(resulset.getString("displaySubject"));
		exam.setUnit(resulset.getString("unit"));
		exam.setComments(resulset.getString("comments"));
		exam.setIssued(resulset.getString("issued"));
		exam.setInterpretation(resulset.getString("interpretation"));
		exam.setEntity(resulset.getString("entity"));
		exam.setOrder(resulset.getInt("id"));
		exam.setReferencePerfomer(resulset.getString("referencePerformer"));
		exam.setDisplayPerformer(resulset.getString("displayPerformer"));
		exam.setDone(resulset.getInt("done"));

		if (!(exam.getInterpretation() == null)) {
			switch (exam.getInterpretation()) {
			case "High":
				exam.setInterpretation("Alto");
				break;
			case "Low":
				exam.setInterpretation("Bajo");
				break;
			case "Normal":
				exam.setInterpretation("Normal");
				break;
			}
		}

		switch (exam.getCode()) {
		case "6768-6":
			exam.setDisplayCode("ALP O FAL - FOSFATASA ALCALINA");
			break;
		case "1751-7":
			exam.setDisplayCode("ALBÚMINA");
			break;
		case "1742-6":
			exam.setDisplayCode("ALT - ALANINO AMINOTRANSFERASA");
			break;
		case "1920-8":
			exam.setDisplayCode("AST - ASPARTATO AMINOTRANSFERASA");
			break;
		case "1968-7":
			exam.setDisplayCode("BILIRUBINA DIRECTA");
			break;
		case "1975-2":
			exam.setDisplayCode("BILIRUBINA TOTAL");
			break;
		case "2324-2":
			exam.setDisplayCode("GGT - GAMMA GLUTAMILTRANSPEPTIDASA");
			break;
		case "2532-0":
			exam.setDisplayCode("DHL - DESHIDROGENASA LÁCTICA");
			break;
		case "2885-2":
			exam.setDisplayCode("PROTEÍNAS TOTALES");
			break;
		case "5902-2":
			exam.setDisplayCode("PT - PROTROMBINA");
			break;
		case "3013-0":
			exam.setDisplayCode("TIROGLOBULINA");
			break;
		case "3016-3":
			exam.setDisplayCode("TIROTROPINA");
			break;
		case "3053-6":
			exam.setDisplayCode("T3 TRIYODOTIRONINA");
			break;
		case "3051-0":
			exam.setDisplayCode("T3 LIBRE TRIYODOTIRONINA");
			break;
		case "3026-2":
			exam.setDisplayCode("T4 TIROXINA");
			break;
		case "3024-7":
			exam.setDisplayCode("T4 LIBRE TIROXINA");
			break;
		}
		return exam;

	}

}
