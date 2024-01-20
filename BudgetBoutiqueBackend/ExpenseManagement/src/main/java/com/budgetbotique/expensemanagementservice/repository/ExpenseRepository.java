package com.budgetbotique.expensemanagementservice.repository;

// ExpenseRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.budgetbotique.expensemanagementservice.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByemail(String email);
    List<Expense> findByCategoryAndSubcategoryAndDateBetween(
            String category, String subcategory, LocalDate startDate, LocalDate endDate
        );
}

