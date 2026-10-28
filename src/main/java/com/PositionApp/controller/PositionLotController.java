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

import com.PositionApp.domain.PositionLot;
import com.PositionApp.dto.PositionLotDTO;
import com.PositionApp.dto.PositionLotSearchDTO;
import com.PositionApp.dto.PositionLotPageDTO;
import com.PositionApp.service.PositionLotService;
import com.PositionApp.dto.common.RequestDTO;
import com.PositionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/positionLot")
@RestController
public class PositionLotController {

	private final static Logger logger = LoggerFactory.getLogger(PositionLotController.class);

	@Autowired
	PositionLotService positionLotService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PositionLot> getAll() {

		List<PositionLot> positionLots = positionLotService.findAll();
		
		return positionLots;	
	}

	@GetMapping(value = "/{positionLotId}")
	@ResponseBody
	public PositionLotDTO getPositionLot(@PathVariable Integer positionLotId) {
		
		return (positionLotService.getPositionLotDTOById(positionLotId));
	}

 	@RequestMapping(value = "/addPositionLot", method = RequestMethod.POST)
	public ResponseEntity<?> addPositionLot(@RequestBody PositionLotDTO positionLotDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionLotService.addPositionLot(positionLotDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/positionLots")
	public ResponseEntity<PositionLotPageDTO> getPositionLots(PositionLotSearchDTO positionLotSearchDTO) {
 
		return positionLotService.getPositionLots(positionLotSearchDTO);
	}	

	@RequestMapping(value = "/updatePositionLot", method = RequestMethod.POST)
	public ResponseEntity<?> updatePositionLot(@RequestBody PositionLotDTO positionLotDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = positionLotService.updatePositionLot(positionLotDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
