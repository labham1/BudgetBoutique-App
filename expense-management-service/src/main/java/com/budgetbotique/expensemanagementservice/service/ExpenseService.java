package com.budgetbotique.expensemanagementservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.budgetbotique.expensemanagementservice.model.Expense;
import com.budgetbotique.expensemanagementservice.repository.ExpenseRepository;

import java.util.List;

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
