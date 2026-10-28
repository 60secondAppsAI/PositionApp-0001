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
import com.PositionApp.dao.PositionSnapshotDAO;
import com.PositionApp.domain.PositionSnapshot;
import com.PositionApp.dto.PositionSnapshotDTO;
import com.PositionApp.dto.PositionSnapshotSearchDTO;
import com.PositionApp.dto.PositionSnapshotPageDTO;
import com.PositionApp.dto.PositionSnapshotConvertCriteriaDTO;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import com.PositionApp.service.PositionSnapshotService;
import com.PositionApp.util.ControllerUtils;


@Service
public class PositionSnapshotServiceImpl extends GenericServiceImpl<PositionSnapshot, Integer> implements PositionSnapshotService {

    private final static Logger logger = LoggerFactory.getLogger(PositionSnapshotServiceImpl.class);

	@Autowired
	PositionSnapshotDAO positionSnapshotDao;

	

	@Override
	public GenericDAO<PositionSnapshot, Integer> getDAO() {
		return (GenericDAO<PositionSnapshot, Integer>) positionSnapshotDao;
	}
	
	public List<PositionSnapshot> findAll () {
		List<PositionSnapshot> positionSnapshots = positionSnapshotDao.findAll();
		
		return positionSnapshots;	
		
	}

	public ResultDTO addPositionSnapshot(PositionSnapshotDTO positionSnapshotDTO, RequestDTO requestDTO) {

		PositionSnapshot positionSnapshot = new PositionSnapshot();

		positionSnapshot.setPositionSnapshotId(positionSnapshotDTO.getPositionSnapshotId());

		positionSnapshot.setPositionId(positionSnapshotDTO.getPositionId());

		positionSnapshot.setQuantity(positionSnapshotDTO.getQuantity());

		positionSnapshot.setMarketValue(positionSnapshotDTO.getMarketValue());

		positionSnapshot.setUnrealizedPnL(positionSnapshotDTO.getUnrealizedPnL());

		positionSnapshot.setCapturedAt(positionSnapshotDTO.getCapturedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		positionSnapshot = positionSnapshotDao.save(positionSnapshot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PositionSnapshot> getAllPositionSnapshots(Pageable pageable) {
		return positionSnapshotDao.findAll(pageable);
	}

	public Page<PositionSnapshot> getAllPositionSnapshots(Specification<PositionSnapshot> spec, Pageable pageable) {
		return positionSnapshotDao.findAll(spec, pageable);
	}

	public ResponseEntity<PositionSnapshotPageDTO> getPositionSnapshots(PositionSnapshotSearchDTO positionSnapshotSearchDTO) {
	
			Integer positionSnapshotId = positionSnapshotSearchDTO.getPositionSnapshotId(); 
 			Integer positionId = positionSnapshotSearchDTO.getPositionId(); 
    			Date capturedAt = positionSnapshotSearchDTO.getCapturedAt();
 			String sortBy = positionSnapshotSearchDTO.getSortBy();
			String sortOrder = positionSnapshotSearchDTO.getSortOrder();
			String searchQuery = positionSnapshotSearchDTO.getSearchQuery();
			Integer page = positionSnapshotSearchDTO.getPage();
			Integer size = positionSnapshotSearchDTO.getSize();

	        Specification<PositionSnapshot> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, positionSnapshotId, "positionSnapshotId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, positionId, "positionId"); 
			
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, capturedAt, "capturedAt");
			

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

		Page<PositionSnapshot> positionSnapshots = this.getAllPositionSnapshots(spec, pageable);
		
		//System.out.println(String.valueOf(positionSnapshots.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(positionSnapshots.getTotalPages()));
		
		List<PositionSnapshot> positionSnapshotsList = positionSnapshots.getContent();
		
		PositionSnapshotConvertCriteriaDTO convertCriteria = new PositionSnapshotConvertCriteriaDTO();
		List<PositionSnapshotDTO> positionSnapshotDTOs = this.convertPositionSnapshotsToPositionSnapshotDTOs(positionSnapshotsList,convertCriteria);
		
		PositionSnapshotPageDTO positionSnapshotPageDTO = new PositionSnapshotPageDTO();
		positionSnapshotPageDTO.setPositionSnapshots(positionSnapshotDTOs);
		positionSnapshotPageDTO.setTotalElements(positionSnapshots.getTotalElements());
		return ResponseEntity.ok(positionSnapshotPageDTO);
	}

	public List<PositionSnapshotDTO> convertPositionSnapshotsToPositionSnapshotDTOs(List<PositionSnapshot> positionSnapshots, PositionSnapshotConvertCriteriaDTO convertCriteria) {
		
		List<PositionSnapshotDTO> positionSnapshotDTOs = new ArrayList<PositionSnapshotDTO>();
		
		for (PositionSnapshot positionSnapshot : positionSnapshots) {
			positionSnapshotDTOs.add(convertPositionSnapshotToPositionSnapshotDTO(positionSnapshot,convertCriteria));
		}
		
		return positionSnapshotDTOs;

	}
	
	public PositionSnapshotDTO convertPositionSnapshotToPositionSnapshotDTO(PositionSnapshot positionSnapshot, PositionSnapshotConvertCriteriaDTO convertCriteria) {
		
		PositionSnapshotDTO positionSnapshotDTO = new PositionSnapshotDTO();

		positionSnapshotDTO.setPositionSnapshotId(positionSnapshot.getPositionSnapshotId());

		positionSnapshotDTO.setPositionId(positionSnapshot.getPositionId());

		positionSnapshotDTO.setQuantity(positionSnapshot.getQuantity());

		positionSnapshotDTO.setMarketValue(positionSnapshot.getMarketValue());

		positionSnapshotDTO.setUnrealizedPnL(positionSnapshot.getUnrealizedPnL());

		positionSnapshotDTO.setCapturedAt(positionSnapshot.getCapturedAt());
		
		return positionSnapshotDTO;
	}

	public ResultDTO updatePositionSnapshot(PositionSnapshotDTO positionSnapshotDTO, RequestDTO requestDTO) {
		
		PositionSnapshot positionSnapshot = positionSnapshotDao.getById(positionSnapshotDTO.getPositionSnapshotId());
		
		positionSnapshot.setPositionSnapshotId(ControllerUtils.setValue(positionSnapshot.getPositionSnapshotId(), positionSnapshotDTO.getPositionSnapshotId()));
		
		positionSnapshot.setPositionId(ControllerUtils.setValue(positionSnapshot.getPositionId(), positionSnapshotDTO.getPositionId()));
		
		positionSnapshot.setQuantity(ControllerUtils.setValue(positionSnapshot.getQuantity(), positionSnapshotDTO.getQuantity()));
		
		positionSnapshot.setMarketValue(ControllerUtils.setValue(positionSnapshot.getMarketValue(), positionSnapshotDTO.getMarketValue()));
		
		positionSnapshot.setUnrealizedPnL(ControllerUtils.setValue(positionSnapshot.getUnrealizedPnL(), positionSnapshotDTO.getUnrealizedPnL()));
		
		positionSnapshot.setCapturedAt(ControllerUtils.setValue(positionSnapshot.getCapturedAt(), positionSnapshotDTO.getCapturedAt()));

        positionSnapshot = positionSnapshotDao.save(positionSnapshot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PositionSnapshotDTO getPositionSnapshotDTOById(Integer positionSnapshotId) {
	
		PositionSnapshot positionSnapshot = positionSnapshotDao.getById(positionSnapshotId);
		
		PositionSnapshotConvertCriteriaDTO convertCriteria = new PositionSnapshotConvertCriteriaDTO();
		return(this.convertPositionSnapshotToPositionSnapshotDTO(positionSnapshot,convertCriteria));
	}

}
