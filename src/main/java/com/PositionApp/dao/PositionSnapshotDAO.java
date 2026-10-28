package com.PositionApp.dao;

import java.util.List;
import java.util.Date;

import com.PositionApp.dao.GenericDAO;
import com.PositionApp.domain.PositionSnapshot;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface PositionSnapshotDAO extends GenericDAO<PositionSnapshot, Integer> {
  
	List<PositionSnapshot> findAll();
	


}

