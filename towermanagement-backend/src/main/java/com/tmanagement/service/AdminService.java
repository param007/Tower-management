package com.tmanagement.service;

import java.util.List;

import com.tmanagement.model.Admin;


public interface AdminService {

	List<Admin> getAllAdmins();
	Admin findByEmail(String email);
}
