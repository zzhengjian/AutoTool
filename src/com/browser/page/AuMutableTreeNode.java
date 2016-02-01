package com.browser.page;

import javax.swing.tree.DefaultMutableTreeNode;

public class AuMutableTreeNode extends DefaultMutableTreeNode {

	@Override
	public String toString() {
		
		if(super.userObject instanceof PageBean)
		{
			return ((PageBean)super.userObject).getPageName();
		}
		else if(super.userObject instanceof ElementBean)
		{
			return ((ElementBean)super.userObject).getElementName();			
		}
		
		return super.userObject.toString();
	}
	
	

}
