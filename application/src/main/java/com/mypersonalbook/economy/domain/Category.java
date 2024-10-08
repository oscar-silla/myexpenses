package com.mypersonalbook.economy.domain;

public class Category {
  Long id;
  String name;
  String type;

  public Category() {}

  public Category(Long id, String name, String type) {
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
  public String toString() {
    return "Category{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type + '\'' + '}';
  }
}
