package com.PositionApp.dao;

import java.util.List;
import java.util.Date;

import com.PositionApp.dao.GenericDAO;
import com.PositionApp.domain.PositionLot;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface PositionLotDAO extends GenericDAO<PositionLot, Integer> {
  
	List<PositionLot> findAll();
	


}

