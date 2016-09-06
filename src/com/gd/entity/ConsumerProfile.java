package com.gd.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table( name = "ConsumerProfile" )
public class ConsumerProfile 
{
	@Id
	private String consumerProfileKey;
	
	private int consumerKey;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinTable(name="ConsumerProfile_Account", joinColumns={@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey")}, inverseJoinColumns={@JoinColumn(name="AccountKey", referencedColumnName="AccountKey")})
	private Customer customer = new Customer();
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinTable(name="ConsumerProfile_Account", joinColumns={@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey")}, inverseJoinColumns={@JoinColumn(name="AccountKey", referencedColumnName="AccountKey")})
	private Account account = new Account();
	
	@ManyToMany(cascade=CascadeType.REFRESH)
	@JoinTable(name="ConsumerProfile_Account", joinColumns={@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey")}, inverseJoinColumns={@JoinColumn(name="AccountKey", referencedColumnName="AccountKey")})
	private List<Budget> budget = new ArrayList<Budget>(); 
	
	@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey", insertable=false, updatable=false)
	private Plastic plastic = new Plastic();
	
	@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey", insertable=false, updatable=false)
	private Identification identification;
	
	public Plastic getPlastic() {
		return plastic;
	}

	public void setPlastic(Plastic plastic) {
		this.plastic = plastic;
	}

	public String getConsumerProfileKey() {
		return consumerProfileKey;
	}
	
	public void setConsumerProfileKey(String consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}
	
	public int getConsumerKey() {
		return consumerKey;
	}
	
	public void setConsumerKey(int consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getChangeDate() {
		return changeDate;
	}
	
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public List<Budget> getBudget() {
		return budget;
	}

	public void setBudget(List<Budget> budget) {
		this.budget = budget;
	}
	
	public Identification getIdentification() {
		return identification;
	}

	public void setIdentification(Identification identification) {
		this.identification = identification;
	}
	
}
