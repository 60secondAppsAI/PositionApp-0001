package com.PositionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowsePositionDTO {

	private Integer ownerId;

	private Integer positionId;

	private Integer positionStatus;
	
	private Integer nextOrPrevious;
}

