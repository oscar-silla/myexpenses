package com.mypersonalbook.economy.domain;

import java.time.LocalDate;

public record Expense(Long id, Float amount, String category, String description, LocalDate date) {}
