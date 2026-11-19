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

import com.PositionApp.domain.Position;
import com.PositionApp.dto.PositionDTO;
import com.PositionApp.dto.PositionSearchDTO;
import com.PositionApp.dto.PositionPageDTO;
import com.PositionApp.service.PositionService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/position")
@RestController
public class PositionController {

	private final static Logger logger = LoggerFactory.getLogger(PositionController.class);

	@Autowired
	PositionService positionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Position> getAll() {

		List<Position> positions = positionService.findAll();
		
		return positions;	
	}

	@GetMapping(value = "/{positionId}")
	@ResponseBody
	public PositionDTO getPosition(@PathVariable Integer positionId) {
		
		return (positionService.getPositionDTOById(positionId));
	}

 	@RequestMapping(value = "/addPosition", method = RequestMethod.POST)
	public ResponseEntity<?> addPosition(@RequestBody PositionDTO positionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionService.addPosition(positionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/positions")
	public ResponseEntity<PositionPageDTO> getPositions(PositionSearchDTO positionSearchDTO) {
 
		return positionService.getPositions(positionSearchDTO);
	}	

	@RequestMapping(value = "/updatePosition", method = RequestMethod.POST)
	public ResponseEntity<?> updatePosition(@RequestBody PositionDTO positionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionService.updatePosition(positionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
