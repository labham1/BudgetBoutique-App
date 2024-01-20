package com.budgetbotique.expensemanagementservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expense_data")
public class Expense {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String email;
 private LocalDate date;
 private BigDecimal amount;
 private String category;
 private String subcategory;
 private String notes;

 // getters and setters

 @Override
 public String toString() {
     return "Expense{" +
            "id=" + id +
            ", email=" + email +
            ", date=" + date +
            ", amount=" + amount +
            ", categoryId=" + category +
            ", subcategoryId=" + subcategory +
            ", notes='" + notes + '\'' +
            '}';
 }

public String getSubcategory() {
	return subcategory;
}

public void setSubcategory(String subcategory) {
	this.subcategory = subcategory;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
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



public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}
}
