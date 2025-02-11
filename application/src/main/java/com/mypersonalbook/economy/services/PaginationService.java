package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.models.response.pagination.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
  public PaginationService() {}

  public <T> PaginationResponse buildPaginationResponse(Page<T> page) {
    return new PaginationResponse(
        page.getNumber() + 1, page.getSize(), (int) page.getTotalElements());
  }
}
