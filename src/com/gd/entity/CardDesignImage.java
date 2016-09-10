package com.gd.entity;
import javax.persistence.*;


@Entity
@Table( name = "CardDesignImage" )
public class CardDesignImage 
{	
	@Id
	private long cardDesignImageKey;
	
	private String imageReferenceID;
	
	private int imageStatusKey;
	
	private int cardDesignKey;
	
	private String consumerProfileKey;

	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getCardDesignImageKey() {
		return cardDesignImageKey;
	}

	public void setCardDesignImageKey(long cardDesignImageKey) {
		this.cardDesignImageKey = cardDesignImageKey;
	}

	public String getImageReferenceID() {
		return imageReferenceID;
	}

	public void setImageReferenceID(String imageReferenceID) {
		this.imageReferenceID = imageReferenceID;
	}

	public int getImageStatusKey() {
		return imageStatusKey;
	}

	public void setImageStatusKey(int imageStatusKey) {
		this.imageStatusKey = imageStatusKey;
	}

	public int getCardDesignKey() {
		return cardDesignKey;
	}

	public void setCardDesignKey(int cardDesignKey) {
		this.cardDesignKey = cardDesignKey;
	}

	public String getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(String consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}
	
	
}
