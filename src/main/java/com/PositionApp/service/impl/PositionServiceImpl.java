package com.PositionApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.PositionApp.dao.GenericDAO;
import com.PositionApp.service.GenericService;
import com.PositionApp.service.impl.GenericServiceImpl;
import com.PositionApp.dao.PositionDAO;
import com.PositionApp.domain.Position;
import com.PositionApp.dto.PositionDTO;
import com.PositionApp.dto.PositionSearchDTO;
import com.PositionApp.dto.PositionPageDTO;
import com.PositionApp.dto.PositionConvertCriteriaDTO;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import com.PositionApp.service.PositionService;
import com.PositionApp.util.ControllerUtils;


@Service
public class PositionServiceImpl extends GenericServiceImpl<Position, Integer> implements PositionService {

    private final static Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

	@Autowired
	PositionDAO positionDao;

	

	@Override
	public GenericDAO<Position, Integer> getDAO() {
		return (GenericDAO<Position, Integer>) positionDao;
	}
	
	public List<Position> findAll () {
		List<Position> positions = positionDao.findAll();
		
		return positions;	
		
	}

	public ResultDTO addPosition(PositionDTO positionDTO, RequestDTO requestDTO) {

		Position position = new Position();

		position.setPositionId(positionDTO.getPositionId());

		position.setPortfolioId(positionDTO.getPortfolioId());

		position.setInstrumentId(positionDTO.getInstrumentId());

		position.setQuantity(positionDTO.getQuantity());

		position.setAveragePrice(positionDTO.getAveragePrice());

		position.setMarketPrice(positionDTO.getMarketPrice());

		position.setMarketValue(positionDTO.getMarketValue());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		position = positionDao.save(position);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Position> getAllPositions(Pageable pageable) {
		return positionDao.findAll(pageable);
	}

	public Page<Position> getAllPositions(Specification<Position> spec, Pageable pageable) {
		return positionDao.findAll(spec, pageable);
	}

	public ResponseEntity<PositionPageDTO> getPositions(PositionSearchDTO positionSearchDTO) {
	
			Integer positionId = positionSearchDTO.getPositionId(); 
 			Integer portfolioId = positionSearchDTO.getPortfolioId(); 
 			Integer instrumentId = positionSearchDTO.getInstrumentId(); 
     			String sortBy = positionSearchDTO.getSortBy();
			String sortOrder = positionSearchDTO.getSortOrder();
			String searchQuery = positionSearchDTO.getSearchQuery();
			Integer page = positionSearchDTO.getPage();
			Integer size = positionSearchDTO.getSize();

	        Specification<Position> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, positionId, "positionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, portfolioId, "portfolioId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Position> positions = this.getAllPositions(spec, pageable);
		
		//System.out.println(String.valueOf(positions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(positions.getTotalPages()));
		
		List<Position> positionsList = positions.getContent();
		
		PositionConvertCriteriaDTO convertCriteria = new PositionConvertCriteriaDTO();
		List<PositionDTO> positionDTOs = this.convertPositionsToPositionDTOs(positionsList,convertCriteria);
		
		PositionPageDTO positionPageDTO = new PositionPageDTO();
		positionPageDTO.setPositions(positionDTOs);
		positionPageDTO.setTotalElements(positions.getTotalElements());
		return ResponseEntity.ok(positionPageDTO);
	}

	public List<PositionDTO> convertPositionsToPositionDTOs(List<Position> positions, PositionConvertCriteriaDTO convertCriteria) {
		
		List<PositionDTO> positionDTOs = new ArrayList<PositionDTO>();
		
		for (Position position : positions) {
			positionDTOs.add(convertPositionToPositionDTO(position,convertCriteria));
		}
		
		return positionDTOs;

	}
	
	public PositionDTO convertPositionToPositionDTO(Position position, PositionConvertCriteriaDTO convertCriteria) {
		
		PositionDTO positionDTO = new PositionDTO();

		positionDTO.setPositionId(position.getPositionId());

		positionDTO.setPortfolioId(position.getPortfolioId());

		positionDTO.setInstrumentId(position.getInstrumentId());

		positionDTO.setQuantity(position.getQuantity());

		positionDTO.setAveragePrice(position.getAveragePrice());

		positionDTO.setMarketPrice(position.getMarketPrice());

		positionDTO.setMarketValue(position.getMarketValue());
		
		return positionDTO;
	}

	public ResultDTO updatePosition(PositionDTO positionDTO, RequestDTO requestDTO) {
		
		Position position = positionDao.getById(positionDTO.getPositionId());
		
		position.setPositionId(ControllerUtils.setValue(position.getPositionId(), positionDTO.getPositionId()));
		
		position.setPortfolioId(ControllerUtils.setValue(position.getPortfolioId(), positionDTO.getPortfolioId()));
		
		position.setInstrumentId(ControllerUtils.setValue(position.getInstrumentId(), positionDTO.getInstrumentId()));
		
		position.setQuantity(ControllerUtils.setValue(position.getQuantity(), positionDTO.getQuantity()));
		
		position.setAveragePrice(ControllerUtils.setValue(position.getAveragePrice(), positionDTO.getAveragePrice()));
		
		position.setMarketPrice(ControllerUtils.setValue(position.getMarketPrice(), positionDTO.getMarketPrice()));
		
		position.setMarketValue(ControllerUtils.setValue(position.getMarketValue(), positionDTO.getMarketValue()));

        position = positionDao.save(position);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PositionDTO getPositionDTOById(Integer positionId) {
	
		Position position = positionDao.getById(positionId);
		
		PositionConvertCriteriaDTO convertCriteria = new PositionConvertCriteriaDTO();
		return(this.convertPositionToPositionDTO(position,convertCriteria));
	}

}
