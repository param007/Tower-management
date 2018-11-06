package com.tmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmanagement.dao.TowerDAO;
import com.tmanagement.model.Complaint;
import com.tmanagement.model.Tower;

@Service
public class TowerServiceImplementation implements TowerService {
	@Autowired
	public TowerDAO towerRepo;
	
	public Complaint complaint;

	@Override
	public List<Tower> getTowerByCircle(String circle) {
		return towerRepo.getTowerByCircle(circle);
	}

	@Override
	public List<Tower> getTowerByCsaId(int inputCsaId) {
		List<Tower> allTowers = towerRepo.findAll();
		List<Tower> csaTower = new ArrayList<>();

		for (Tower tow : allTowers) {
			if (tow.csa.getUserr().getId() == inputCsaId) {
				csaTower.add(tow);
			}
		}
		return csaTower;
	}

	@Override
	public Tower getTowerByTowerId(int towerId) {
		Tower tower = null;

		Optional<Tower> opTower = towerRepo.findById(towerId);
		if (opTower.isPresent()) {
			tower = opTower.get();
		}

		return tower;
	}


	@Override
	public List<Tower> allTowers() {
		return towerRepo.findAll();
	}

	@Override
	public Tower changeTowerStatus(Tower tower) {
		return towerRepo.save(tower);
	}

}
