package com.mypersonalbook.economy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "d_transaction_type")
public class TransactionTypeMO {
  @Id
  @Column(name = "transaction_type_id")
  private String id;

  public TransactionTypeMO() {}

  public TransactionTypeMO(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
