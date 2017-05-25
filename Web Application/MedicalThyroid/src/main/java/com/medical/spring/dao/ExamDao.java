package com.medical.spring.dao;

import java.util.List;

import com.medical.spring.model.Exam;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface ExamDao {

	/**
	 * insert exam to database
	 * @param exam - exam object
	 */
	public void insert(Exam exam);

	/**
	 * update exam to database
	 * @param exam - exam object
	 */
	public void edit(Exam exam);

	/**
	 * find if exist order
	 * @param ndivalue - patient of exam
	 * @param order - order number
	 * @return - true if find exam
	 */
	public boolean find(String ndivalue, String order);

	/**
	 * delete exam
	 * @param ndivalue - patient of exam
	 * @param order - order number
	 */
	public void delete(String ndivalue, String order);

	/**
	 * select exam
	 * @param ndivalue - patient of exam
	 * @param order - order number
	 * @return - exam object
	 */
	public Exam select(String ndivalue, String order);

	/**
	 * select by order
	 * @param order - order number
	 * @return - exam object
	 */
	public Exam selectByOrder(String order);

	/**
	 * list all exams
	 * @param ndivalue - patient's ndivalue
	 * @return - list exam
	 */
	public List<Exam> selectAll(String ndivalue);

	/**
	 * list exam autorized
	 * @return - list exam
	 */
	public List<Exam> selectForLab();
}
