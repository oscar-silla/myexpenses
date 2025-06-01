package com.mypersonalbook.economy.adapters;

import com.mypersonalbook.economy.application.ports.driving.category.GetCategoriesUseCasePort;
import com.mypersonalbook.economy.domain.Category;
import com.mypersonalbook.economy.mappers.CategoryControllerMapper;
import openapi.economy.api.CategoriesApi;
import openapi.economy.model.CategoriesResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/economy/v1")
public class CategoryControllerAdapter implements CategoriesApi {
  Logger log = LoggerFactory.getLogger(CategoryControllerAdapter.class);
  private final CategoryControllerMapper categoryControllerMapper;
  private final GetCategoriesUseCasePort getCategoriesUseCasePort;

  public CategoryControllerAdapter(
      CategoryControllerMapper categoryControllerMapper,
      GetCategoriesUseCasePort getCategoriesUseCasePort) {
    this.categoryControllerMapper = categoryControllerMapper;
    this.getCategoriesUseCasePort = getCategoriesUseCasePort;
  }

  @Override
  public ResponseEntity<CategoriesResponseType> getCategories(
      Integer pageSize, Integer pageNumber) {
    log.info("GET /economy/v1/categories with pageSize: {}, pageNumber: {}", pageSize, pageNumber);
    Page<Category> categories = this.getCategoriesUseCasePort.getCategories(pageNumber, pageSize);
    return ResponseEntity.ok(this.categoryControllerMapper.toCategoriesResponseType(categories));
  }
}
