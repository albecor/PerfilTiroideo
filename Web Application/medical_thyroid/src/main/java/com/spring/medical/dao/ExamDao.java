package com.spring.medical.dao;

import java.util.List;
import java.util.Set;

import com.spring.medical.model.Exam;

public interface ExamDao {

	/**
	 * insert exam into database
	 * 
	 * @param exam
	 */
	void insert(Exam exam);

	/**
	 * remove exam from user into table exams
	 * 
	 * @param exam_id
	 */
	void removeUserExam(Integer exam_id);

	/**
	 * delete exam in the database
	 * 
	 * @param exam_id
	 */
	void deleteExam(Integer exam_id);

	/**
	 * set exam to user into table exams
	 * 
	 * @param user_id
	 * @param exam_id
	 */
	void setUserExam(Integer user_id, Integer exam_id);

	/**
	 * update exam in the database
	 * 
	 * @param exam
	 */
	void update(Exam exam);

	/**
	 * select all exam in the database
	 * 
	 * @return list exams
	 */
	List<Exam> selectAll();

	/**
	 * select all exams not done
	 * 
	 * @return list exams
	 */
	List<Exam> selectNotDone();

	/**
	 * select exam by user id
	 * 
	 * @param id
	 * @return list exams
	 */
	Set<Exam> selectByUser(Integer id);

	/**
	 * select exam by order
	 * 
	 * @param order
	 * @return exam
	 */
	Exam selectByOrder(Integer order);

	/**
	 * select exam id from issued
	 * 
	 * @param issued
	 * @return exam id
	 */
	Integer selectIdByIssued(String issued);

}
