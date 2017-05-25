package com.medical.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.ExamDao;
import com.medical.spring.model.Exam;

@Service("examService")
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamDao examDao;

	@Override
	public void insert(Exam exam) {
		examDao.insert(exam);
	}

	@Override
	public void edit(Exam exam) {
		examDao.edit(exam);
	}

	@Override
	public Exam select(String ndivalue, String order) {
		return examDao.select(ndivalue, order);
	}

	@Override
	public List<Exam> selectAll(String ndivalue) {
		return examDao.selectAll(ndivalue);
	}

	@Override
	public void delete(String ndivalue, String order) {
		examDao.delete(ndivalue, order);
	}

	@Override
	public boolean find(String ndivalue, String order) {
		return examDao.find(ndivalue, order);
	}

	@Override
	public List<Exam> selectForLab() {
		return examDao.selectForLab();
	}

	@Override
	public Exam selectByOrder(String order) {
		return examDao.selectByOrder(order);
	}

}
