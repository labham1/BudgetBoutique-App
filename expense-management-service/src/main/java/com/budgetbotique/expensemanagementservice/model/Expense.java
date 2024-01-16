package com.budgetbotique.expensemanagementservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Expense {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String email;
 private LocalDate date;
 private BigDecimal amount;
 private Long categoryId;
 private Long subcategoryId;
 private String notes;

 // getters and setters

 @Override
 public String toString() {
     return "Expense{" +
            "id=" + id +
            ", email=" + email +
            ", date=" + date +
            ", amount=" + amount +
            ", categoryId=" + categoryId +
            ", subcategoryId=" + subcategoryId +
            ", notes='" + notes + '\'' +
            '}';
 }

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public LocalDate getDate() {
	return date;
}

public void setDate(LocalDate date) {
	this.date = date;
}

public BigDecimal getAmount() {
	return amount;
}

public void setAmount(BigDecimal amount) {
	this.amount = amount;
}

public Long getCategoryId() {
	return categoryId;
}

public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
}

public Long getSubcategoryId() {
	return subcategoryId;
}

public void setSubcategoryId(Long subcategoryId) {
	this.subcategoryId = subcategoryId;
}

public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}
}
