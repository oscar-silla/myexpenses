package com.mypersonalbook.economy.domain;

public class Category {
  Long id;
  String name;
  String type;
  String color;

  public Category() {}

  public Category(Long id, String name, String type, String color) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.color = color;
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Category{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", type='"
        + type
        + '\''
        + ", color='"
        + color
        + '\''
        + '}';
  }
}
