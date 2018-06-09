package com.spite.scriping_rakuten;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="sc_rakuten")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DumpEntry extends TimestampEntity{
	/**
	 * 
	 */

	@Id
	private String itemCode;
	
	//@Column(nullable = false)
	private String itemName;
	private Integer itemPrice;
	private String catchcopy;
	private String itemCaption;
	private String itemUrl;
	private String shopUrl;
	private String shopCode;
	private String shopName;
	private String genreId;
	private BigDecimal reviewAverage;
	//private Timestamp INSDT;

}
