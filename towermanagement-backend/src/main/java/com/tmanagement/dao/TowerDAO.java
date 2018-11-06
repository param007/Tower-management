package com.tmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmanagement.model.Tower;

@Repository
public interface TowerDAO extends JpaRepository<Tower, Integer> {
	
	public List<Tower> getTowerByCircle(String circle);	
	public Tower getTowerByTowerId (int towerId);

	
	
}
