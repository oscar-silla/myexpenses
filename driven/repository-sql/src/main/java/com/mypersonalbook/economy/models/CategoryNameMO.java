package com.mypersonalbook.economy.models;

import com.mypersonalbook.economy.models.ids.CategoryNameId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "d_category_names")
public class CategoryNameMO {
  @EmbeddedId
  private CategoryNameId id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @MapsId("id")
  @JoinColumn(name = "category_id")
  private CategoryMO category;

  public CategoryNameMO(CategoryNameId id, String name, CategoryMO category) {
    this.id = id;
    this.name = name;
    this.category = category;
  }

  public CategoryNameMO() {}

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    CategoryNameMO that = (CategoryNameMO) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CategoryNameId getId() {
    return this.id;
  }

  public void setId(CategoryNameId id) {
    this.id = id;
  }

  public CategoryMO getCategory() {
    return this.category;
  }

  public void setCategory(CategoryMO category) {
    this.category = category;
  }
}
