package com.mypersonalbook.economy.models;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.time.LocalDateTime;

@Entity
@RevisionEntity
@Table(name = "revinfo")
public class CustomRevisionMO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rev")
  private int id;

  @RevisionNumber
  @Column(name = "revnumber", nullable = false, updatable = false)
  private int revisionNumber;

  @RevisionTimestamp
  @Column(name = "revtstmp", nullable = false, updatable = false)
  private LocalDateTime timestamp;

  @PrePersist
  public void prePersist() {
    this.timestamp = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}