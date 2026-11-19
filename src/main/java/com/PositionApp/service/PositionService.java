package com.PositionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PositionApp.domain.Position;
import com.PositionApp.dto.PositionDTO;
import com.PositionApp.dto.PositionSearchDTO;
import com.PositionApp.dto.PositionPageDTO;
import com.PositionApp.dto.PositionConvertCriteriaDTO;
import com.PositionApp.service.GenericService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PositionService extends GenericService<Position, Integer> {

	List<Position> findAll();

	ResultDTO addPosition(PositionDTO positionDTO, RequestDTO requestDTO);

	ResultDTO updatePosition(PositionDTO positionDTO, RequestDTO requestDTO);

    Page<Position> getAllPositions(Pageable pageable);

    Page<Position> getAllPositions(Specification<Position> spec, Pageable pageable);

	ResponseEntity<PositionPageDTO> getPositions(PositionSearchDTO positionSearchDTO);
	
	List<PositionDTO> convertPositionsToPositionDTOs(List<Position> positions, PositionConvertCriteriaDTO convertCriteria);

	PositionDTO getPositionDTOById(Integer positionId);



	
}
