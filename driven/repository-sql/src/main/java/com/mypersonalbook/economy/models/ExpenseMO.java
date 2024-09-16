package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "o_expenses")
public class ExpenseMO {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "o_expenses_seq")
  @SequenceGenerator(name = "o_expenses_seq", sequenceName = "O_EXPENSES_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "amount")
  private Float amount;

  @Column(name = "description")
  private String description;

  @Column(name = "date")
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private CategoryMO category;

  public ExpenseMO() {}

  public ExpenseMO(Long id, Float amount, String description, LocalDate date, CategoryMO category) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.date = date;
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
    ExpenseMO expenseMO = (ExpenseMO) o;
    return Objects.equals(id, expenseMO.id)
        && Objects.equals(amount, expenseMO.amount)
        && Objects.equals(description, expenseMO.description)
        && Objects.equals(date, expenseMO.date)
        && Objects.equals(category, expenseMO.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, description, date, category);
  }
}
