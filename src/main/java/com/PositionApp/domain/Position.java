package com.PositionApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="positions")
@Getter @Setter @NoArgsConstructor
public class Position {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="position_id")
	private Integer positionId;
    
  	@Column(name="portfolio_id")
	private Integer portfolioId;
    
  	@Column(name="instrument_id")
	private Integer instrumentId;
    
  	@Column(name="quantity")
	private Double quantity;
    
  	@Column(name="average_price")
	private Double averagePrice;
    
  	@Column(name="market_price")
	private Double marketPrice;
    
  	@Column(name="market_value")
	private Double marketValue;
    

}
