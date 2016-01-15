package com.browser.debug;

public class ElementNode {
	
	private String name;
	private String selector;
	private String text;
	
	private String src;
	private String href;
	private String elementLine;

	public ElementNode()
	{
		
	}
	
	public ElementNode(String name, String selector)
	{
		this.name = name;
		this.selector = selector;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getElementLine() {
		return elementLine;
	}
	public void setElementLine(String elementLine) {
		this.elementLine = elementLine;
	}	
	
	
	

}
