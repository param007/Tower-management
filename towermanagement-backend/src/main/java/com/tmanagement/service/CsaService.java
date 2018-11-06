package com.tmanagement.service;

import java.util.List;

import com.tmanagement.model.CSA;

public interface CsaService {
	
	public List<CSA> fetchAllCsa();
	public CSA getCsaByCsaId (int csaId);
	CSA findByEmail(String email);

}
