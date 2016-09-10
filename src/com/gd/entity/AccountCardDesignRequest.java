package com.gd.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "AccountCardDesignRequest" )
public class AccountCardDesignRequest 
{
	@Id
	private long accountCardDesignRequestKey;
	
	private int accountCardDesignRequestStatusKey;

	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinTable(name="AccountCardDesignRequest_CardDesignImage", joinColumns={@JoinColumn(name="AccountCardDesignRequestKey", referencedColumnName="AccountCardDesignRequestKey")}, inverseJoinColumns={@JoinColumn(name="CardDesignImageKey", referencedColumnName="CardDesignImageKey")})
	private CardDesignImage cardDesignImage = new CardDesignImage();
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getAccountCardDesignRequestKey() {
		return accountCardDesignRequestKey;
	}

	public void setAccountCardDesignRequestKey(long accountCardDesignRequestKey) {
		this.accountCardDesignRequestKey = accountCardDesignRequestKey;
	}

	public int getAccountCardDesignRequestStatusKey() {
		return accountCardDesignRequestStatusKey;
	}

	public void setAccountCardDesignRequestStatusKey(
			int accountCardDesignRequestStatusKey) {
		this.accountCardDesignRequestStatusKey = accountCardDesignRequestStatusKey;
	}

	public CardDesignImage getCardDesignImage() {
		return cardDesignImage;
	}

	public void setCardDesignImage(CardDesignImage cardDesignImage) {
		this.cardDesignImage = cardDesignImage;
	}
	
	public int getImageStatusKey() {
		return cardDesignImage.getImageStatusKey();
	}
	
	public int getCardDesignKey() {
		return cardDesignImage.getCardDesignKey();
	}
}
