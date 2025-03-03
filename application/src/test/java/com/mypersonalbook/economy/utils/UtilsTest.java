package com.mypersonalbook.economy.utils;

import com.mypersonalbook.economy.application.exceptions.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static com.mypersonalbook.economy.utils.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

  @Test
  @DisplayName("Should validate date range when are same dates")
  void shouldValidateAndThrowDateRange_WhenAreSameDates() {
    Executable executable =
        () ->
            Utils.validateAndThrowDateRange(
                LocalDate.now(), LocalDate.now(), LoggerFactory.getLogger(UtilsTest.class));
    assertDoesNotThrow(executable);
  }

  @Test
  @DisplayName("Should validate date range when start date is before than end date")
  void shouldValidateDateRange_WhenStartDateIsBeforeThanEndAndThrowDate() {
    Executable executable =
        () ->
            Utils.validateAndThrowDateRange(
                START_DATE, END_DATE, LoggerFactory.getLogger(UtilsTest.class));
    assertDoesNotThrow(executable);
  }

  @Test
  @DisplayName("Should throw bad request exception when start date is after than end date")
  void shouldThrowBadRequestException_WhenStartDateIsAfterThanEndAndThrowDate() {
    Executable executable =
        () ->
            Utils.validateAndThrowDateRange(
                END_DATE, START_DATE, LoggerFactory.getLogger(UtilsTest.class));
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should throw bad request exception when page number is zero")
  void shouldThrowBadRequestException_WhenPageNumberIsZero() {
    Executable executable =
        () ->
            Utils.validateAndThrowPagination(
                0, PAGE_SIZE, LoggerFactory.getLogger(UtilsTest.class));
    assertThrows(BadRequestException.class, executable);
  }

  @Test
  @DisplayName("Should throw bad request exception when page size is zero")
  void shouldThrowBadRequestException_WhenPageSizeIsZero() {
    Executable executable =
        () ->
            Utils.validateAndThrowPagination(
                PAGE_NUMBER, 0, LoggerFactory.getLogger(UtilsTest.class));
    assertThrows(BadRequestException.class, executable);
  }
}
