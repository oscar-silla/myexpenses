package com.mypersonalbook.economy.ports.in;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.models.response.ExpenseDateResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface GetExpensesUseCasePort {
    Page<ExpenseDateResponse> execute(Integer pageSize, Integer pageNumber, LocalDate startDate, LocalDate endDate);
}
