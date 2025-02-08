package com.mypersonalbook.economy.domain;

import java.util.List;

public class Category {
  Long id;
  List<LocaleName> names;
  String type;

  public Category() {}

  public Category(Long id, List<LocaleName> names, String type) {
    this.id = id;
    this.names = names;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<LocaleName> getNames() {
    return names;
  }

  public void setNames(List<LocaleName> names) {
    this.names = names;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Category{" + "id=" + id + ", name='" + names + '\'' + ", type='" + type + '\'' + '}';
  }
}
