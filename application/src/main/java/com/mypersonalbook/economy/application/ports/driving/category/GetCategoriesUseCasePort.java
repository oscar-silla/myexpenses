package com.mypersonalbook.economy.application.ports.driving.category;

import com.mypersonalbook.economy.domain.Category;
import org.springframework.data.domain.Page;

public interface GetCategoriesUseCasePort {
  Page<Category> getCategories(Integer pageNumber, Integer pageSize);
}
