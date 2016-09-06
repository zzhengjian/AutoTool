package com.gd.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table( name = "BudgetItem" )
public class Budget
{
	
	@Id
	private long budgetItemKey;
		
	private long accountKey;
	
	private int frequencyTypeKey;
	
	private int budgetItemTypeKey;
	
	private String budgetItem;
	
	private String description;
	
	private java.math.BigDecimal amount;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private boolean isActive;
	
	public long getBudgetItemKey() {
		return budgetItemKey;
	}

	public void setBudgetItemKey(long budgetItemKey) {
		this.budgetItemKey = budgetItemKey;
	}

	public long getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}

	public int getFrequencyTypeKey() {
		return frequencyTypeKey;
	}

	public void setFrequencyTypeKey(int frequencyTypeKey) {
		this.frequencyTypeKey = frequencyTypeKey;
	}

	public int getBudgetItemTypeKey() {
		return budgetItemTypeKey;
	}

	public void setBudgetItemTypeKey(int budgetItemTypeKey) {
		this.budgetItemTypeKey = budgetItemTypeKey;
	}

	public String getBudgetItem() {
		return budgetItem;
	}

	public void setBudgetItem(String budgetItem) {
		this.budgetItem = budgetItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public java.math.BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
		
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public static List<Budget> getBudgetByJPQL(String sJPQL, ATFEntityManager oEm,Object...params )
	{
		Query query = oEm.createQuery(sJPQL);
        return (List<Budget>)query.getResultList();
	}
	
	
		
}
