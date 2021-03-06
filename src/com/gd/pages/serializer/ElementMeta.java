package com.gd.pages.serializer;

public class ElementMeta {
	private String key;
	private String value;
	private String inlineComment;
	
	private String platform = ""; 

	
	public ElementMeta(String key, String value) {
		this(key, value, "");
	}
	
	public ElementMeta(String key, String value, String inlineComment) {
		this.key = key;
		this.value = value;
		this.inlineComment = inlineComment;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getInlineComment() {
		return inlineComment;
	}
	public void setInlineComment(String inlineComment) {
		this.inlineComment = inlineComment;
	}
	
	

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementMeta other = (ElementMeta) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform))
			return false;
		return true;
	}	
	
	
}
