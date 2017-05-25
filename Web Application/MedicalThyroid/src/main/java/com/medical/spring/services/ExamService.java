package com.medical.spring.services;

import java.util.List;

import com.medical.spring.model.Exam;

public interface ExamService {

	public void insert(Exam exam);

	public void edit(Exam exam);

	public boolean find(String ndivalue, String order);

	public Exam select(String ndivalue, String order);

	public List<Exam> selectAll(String ndivalue);

	public Exam selectByOrder(String order);

	public void delete(String ndivalue, String order);

	public List<Exam> selectForLab();
}
