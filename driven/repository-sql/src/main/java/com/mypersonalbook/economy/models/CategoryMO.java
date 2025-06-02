package com.mypersonalbook.economy.models;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity(name = "CategoryMO")
@Audited
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

  @Column(name = "color", nullable = false)
  private String color;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
  private UserMO user;

  public CategoryMO() {}

  public CategoryMO(Long id, String name, UserMO user, String color) {
    this.id = id;
    this.name = name;
    this.user = user;
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

  public UserMO getUser() {
    return user;
  }

  public void setUser(UserMO user) {
    this.user = user;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
