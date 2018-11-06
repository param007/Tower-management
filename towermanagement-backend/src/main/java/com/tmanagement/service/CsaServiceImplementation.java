package com.tmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmanagement.dao.CsaDAO;
import com.tmanagement.model.CSA;

@Service
public class CsaServiceImplementation implements CsaService{

	@Autowired
	public CsaDAO csaRepo;
	
	@Override
	public List<CSA> fetchAllCsa() {
		return csaRepo.findAll();
	}

	@Override
	public CSA getCsaByCsaId(int csaId) {
		CSA csa = null;

		Optional<CSA> opCsa = csaRepo.findById(csaId);
		if (opCsa.isPresent()) {
			csa = opCsa.get();
		}

		return csa;
	}

	@Override
	public CSA findByEmail(String email) {
		return csaRepo.findByEmail(email);
	}

}
