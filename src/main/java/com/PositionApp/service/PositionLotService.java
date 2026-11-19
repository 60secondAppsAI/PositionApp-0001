package com.PositionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PositionApp.domain.PositionLot;
import com.PositionApp.dto.PositionLotDTO;
import com.PositionApp.dto.PositionLotSearchDTO;
import com.PositionApp.dto.PositionLotPageDTO;
import com.PositionApp.dto.PositionLotConvertCriteriaDTO;
import com.PositionApp.service.GenericService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PositionLotService extends GenericService<PositionLot, Integer> {

	List<PositionLot> findAll();

	ResultDTO addPositionLot(PositionLotDTO positionLotDTO, RequestDTO requestDTO);

	ResultDTO updatePositionLot(PositionLotDTO positionLotDTO, RequestDTO requestDTO);

    Page<PositionLot> getAllPositionLots(Pageable pageable);

    Page<PositionLot> getAllPositionLots(Specification<PositionLot> spec, Pageable pageable);

	ResponseEntity<PositionLotPageDTO> getPositionLots(PositionLotSearchDTO positionLotSearchDTO);
	
	List<PositionLotDTO> convertPositionLotsToPositionLotDTOs(List<PositionLot> positionLots, PositionLotConvertCriteriaDTO convertCriteria);

	PositionLotDTO getPositionLotDTOById(Integer positionLotId);



	
}
