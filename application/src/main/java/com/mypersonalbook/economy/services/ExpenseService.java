package com.mypersonalbook.economy.services;

import com.mypersonalbook.economy.domain.Expense;
import com.mypersonalbook.economy.ports.out.ExpenseRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private ExpenseRepositoryPort expenseRepository;

    public ExpenseService(ExpenseRepositoryPort expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void save(Expense expense) {
        
    }
}
