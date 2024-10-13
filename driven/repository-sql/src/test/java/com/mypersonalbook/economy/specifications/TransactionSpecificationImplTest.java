package com.mypersonalbook.economy.specifications;

import com.mypersonalbook.economy.models.TransactionMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static com.mypersonalbook.economy.utils.test.mocks.filters.TransactionFilterMock.TRANSACTION_FILTER;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TransactionSpecificationImplTest {
  TransactionSpecification transactionSpecification;

  @BeforeEach
  void setUp() {
    this.transactionSpecification = new TransactionSpecificationImpl();
  }

  @Test
  @DisplayName("Should return specification")
  void shouldReturnSpecification() {
    Specification<TransactionMO> specification =
        this.transactionSpecification.getSpecification(TRANSACTION_FILTER);
    assertNotNull(specification);
  }
}
