package com.PositionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowsePositionSnapshotDTO {

	private Integer ownerId;

	private Integer positionSnapshotId;

	private Integer positionSnapshotStatus;
	
	private Integer nextOrPrevious;
}

