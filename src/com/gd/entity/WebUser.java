package com.gd.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table( name = "WebUser" )
public class WebUser 
{
	@Id
	private int webUserKey;
	
	private String userID;
	
	@Transient
	private String password = "NECTESTAU0";
	
	private String passwordHash;
	
	private int webSiteKey;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date passwordExpirationDate;
	
	private boolean isOneTimePassword;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	private long consumerProfileKey;
	
	@JoinColumn(name="webUserKey", referencedColumnName="webUserKey", insertable=false, updatable=false)
	private WebUserAuth webUserAuthentication = new WebUserAuth();

	///////////////////////////
	//  Helper methods       //
	///////////////////////////
	
	boolean equals(WebUser oWu)
	{
		return this.webSiteKey == oWu.getWebUserKey() &&
				this.userID.equals(oWu.getUserID()) &&
				this.webSiteKey == oWu.getWebSiteKey() &&
				this.isOneTimePassword == oWu.isOneTimePassword() &&
				this.consumerProfileKey == oWu.getConsumerProfileKey();
	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public WebUserAuth getWebUserAuth()
	{
		return webUserAuthentication;
	}
	
	public int getWebUserKey() {
		return webUserKey;
	}

	public void setWebUserKey(int webUserKey) {
		this.webUserKey = webUserKey;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWebSiteKey() {
		return webSiteKey;
	}

	public void setWebSiteKey(int webSiteKey) {
		this.webSiteKey = webSiteKey;
	}

	public java.util.Date getPasswordExpirationDate() {
		return passwordExpirationDate;
	}

	public void setPasswordExpirationDate(java.util.Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
	}

	public boolean isOneTimePassword() {
		return isOneTimePassword;
	}

	public void setOneTimePassword(boolean isOneTimePassword) {
		this.isOneTimePassword = isOneTimePassword;
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

	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	} 
}
