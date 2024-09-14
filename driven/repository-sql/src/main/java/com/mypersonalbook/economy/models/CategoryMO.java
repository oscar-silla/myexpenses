package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "d_category")
public class CategoryMO {
  @Id
  @Column(name = "category_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "category_name")
  private String name;

  @Column(name = "category_type")
  private String type;

  public CategoryMO() {}

  public CategoryMO(Long id, String name, String type) {
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
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
