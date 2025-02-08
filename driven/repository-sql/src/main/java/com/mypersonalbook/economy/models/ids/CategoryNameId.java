package com.mypersonalbook.economy.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CategoryNameId implements Serializable {
  @Column(name = "category_id")
  private Long id;

  @Column(name = "locale")
  private String locale;

  public CategoryNameId(Long id, String locale) {
    this.id = id;
    this.locale = locale;
  }

  public CategoryNameId() {}

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLocale() {
    return this.locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
