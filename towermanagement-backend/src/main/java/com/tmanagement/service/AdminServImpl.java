package com.tmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmanagement.dao.AdminDao;
import com.tmanagement.model.Admin;

@Service
public class AdminServImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.findAll();
	}

	@Override
	public Admin findByEmail(String email) {
		return adminDao.findByEmail(email);
	}

}
