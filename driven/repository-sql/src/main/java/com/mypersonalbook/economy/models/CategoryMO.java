package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "o_categories")
public class CategoryMO {
  @Id
  @Column(name = "category_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "o_categories_seq")
  @SequenceGenerator(
      name = "o_categories_seq",
      sequenceName = "O_CATEGORIES_SEQ",
      allocationSize = 1)
  private Long id;

  @Column(name = "category_name")
  private String name;

  @OneToOne
  @JoinColumn(name = "transaction_type_id")
  private TransactionTypeMO type;

  public CategoryMO() {}

  public CategoryMO(Long id, String name, TransactionTypeMO type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TransactionTypeMO getType() {
    return type;
  }

  public void setType(TransactionTypeMO type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CategoryMO that = (CategoryMO) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type);
  }
}
