package com.mypersonalbook.economy.domain;

public record Category(Long id, String name, String type) {
  public Category setType(String type) {
    return new Category(this.id, this.name, type);
  }
}
