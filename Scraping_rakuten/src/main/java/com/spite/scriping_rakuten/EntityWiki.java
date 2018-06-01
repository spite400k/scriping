package com.spite.scriping_rakuten;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sc_wikipedia")
public class EntityWiki  extends TimestampEntity {

	@Id
	public Timestamp gettime;

	public Integer id;
	
	public String ref;

	public String status;

  // データ型 serial（PostgreSQL）。
//  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
//  public long id;

//  @NotEmpty
//  public String text;
//
//  @Version
//  public long version;    
}
