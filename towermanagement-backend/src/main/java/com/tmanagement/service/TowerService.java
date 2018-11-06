package com.tmanagement.service;

import java.util.List;

import com.tmanagement.model.Tower;

public interface TowerService {
	public List<Tower> getTowerByCircle(String circle);
	public List<Tower> getTowerByCsaId(int inputCsaId);
	
	public Tower getTowerByTowerId (int towerId);
	public Tower changeTowerStatus(Tower tower);
	
	public List<Tower> allTowers();
}
