package com.mypersonalbook.economy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionTypeMO that = (TransactionTypeMO) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "TransactionTypeMO{" + "transactionTypeId='" + id + '\'' + '}';
  }
}
