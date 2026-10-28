package com.PositionApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PositionSnapshotDTO {

	private Integer positionSnapshotId;

	private Integer positionId;

	private Double quantity;

	private Double marketValue;

	private Double unrealizedPnL;

	private Date capturedAt;






}
