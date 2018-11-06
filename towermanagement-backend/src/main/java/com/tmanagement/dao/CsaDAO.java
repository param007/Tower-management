package com.tmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmanagement.model.CSA;

@Repository
public interface CsaDAO  extends JpaRepository<CSA, Integer>{

	public CSA getCsaByCsaId (int csaId);
	CSA findByEmail(String email);

	
}
