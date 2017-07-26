package com.spring.medical.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.medical.dao.ExamDao;
import com.spring.medical.dao.UserDao;
import com.spring.medical.model.Exam;
import com.spring.medical.utilities.PanelThyroid;

@Service("examService")
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamDao examDao;

	@Autowired
	UserDao userDao;

	@Override
	public void insert(Exam exam) {
		examDao.insert(exam);

		examDao.setUserExam(userDao.selectByNdivalue(exam.getReferenceSubject()).getId(),
				examDao.selectIdByIssued(exam.getIssued()));
	}

	@Override
	public void update(Exam exam) {
		examDao.update(exam);
	}

	@Override
	public List<Exam> selectAll() {
		return examDao.selectAll();
	}

	@Override
	public Exam selectByOrder(Integer order) {
		return examDao.selectByOrder(order);
	}

	@Override
	public void setUserExam(Integer user_id, Integer exam_id) {
		examDao.setUserExam(user_id, exam_id);

	}

	@Override
	public void deleteExam(Integer exam_id) {
		examDao.removeUserExam(exam_id);
		examDao.deleteExam(exam_id);
	}

	@Override
	public List<Exam> selectNotDone() {
		return examDao.selectNotDone();
	}

	@Override
	public Set<Exam> selectByUser(Integer id) {
		return examDao.selectByUser(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.medical.service.ExamService#getInitialExamParameters(com.
	 * spring.medical.model.Exam)
	 */
	@Override
	public Exam getInitialExamParameters(Exam exam) {
		return new PanelThyroid().getExam(exam);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.medical.service.ExamService#listPanelExam()
	 */
	@Override
	public List<String> listPanelExam() {
		return new PanelThyroid().listExam();

	}

}
