package com.gd.pages.serializer;

import java.io.File;

import com.gd.pagetree.POMutableTreeNode;

public class GdPageTree {
	
	private POMutableTreeNode pageNode;
	
	
	public GdPageTree(File pageFile) {
		
		Page page = new Page();
		page.setPageFile(pageFile);
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
