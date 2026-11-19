package com.PositionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.PositionApp.domain.PositionSnapshot;
import com.PositionApp.dto.PositionSnapshotDTO;
import com.PositionApp.dto.PositionSnapshotSearchDTO;
import com.PositionApp.dto.PositionSnapshotPageDTO;
import com.PositionApp.dto.PositionSnapshotConvertCriteriaDTO;
import com.PositionApp.service.GenericService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PositionSnapshotService extends GenericService<PositionSnapshot, Integer> {

	List<PositionSnapshot> findAll();

	ResultDTO addPositionSnapshot(PositionSnapshotDTO positionSnapshotDTO, RequestDTO requestDTO);

	ResultDTO updatePositionSnapshot(PositionSnapshotDTO positionSnapshotDTO, RequestDTO requestDTO);

    Page<PositionSnapshot> getAllPositionSnapshots(Pageable pageable);

    Page<PositionSnapshot> getAllPositionSnapshots(Specification<PositionSnapshot> spec, Pageable pageable);

	ResponseEntity<PositionSnapshotPageDTO> getPositionSnapshots(PositionSnapshotSearchDTO positionSnapshotSearchDTO);
	
	List<PositionSnapshotDTO> convertPositionSnapshotsToPositionSnapshotDTOs(List<PositionSnapshot> positionSnapshots, PositionSnapshotConvertCriteriaDTO convertCriteria);

	PositionSnapshotDTO getPositionSnapshotDTOById(Integer positionSnapshotId);



	
}
