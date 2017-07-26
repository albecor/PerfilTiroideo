package com.spring.medical.service;

import java.util.List;
import java.util.Set;

import com.spring.medical.model.Exam;

public interface ExamService {

	/**
	 * insert exam in the database
	 * 
	 * @param exam
	 */
	void insert(Exam exam);

	/**
	 * delete exam in the database
	 * 
	 * @param exam_id
	 */
	void deleteExam(Integer exam_id);

	/**
	 * set exam to user
	 * 
	 * @param user_id
	 * @param exam_id
	 */
	void setUserExam(Integer user_id, Integer exam_id);

	/**
	 * update exam
	 * 
	 * @param exam
	 */
	void update(Exam exam);

	/**
	 * select all exams
	 * 
	 * @return exams list
	 */
	List<Exam> selectAll();

	/**
	 * select exams not done
	 * 
	 * @return exmas list
	 */
	List<Exam> selectNotDone();

	/**
	 * select exams by user
	 * 
	 * @param id
	 * @return exams list
	 */
	Set<Exam> selectByUser(Integer id);

	/**
	 * select exam by order
	 * 
	 * @param order
	 * @return exam object
	 */
	Exam selectByOrder(Integer order);

	/**
	 * select initial parameters for the exams
	 * 
	 * @param exam
	 * @return
	 */
	Exam getInitialExamParameters(Exam exam);

	/**
	 * select list panel of exams
	 * 
	 * @return names list
	 */
	List<String> listPanelExam();

}
