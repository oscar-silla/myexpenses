package com.mypersonalbook.economy.utils;

import com.mypersonalbook.economy.exceptions.BadRequestException;
import org.slf4j.Logger;

import java.time.LocalDate;

public class Utils {
  public static void validateAndThrowDateRange(LocalDate startDate, LocalDate endDate, Logger logger) {
    if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
      logger.error("Wrong date range, startDate: {}, endDate: {}", startDate, endDate);
      throw new BadRequestException();
    }
  }
  public static void validateAndThrowPagination(Integer pageNumber, Integer pageSize, Logger logger) {
    if (pageNumber.equals(0)) {
      logger.error("Page index must not be less than one");
      throw new BadRequestException();
    }
    if (pageSize.equals(0)) {
      logger.error("Page size must not be less than one");
      throw new BadRequestException();
    }
  }
}