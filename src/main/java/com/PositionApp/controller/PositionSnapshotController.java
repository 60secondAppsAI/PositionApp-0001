package com.PositionApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.PositionApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.PositionApp.domain.PositionSnapshot;
import com.PositionApp.dto.PositionSnapshotDTO;
import com.PositionApp.dto.PositionSnapshotSearchDTO;
import com.PositionApp.dto.PositionSnapshotPageDTO;
import com.PositionApp.service.PositionSnapshotService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/positionSnapshot")
@RestController
public class PositionSnapshotController {

	private final static Logger logger = LoggerFactory.getLogger(PositionSnapshotController.class);

	@Autowired
	PositionSnapshotService positionSnapshotService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PositionSnapshot> getAll() {

		List<PositionSnapshot> positionSnapshots = positionSnapshotService.findAll();
		
		return positionSnapshots;	
	}

	@GetMapping(value = "/{positionSnapshotId}")
	@ResponseBody
	public PositionSnapshotDTO getPositionSnapshot(@PathVariable Integer positionSnapshotId) {
		
		return (positionSnapshotService.getPositionSnapshotDTOById(positionSnapshotId));
	}

 	@RequestMapping(value = "/addPositionSnapshot", method = RequestMethod.POST)
	public ResponseEntity<?> addPositionSnapshot(@RequestBody PositionSnapshotDTO positionSnapshotDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionSnapshotService.addPositionSnapshot(positionSnapshotDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/positionSnapshots")
	public ResponseEntity<PositionSnapshotPageDTO> getPositionSnapshots(PositionSnapshotSearchDTO positionSnapshotSearchDTO) {
 
		return positionSnapshotService.getPositionSnapshots(positionSnapshotSearchDTO);
	}	

	@RequestMapping(value = "/updatePositionSnapshot", method = RequestMethod.POST)
	public ResponseEntity<?> updatePositionSnapshot(@RequestBody PositionSnapshotDTO positionSnapshotDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionSnapshotService.updatePositionSnapshot(positionSnapshotDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
