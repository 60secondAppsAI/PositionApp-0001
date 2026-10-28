package com.PositionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowsePositionLotDTO {

	private Integer ownerId;

	private Integer positionLotId;

	private Integer positionLotStatus;
	
	private Integer nextOrPrevious;
}

