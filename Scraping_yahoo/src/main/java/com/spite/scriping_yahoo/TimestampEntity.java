package com.spite.scriping_yahoo;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass    // JPAエンティティの親に必要。
public abstract class TimestampEntity {

  public Timestamp UPDDT;

  @Column(updatable=false)
  public Timestamp INSDT;

  @PrePersist
  public void prePersist() {
    Timestamp ts = new Timestamp((new Date()).getTime());
    this.INSDT = ts;
    this.UPDDT = ts;
    }

  @PreUpdate
  public void preUpdate() {
    this.UPDDT = new Timestamp((new Date()).getTime());
  }
}
