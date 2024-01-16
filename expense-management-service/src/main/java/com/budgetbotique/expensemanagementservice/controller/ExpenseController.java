package com.budgetbotique.expensemanagementservice.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.budgetbotique.expensemanagementservice.model.Expense;
import com.budgetbotique.expensemanagementservice.security.JwtService;
import com.budgetbotique.expensemanagementservice.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

 @Autowired
 private ExpenseService expenseService;
 
 @Autowired
 private JwtService jwtService;

 private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExpenseController.class);

 @GetMapping("/user/getExpense")
 public ResponseEntity<List<Expense>> getUserExpenses(@RequestHeader("Authorization") String token) {
     String email = extractUsernameFromToken(token);
     try {
         List<Expense> expenses = expenseService.getUserExpenses(email);
         return ResponseEntity.ok(expenses);
     } catch (Exception e) {
         logger.error("Error getting user expenses for email: {}", email, e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }


 @PostMapping("/add")
 public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, @RequestHeader("Authorization") String token) {
     String username = extractUsernameFromToken(token);
     try {
         // Assuming you have a 'userId' field in Expense
         expense.setEmail(username);
         
         Expense addedExpense = expenseService.addExpense(expense);
         return ResponseEntity.ok(addedExpense);
     } catch (Exception e) {
         logger.error("Error adding expense: {}", expense, e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }


 @GetMapping("getExpenseDetails/{expenseId}")
 public ResponseEntity<Expense> getExpenseDetails(@PathVariable Long expenseId) {
     try {
         Expense expense = expenseService.getExpenseById(expenseId);
         return ResponseEntity.ok(expense);
     } catch (Exception e) {
         logger.error("Error getting expense details for expenseId: {}", expenseId, e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }

 @PutMapping("/edit/{expenseId}")
 public ResponseEntity<Expense> editExpense(@PathVariable Long expenseId, @RequestBody Expense updatedExpense) {
     try {
         Expense editedExpense = expenseService.editExpense(expenseId, updatedExpense);
         return ResponseEntity.ok(editedExpense);
     } catch (Exception e) {
         logger.error("Error editing expense for expenseId: {}", expenseId, e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }

 @DeleteMapping("/delete/{expenseId}")
 public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId) {
     try {
         expenseService.deleteExpense(expenseId);
         return ResponseEntity.ok("Expense deleted successfully");
     } catch (Exception e) {
         logger.error("Error deleting expense for expenseId: {}", expenseId, e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
 }
 
 private String extractUsernameFromToken(String token) {
     // Assuming your token is in the format "Bearer YOUR_TOKEN"
     if (token.startsWith("Bearer ")) {
         String jwtToken = token.substring(7); // Remove "Bearer "
         return jwtService.extractUsernameFromToken(jwtToken);
     } else {
         // Handle invalid token format
         return null;
     }
 }
}
