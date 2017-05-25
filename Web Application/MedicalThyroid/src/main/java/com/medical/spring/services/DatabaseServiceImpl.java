package com.medical.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.DAO;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	DAO dao;

	@Override
	public void createDatabase() {
		dao.createDataBase();
	}
}
