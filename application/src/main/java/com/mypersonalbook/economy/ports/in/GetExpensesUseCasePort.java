package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Expense;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface GetExpensesUseCasePort {
    Page<Expense> execute(Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate);
}
