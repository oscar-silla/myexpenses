package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.models.CategoryMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static com.mypersonalbook.economy.utils.test.mocks.filters.CategoryFilterMock.CATEGORY_FILTER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CategorySpecificationImplTest {
  CategorySpecification categorySpecification;

  @BeforeEach
  void setUp() {
    this.categorySpecification = new CategorySpecificationImpl();
  }

  @Test
  @DisplayName("Should return specification")
  void shouldReturnSpecification() {
    Specification<CategoryMO> specification =
        this.categorySpecification.getSpecification(CATEGORY_FILTER);
    assertNotNull(specification);
  }
}
