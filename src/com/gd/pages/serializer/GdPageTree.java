package com.gd.pages.serializer;

import com.gd.pagetree.POMutableTreeNode;

public class GdPageTree {
	
	private String PagePath;
	private POMutableTreeNode pageNode;
	
	
	public GdPageTree(String pagePath) {
		PagePath = pagePath;
		
		Page page = new Page();
		page.setPagePath(pagePath);
		page.ProcessPage();
		pageNode = new POMutableTreeNode();
		pageNode.setUserObject(page);		
		
		for(Element e : page.getElements())
		{
			POMutableTreeNode elementNode = new POMutableTreeNode();
			elementNode.setUserObject(e);
			this.pageNode.add(elementNode);
		}		
	}
	
	
	
	public POMutableTreeNode getPageNode() {
		return pageNode;
	}

	public void setPageNode(POMutableTreeNode pageNode) {
		this.pageNode = pageNode;
	}
	

}
