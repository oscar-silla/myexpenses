package com.mypersonalbook.economy.models;

import jakarta.persistence.*;

import java.util.List;

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

  @OneToOne
  @JoinColumn(name = "transaction_type_id")
  private TransactionTypeMO type;

  @OneToMany(mappedBy = "category")
  private List<CategoryNameMO> names;

  public CategoryMO() {}

  public CategoryMO(Long id, TransactionTypeMO type, List<CategoryNameMO> names) {
    this.id = id;
    this.type = type;
    this.names = names;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TransactionTypeMO getType() {
    return type;
  }

  public void setType(TransactionTypeMO type) {
    this.type = type;
  }

  public List<CategoryNameMO> getNames() {
    return this.names;
  }

  public void setNames(List<CategoryNameMO> names) {
    this.names = names;
  }
}
