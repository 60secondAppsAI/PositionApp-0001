package com.PositionApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PositionDTO {

	private Integer positionId;

	private Integer portfolioId;

	private Integer instrumentId;

	private Double quantity;

	private Double averagePrice;

	private Double marketPrice;

	private Double marketValue;






}
