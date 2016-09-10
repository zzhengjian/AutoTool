package com.gd.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table( name = "WebUserAuthentication" )
public class WebUserAuth 
{
	@Id
	private long webUserKey;
	
	@Temporal(TemporalType.DATE)
	private Date lastSuccessfulLoginTime;
	
	@Temporal(TemporalType.DATE)
	private Date lastFailedLoginTime;
	
	private int failedLoginAttempts;
	
	private boolean isLocked;
	
	@Temporal(TemporalType.DATE)
	private Date lockedTime;
	
	private int lifetimeLockoutCount;
	
	private int failedAnswerQuestions;
	
	@Temporal(TemporalType.DATE)
	private Date lastFailedAnswerQuestionsTime;
	
	private int failedSecurityAnswerCount;
	

	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getWebUserKey() {
		return webUserKey;
	}

	public void setWebUserKey(long webUserKey) {
		this.webUserKey = webUserKey;
	}

	public Date getLastSuccessfulLoginTime() {
		return lastSuccessfulLoginTime;
	}

	public void setLastSuccessfulLoginTime(Date lastSuccessfulLoginTime) {
		this.lastSuccessfulLoginTime = lastSuccessfulLoginTime;
	}

	public Date getLastFailedLoginTime() {
		return lastFailedLoginTime;
	}

	public void setLastFailedLoginTime(Date lastFailedLoginTime) {
		this.lastFailedLoginTime = lastFailedLoginTime;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Date getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}

	public int getLifetimeLockoutCount() {
		return lifetimeLockoutCount;
	}

	public void setLifetimeLockoutCount(int lifetimeLockoutCount) {
		this.lifetimeLockoutCount = lifetimeLockoutCount;
	}

	public int getFailedAnswerQuestions() {
		return failedAnswerQuestions;
	}

	public void setFailedAnswerQuestions(int failedAnswerQuestions) {
		this.failedAnswerQuestions = failedAnswerQuestions;
	}

	public Date getLastFailedAnswerQuestionsTime() {
		return lastFailedAnswerQuestionsTime;
	}

	public void setLastFailedAnswerQuestionsTime(Date lastFailedAnswerQuestionsTime) {
		this.lastFailedAnswerQuestionsTime = lastFailedAnswerQuestionsTime;
	}

	public int getFailedSecurityAnswerCount() {
		return failedSecurityAnswerCount;
	}

	public void setFailedSecurityAnswerCount(int failedSecurityAnswerCount) {
		this.failedSecurityAnswerCount = failedSecurityAnswerCount;
	}

	
		
}
