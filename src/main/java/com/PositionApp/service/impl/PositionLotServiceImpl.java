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
import com.PositionApp.dao.PositionLotDAO;
import com.PositionApp.domain.PositionLot;
import com.PositionApp.dto.PositionLotDTO;
import com.PositionApp.dto.PositionLotSearchDTO;
import com.PositionApp.dto.PositionLotPageDTO;
import com.PositionApp.dto.PositionLotConvertCriteriaDTO;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import com.PositionApp.service.PositionLotService;
import com.PositionApp.util.ControllerUtils;


@Service
public class PositionLotServiceImpl extends GenericServiceImpl<PositionLot, Integer> implements PositionLotService {

    private final static Logger logger = LoggerFactory.getLogger(PositionLotServiceImpl.class);

	@Autowired
	PositionLotDAO positionLotDao;

	

	@Override
	public GenericDAO<PositionLot, Integer> getDAO() {
		return (GenericDAO<PositionLot, Integer>) positionLotDao;
	}
	
	public List<PositionLot> findAll () {
		List<PositionLot> positionLots = positionLotDao.findAll();
		
		return positionLots;	
		
	}

	public ResultDTO addPositionLot(PositionLotDTO positionLotDTO, RequestDTO requestDTO) {

		PositionLot positionLot = new PositionLot();

		positionLot.setPositionLotId(positionLotDTO.getPositionLotId());

		positionLot.setPositionId(positionLotDTO.getPositionId());

		positionLot.setQuantity(positionLotDTO.getQuantity());

		positionLot.setAcquisitionPrice(positionLotDTO.getAcquisitionPrice());

		positionLot.setAcquisitionDate(positionLotDTO.getAcquisitionDate());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		positionLot = positionLotDao.save(positionLot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PositionLot> getAllPositionLots(Pageable pageable) {
		return positionLotDao.findAll(pageable);
	}

	public Page<PositionLot> getAllPositionLots(Specification<PositionLot> spec, Pageable pageable) {
		return positionLotDao.findAll(spec, pageable);
	}

	public ResponseEntity<PositionLotPageDTO> getPositionLots(PositionLotSearchDTO positionLotSearchDTO) {
	
			Integer positionLotId = positionLotSearchDTO.getPositionLotId(); 
 			Integer positionId = positionLotSearchDTO.getPositionId(); 
   			Date acquisitionDate = positionLotSearchDTO.getAcquisitionDate();
 			String sortBy = positionLotSearchDTO.getSortBy();
			String sortOrder = positionLotSearchDTO.getSortOrder();
			String searchQuery = positionLotSearchDTO.getSearchQuery();
			Integer page = positionLotSearchDTO.getPage();
			Integer size = positionLotSearchDTO.getSize();

	        Specification<PositionLot> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, positionLotId, "positionLotId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, positionId, "positionId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, acquisitionDate, "acquisitionDate");
			

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

		Page<PositionLot> positionLots = this.getAllPositionLots(spec, pageable);
		
		//System.out.println(String.valueOf(positionLots.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(positionLots.getTotalPages()));
		
		List<PositionLot> positionLotsList = positionLots.getContent();
		
		PositionLotConvertCriteriaDTO convertCriteria = new PositionLotConvertCriteriaDTO();
		List<PositionLotDTO> positionLotDTOs = this.convertPositionLotsToPositionLotDTOs(positionLotsList,convertCriteria);
		
		PositionLotPageDTO positionLotPageDTO = new PositionLotPageDTO();
		positionLotPageDTO.setPositionLots(positionLotDTOs);
		positionLotPageDTO.setTotalElements(positionLots.getTotalElements());
		return ResponseEntity.ok(positionLotPageDTO);
	}

	public List<PositionLotDTO> convertPositionLotsToPositionLotDTOs(List<PositionLot> positionLots, PositionLotConvertCriteriaDTO convertCriteria) {
		
		List<PositionLotDTO> positionLotDTOs = new ArrayList<PositionLotDTO>();
		
		for (PositionLot positionLot : positionLots) {
			positionLotDTOs.add(convertPositionLotToPositionLotDTO(positionLot,convertCriteria));
		}
		
		return positionLotDTOs;

	}
	
	public PositionLotDTO convertPositionLotToPositionLotDTO(PositionLot positionLot, PositionLotConvertCriteriaDTO convertCriteria) {
		
		PositionLotDTO positionLotDTO = new PositionLotDTO();

		positionLotDTO.setPositionLotId(positionLot.getPositionLotId());

		positionLotDTO.setPositionId(positionLot.getPositionId());

		positionLotDTO.setQuantity(positionLot.getQuantity());

		positionLotDTO.setAcquisitionPrice(positionLot.getAcquisitionPrice());

		positionLotDTO.setAcquisitionDate(positionLot.getAcquisitionDate());
		
		return positionLotDTO;
	}

	public ResultDTO updatePositionLot(PositionLotDTO positionLotDTO, RequestDTO requestDTO) {
		
		PositionLot positionLot = positionLotDao.getById(positionLotDTO.getPositionLotId());
		
		positionLot.setPositionLotId(ControllerUtils.setValue(positionLot.getPositionLotId(), positionLotDTO.getPositionLotId()));
		
		positionLot.setPositionId(ControllerUtils.setValue(positionLot.getPositionId(), positionLotDTO.getPositionId()));
		
		positionLot.setQuantity(ControllerUtils.setValue(positionLot.getQuantity(), positionLotDTO.getQuantity()));
		
		positionLot.setAcquisitionPrice(ControllerUtils.setValue(positionLot.getAcquisitionPrice(), positionLotDTO.getAcquisitionPrice()));
		
		positionLot.setAcquisitionDate(ControllerUtils.setValue(positionLot.getAcquisitionDate(), positionLotDTO.getAcquisitionDate()));

        positionLot = positionLotDao.save(positionLot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PositionLotDTO getPositionLotDTOById(Integer positionLotId) {
	
		PositionLot positionLot = positionLotDao.getById(positionLotId);
		
		PositionLotConvertCriteriaDTO convertCriteria = new PositionLotConvertCriteriaDTO();
		return(this.convertPositionLotToPositionLotDTO(positionLot,convertCriteria));
	}

}
