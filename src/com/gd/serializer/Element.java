package com.gd.serializer;

import java.util.ArrayList;
import java.util.List;

public class Element {
	
	private String elementName;
	private	String creationLine;
	private List<ElementMeta> metas = new ArrayList<ElementMeta>();
	
	public Element(String elementName) {
		this.elementName = elementName;
	}

	public Element(String elementName, String creationLine) {
		this.elementName = elementName;
		this.creationLine = creationLine;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getCreationLine() {
		return creationLine;
	}

	public void setCreationLine(String creationLine) {
		this.creationLine = creationLine;
	}

	public List<ElementMeta> getMetas() {
		return metas;
	}

	public void setMetas(List<ElementMeta> metas) {
		this.metas = metas;
	}
	
	
	public ElementMeta getElementMetaByKey(String key)
	{
		for(ElementMeta meta : metas)
		{
			if(meta.getKey().equals(key))
				return meta;
			
		}
		//TODO this may need to throw an exception if no metakey is found
		return null;
	}
	
	
	public boolean hasElementMetaKey(String key)
	{
		for(ElementMeta meta : metas)
		{
			if(meta.getKey().equals(key))
				return true;
			
		}
		return false;
	}

	public void addElementMeta(String metaKey, String metaValue, String comment) {
		ElementMeta meta = new ElementMeta(metaKey,metaValue, comment);
		addElementMeta(meta);	
	}
	
	public void addElementMeta(ElementMeta meta) {
		if(!metas.contains(meta))
		{
			metas.add(meta);
		}
					
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementName == null) ? 0 : elementName.hashCode());
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
		Element other = (Element) obj;
		if (elementName == null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		return true;
	}
	

}
