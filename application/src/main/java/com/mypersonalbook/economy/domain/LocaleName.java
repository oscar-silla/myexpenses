package com.mypersonalbook.economy.domain;

import java.util.Objects;

public class LocaleName {
  private String name;
  private String locale;

  public LocaleName() {}

  public LocaleName(String name, String locale) {
    this.name = name;
    this.locale = locale;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    LocaleName that = (LocaleName) o;
    return Objects.equals(name, that.name) && Objects.equals(locale, that.locale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, locale);
  }
}
