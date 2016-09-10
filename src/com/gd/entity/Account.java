package com.gd.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "Account" )
public class Account 
{
	@Id
	private int AccountKey;
	
	@Temporal(TemporalType.DATE)
	private Date openedDate;
	
	@Temporal(TemporalType.DATE)
	private Date closedDate;
	
	private String accountExternalId;
	
	private java.math.BigDecimal availableBalance;
	
	@Temporal(TemporalType.DATE)
	private Date availableBalanceLastUpdated;
	
	private java.math.BigDecimal currentBalance;
	
	@Temporal(TemporalType.DATE)
	private Date currentBalanceLastUpdated;
	
	private int accountStatusKey;
	
	@Temporal(TemporalType.DATE)
	private Date accountStatusDate;
	
	private int lastAccountStatusKey;
	
	private int accountBaseTypeKey;
	
	private boolean isFunded;
	
	@Temporal(TemporalType.DATE)
	private Date initialFundedDate;
	
	public int getAccountKey() {
		return AccountKey;
	}

	public void setAccountKey(int accountKey) {
		AccountKey = accountKey;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getAccountExternalId() {
		return accountExternalId;
	}

	public void setAccountExternalId(String accountExternalId) {
		this.accountExternalId = accountExternalId;
	}

	public java.math.BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(java.math.BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getAvailableBalanceLastUpdated() {
		return availableBalanceLastUpdated;
	}

	public void setAvailableBalanceLastUpdated(
			Date availableBalanceLastUpdated) {
		this.availableBalanceLastUpdated = availableBalanceLastUpdated;
	}

	public java.math.BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(java.math.BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getCurrentBalanceLastUpdated() {
		return currentBalanceLastUpdated;
	}

	public void setCurrentBalanceLastUpdated(
			Date currentBalanceLastUpdated) {
		this.currentBalanceLastUpdated = currentBalanceLastUpdated;
	}

	public int getAccountStatusKey() {
		return accountStatusKey;
	}

	public void setAccountStatusKey(int accountStatusKey) {
		this.accountStatusKey = accountStatusKey;
	}

	public Date getAccountStatusDate() {
		return accountStatusDate;
	}

	public void setAccountStatusDate(Date accountStatusDate) {
		this.accountStatusDate = accountStatusDate;
	}

	public int getLastAccountStatusKey() {
		return lastAccountStatusKey;
	}

	public void setLastAccountStatusKey(int lastAccountStatusKey) {
		this.lastAccountStatusKey = lastAccountStatusKey;
	}

	public int getAccountBaseTypeKey() {
		return accountBaseTypeKey;
	}

	public void setAccountBaseTypeKey(int accountBaseTypeKey) {
		this.accountBaseTypeKey = accountBaseTypeKey;
	}

	public boolean getIsFunded() {
		return isFunded;
	}

	public void setIsFunded(boolean isFunded) {
		this.isFunded = isFunded;
	}

	public Date getInitialFundedDate() {
		return initialFundedDate;
	}

	public void setInitialFundedDate(Date initialFundedDate) {
		this.initialFundedDate = initialFundedDate;
	}

	public static List<Account> getAccountsByJPQL(String sJPQL, ATFEntityManager oEm, Object...params )
	{
		Query query = oEm.createQuery(sJPQL);
		
		if (params.length == 1)
			query.setMaxResults((int)params[0]);
		
		return (List<Account>)query.getResultList();
	}
	
	public static BigDecimal getSavingsAccountBalance(int iSavingsAccountNumber, ATFEntityManager oEm) 
	{
		String sSQL = "select AvailableBalance from account where accountkey ="+ iSavingsAccountNumber;
		return (BigDecimal)oEm.createNativeQuery(sSQL).getSingleResult();
	}
}
