package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "o_transactions")
public class TransactionMO {
  @Id
  @Column(name = "transaction_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "o_transactions_seq")
  @SequenceGenerator(
      name = "o_transactions_seq",
      sequenceName = "O_TRANSACTIONS_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "transaction_amount")
  private Float amount;

  @Column(name = "transaction_description")
  private String description;

  @Column(name = "transaction_date")
  private LocalDate date;

  @OneToOne
  @JoinColumn(name = "transaction_type_id")
  private TransactionTypeMO type;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryMO category;

  public TransactionMO() {}

  public TransactionMO(
      Long id,
      Float amount,
      String description,
      LocalDate date,
      TransactionTypeMO type,
      CategoryMO category) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.date = date;
    this.type = type;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public TransactionTypeMO getType() {
    return type;
  }

  public void setType(TransactionTypeMO type) {
    this.type = type;
  }

  public CategoryMO getCategory() {
    return category;
  }

  public void setCategory(CategoryMO category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionMO transactionMO = (TransactionMO) o;
    return Objects.equals(id, transactionMO.id)
        && Objects.equals(amount, transactionMO.amount)
        && Objects.equals(description, transactionMO.description)
        && Objects.equals(date, transactionMO.date)
        && Objects.equals(category, transactionMO.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, description, date, category);
  }
}
