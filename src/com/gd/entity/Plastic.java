package com.gd.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table( name = "Plastic" )
public class Plastic 
{
	@Id
	private long plasticKey;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date expirationDate;
	
	private boolean isPersonalized;
	
	private String emBossedName;
	
	private boolean hasCRV;
	
	@Temporal(TemporalType.DATE)
	private java.util.Date CRVRemovalDate;
	
	private boolean isActive;
	
	private long consumerProfileKey;
	
	private long cardKey;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDate;

	private long plasticStatusKey;
	
	
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		
		out.append("\n\n");
		out.append("Key:  " + plasticKey);
		out.append("\n");
		out.append("Expiration:  " + expirationDate);
		out.append("\n");
		out.append("Personalized:  " + isPersonalized);
		out.append("\n");
		out.append("HasCRV:  " + hasCRV);
		out.append("\n");
		out.append("Active:  " + isActive);
		out.append("\n");
		out.append("CreateDate:  " + createDate);
		out.append("\n");
		
		return out.toString();
	}
	
	///////////////////////////
	//  Getter and setters   //
	///////////////////////////
	
	public long getPlasticKey() {
		return plasticKey;
	}

	public void setPlasticKey(long plasticKey) {
		this.plasticKey = plasticKey;
	}

	public java.util.Date getExpirationDate() {
		return expirationDate;
	}

	public String getExpirationDate(String format){
		return new SimpleDateFormat(format).format(expirationDate);
	}
	
	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isPersonalized() {
		return isPersonalized;
	}

	public void setPersonalized(boolean isPersonalized) {
		this.isPersonalized = isPersonalized;
	}

	public String getEmBossedName() {
		return emBossedName;
	}

	public void setEmBossedName(String emBossedName) {
		this.emBossedName = emBossedName;
	}

	public boolean isHasCRV() {
		return hasCRV;
	}

	public void setHasCRV(boolean hasCRV) {
		this.hasCRV = hasCRV;
	}

	public java.util.Date getCRVRemovalDate() {
		return CRVRemovalDate;
	}

	public void setCRVRemovalDate(java.util.Date cRVRemovalDate) {
		CRVRemovalDate = cRVRemovalDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getConsumerProfileKey() {
		return consumerProfileKey;
	}

	public void setConsumerProfileKey(long consumerProfileKey) {
		this.consumerProfileKey = consumerProfileKey;
	}

	public long getCardKey() {
		return cardKey;
	}

	public void setCardKey(long cardKey) {
		this.cardKey = cardKey;
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
	
	public long getPlasticStatusKey() {
		return plasticStatusKey;
	}

	public void setPlasticStatusKey(long plasticStatusKey) {
		this.plasticStatusKey = plasticStatusKey;
	}

}
