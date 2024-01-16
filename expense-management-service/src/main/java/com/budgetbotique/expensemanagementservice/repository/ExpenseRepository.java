package com.budgetbotique.expensemanagementservice.repository;

// ExpenseRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.budgetbotique.expensemanagementservice.model.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByemail(String email);
}

