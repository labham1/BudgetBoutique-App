package com.budgetbotique.expensemanagementservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.budgetbotique.expensemanagementservice.model.Expense;
import com.budgetbotique.expensemanagementservice.repository.ExpenseRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExpenseService.class);

    public List<Expense> getUserExpenses(String email) {
        try {
            return expenseRepository.findByemail(email);
        } catch (Exception e) {
            logger.error("Error getting user expenses for userId: {}", email, e);
            throw e;
        }
    }

    public List<Expense> filterExpenses(String category, String subcategory, Integer year, Integer month, Integer week) {
        try {
            LocalDate startDate;
            LocalDate endDate;

            if (year != null && month != null && week != null) {
                TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
                LocalDate firstDayOfSelectedWeek = firstDayOfYear.with(woy, week);
                startDate = firstDayOfSelectedWeek.minusDays(1);
                endDate = firstDayOfSelectedWeek.plusDays(6);

                // Adjust end date to include the entire last day
                endDate = endDate.plusDays(1);
            } else {
                startDate = LocalDate.of(1970, 1, 1);
                endDate = LocalDate.now();
            }

            if (year != null && month != null) {
                // Adjust the start date to the first day of the specified month
                startDate = LocalDate.of(year, month, 1);

                // Adjust the end date to the last day of the specified month
                endDate = startDate.plusMonths(1).minusDays(1);
            }

            List<Expense> filteredExpenses = expenseRepository.findByCategoryAndSubcategoryAndDateBetween(
                    category, subcategory, startDate, endDate);

            // Log the number of filtered expenses
            logger.info("Number of filtered expenses: {}", filteredExpenses.size());

            return filteredExpenses;
        } catch (Exception e) {
            logger.error("Error filtering expenses", e);
            throw e;
        }
    }

    
    public Expense addExpense(Expense expense) {
        try {
            return expenseRepository.save(expense);
        } catch (Exception e) {
            logger.error("Error adding expense: {}", expense, e);
            throw e;
        }
    }

    public Expense getExpenseById(Long expenseId) throws Exception {
        try {
            return expenseRepository.findById(expenseId)
                    .orElseThrow(() -> new NotFoundException());
        } catch (Exception e) {
            logger.error("Error getting expense details for expenseId: {}", expenseId, e);
            throw e;
        }
    }

    public Expense editExpense(Long expenseId, Expense updatedExpense) throws Exception {
        try {
            Expense existingExpense = getExpenseById(expenseId);
            // Update existingExpense fields with updatedExpense fields
            // ...
            return expenseRepository.save(existingExpense);
        } catch (Exception e) {
            logger.error("Error editing expense for expenseId: {}", expenseId, e);
            throw e;
        }
    }

    public void deleteExpense(Long expenseId) {
        try {
            expenseRepository.deleteById(expenseId);
        } catch (Exception e) {
            logger.error("Error deleting expense for expenseId: {}", expenseId, e);
            throw e;
        }
    }
}
