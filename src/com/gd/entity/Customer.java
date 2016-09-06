package com.gd.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
//@Table( name = "Customer" )
public class Customer 
{
	@Id
	private int customerKey;
	
	private String SSN;
	
	private String cellPhone;
	
	private String email;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private String card2FirstName;
	
	private String card2LastName;
	
	private String firstName;
	
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date DOB;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date Card2DOB;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date cardActivationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date cardExpDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date plasticsRequest;
	
	private int accountkey;
	
	private String serialNbr;
	
	private java.math.BigDecimal currBalance;
	
	private java.math.BigDecimal acctBalance;
	
	private int activationStatusKey;
	
	private String creditRatingKey;
	
	private int plasticIssueTypeKey;
	
	private int productKey; 
	
	private boolean hasCRV;

	private String atmPin;
	
	private String homePhone;
	
	private String residentialAddress1;
	
	private String residentialAddress2;
	
	private String residentialCity;
	
	private String residentialState;
	
	private String residentialZipCode;
	
	private String cardReference;
	
	@Transient
	private String decryptedSerialNbr = null;
	
	public void setDecryptedSerialNbr(String decryptedSerialNbr) {
		this.decryptedSerialNbr = decryptedSerialNbr;
	}

	public List<TSysQueue> gettSysQueueList() {
		return tSysQueueList;
	}

	public void settSysQueueList(List<TSysQueue> tSysQueueList) {
		this.tSysQueueList = tSysQueueList;
	}

	@ManyToMany(cascade=CascadeType.REFRESH)
	@JoinColumn(name="customerKey", referencedColumnName="customerKey", insertable=false, updatable=false)
	@OrderBy("requestDate DESC")
	private List<TSysQueue> tSysQueueList = new ArrayList<TSysQueue>();
	
	@JoinColumn(name="customerKey", referencedColumnName="customerKey", insertable=false, updatable=false)
	private Card card = new Card();
	
	@ManyToMany(cascade=CascadeType.REFRESH)
	@JoinTable(name="card", joinColumns={@JoinColumn(name="CustomerKey", referencedColumnName="CustomerKey")}, inverseJoinColumns={@JoinColumn(name="CardKey", referencedColumnName="CardKey")})
	@OrderBy("plasticKey DESC")
	private List<Plastic> plastics = new ArrayList<Plastic>();
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinTable(name="ConsumerProfile_Account", joinColumns={@JoinColumn(name="AccountKey", referencedColumnName="AccountKey")}, inverseJoinColumns={@JoinColumn(name="consumerProfileKey", referencedColumnName="consumerProfileKey")})
	private ConsumerProfile consumerProfile;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinTable(name="WebUserAccount", joinColumns={@JoinColumn(name="AccountKey", referencedColumnName="AccountKey")}, inverseJoinColumns={@JoinColumn(name="WebUserKey", referencedColumnName="WebUserKey")})
	private WebUser webUser;
	
	
	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	boolean equals(Customer oCust)
	{
		return this.SSN.equals(oCust.getSSN()) &&
				this.DOB == oCust.getDOB() &&
				this.cellPhone.equals(oCust.getCellPhone()) &&
				this.email.equals(oCust.getEmail());
	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public int getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(int customerKey) {
		this.customerKey = customerKey;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getDOB() {
		return DOB;
	}
	
	public java.util.Date getCard2DOB() {
		return Card2DOB;
	}

	public void setDOB(java.util.Date dOB) {
		DOB = dOB;
	}

	public java.util.Date getCardActivationDate() {
		return cardActivationDate;
	}

	public void setCardActivationDate(java.util.Date cardActivationDate) {
		this.cardActivationDate = cardActivationDate;
	}

	public int getAccountkey() {
		return accountkey;
	}

	public void setAccountkey(int accountkey) {
		this.accountkey = accountkey;
	}

	public String getSerialNbr() {
		return serialNbr;
	}

	public String getDecryptedSerialNbr() {
		if (decryptedSerialNbr == null)	
			decryptedSerialNbr = null; //SysTools.decrypt(serialNbr);
	
		return decryptedSerialNbr;
	}
	
	public void setSerialNbr(String serialNbr) {
		this.serialNbr = serialNbr;
	}

	public java.math.BigDecimal getCurrBalance() {
		return currBalance;
	}

	public void setCurrBalance(java.math.BigDecimal currBalance) {
		this.currBalance = currBalance;
	}

	public java.math.BigDecimal getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(java.math.BigDecimal acctBalance) {
		this.acctBalance = acctBalance;
	}

	public int getActivationStatusKey() {
		return activationStatusKey;
	}

	public void setActivationStatusKey(int activationStatusKey) {
		this.activationStatusKey = activationStatusKey;
	}

	public String getCreditRatingKey() {
		return creditRatingKey;
	}

	public void setCreditRatingKey(String creditRatingKey) {
		this.creditRatingKey = creditRatingKey;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getPlasticIssueTypeKey() {
		return plasticIssueTypeKey;
	}

	public void setPlasticIssueTypeKey(int plasticIssueTypeKey) {
		this.plasticIssueTypeKey = plasticIssueTypeKey;
	}

	public void setCardExpDate(java.util.Date cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public boolean getHasCRV() {
		return hasCRV;
	}

	public void setHasCRV(boolean hasCRV) {
		this.hasCRV = hasCRV;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCard2FirstName() {
		return card2FirstName;
	}

	public void setCard2FirstName(String card2FirstName) {
		this.card2FirstName = card2FirstName;
	}

	public String getAtmPin() {
		return atmPin;
	}

	public void setAtmPin(String atmPin) {
		this.atmPin = atmPin;
	}

	public String getCard2LastName() {
		return card2LastName;
	}

	public void setCard2LastName(String card2LastName) {
		this.card2LastName = card2LastName;
	}
	
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getResidentialAddress1() {
		return residentialAddress1;
	}

	public void setResidentialAddress1(String residentialAddress1) {
		this.residentialAddress1 = residentialAddress1;
	}

	public String getResidentialAddress2() {
		return residentialAddress2;
	}

	public void setResidentialAddress2(String residentialAddress2) {
		this.residentialAddress2 = residentialAddress2;
	}

	public String getResidentialCity() {
		return residentialCity;
	}

	public void setResidentialcity(String residentialCity) {
		this.residentialCity = residentialCity;
	}

	public String getResidentialState() {
		return residentialState;
	}

	public void setResidentialstate(String residentialState) {
		this.residentialState = residentialState;
	}

	public String getResidentialZipCode() {
		return residentialZipCode;
	}

	public void setResidentialzipCode(String residentialZipCode) {
		this.residentialZipCode = residentialZipCode;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ConsumerProfile getConsumerProfile() {
		return consumerProfile;
	}
	
	public void setConsumerProfile(ConsumerProfile consumerProfile) {
		this.consumerProfile = consumerProfile;
	}	
	
	public int getProductKey() {
		return productKey;
	}

	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}

	public java.util.Date getPlasticsRequest() {
		return plasticsRequest;
	}

	public void setPlasticsRequest(java.util.Date plasticsRequest) {
		this.plasticsRequest = plasticsRequest;
	}
	
	public Address getMailingAddress()
	{
		Address oAddress = new Address();
		oAddress.setAddress1(this.address1);
		if(this.address2 == null)
			oAddress.setAddress2("");
		else
			oAddress.setAddress2(this.address2);
		oAddress.setCity(this.city);
		oAddress.setState(this.state);
		oAddress.setZipCode(this.zipCode.substring(0, 5));
		return oAddress;
	}
	
	public Address getResidentialAddress()
	{
		Address oAddress = new Address();
		oAddress.setAddress1(this.residentialAddress1);
		if(this.address2 == null)
			oAddress.setAddress2("");
		else
			oAddress.setAddress2(this.residentialAddress2);
		oAddress.setCity(this.residentialCity);
		oAddress.setState(this.residentialState);
		oAddress.setZipCode(this.residentialZipCode.substring(0, 5));
		return oAddress;
	}
	
	public String getCardReference() {
		return cardReference;
	}

	public void setCardReference(String cardReference) {
		this.cardReference = cardReference;
	}
	
	public String getUserID()
	{
		return webUser.getUserID();
	}
}
