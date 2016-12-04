package com.gd.pagetree;

import javax.swing.tree.DefaultMutableTreeNode;

import com.gd.pages.serializer.Element;
import com.gd.pages.serializer.Page;

public class POMutableTreeNode extends DefaultMutableTreeNode {

	/**
	 * Page Tree Node overrides
	 */
	private static final long serialVersionUID = -8530767558450178183L;

	
	@Override
	public String toString() {
		
		if(super.userObject instanceof Page)
		{
			return ((Page)super.userObject).getPageName();
		}
		else if(super.userObject instanceof Element)
		{
			return ((Element)super.userObject).getElementName();			
		}
		
		return super.userObject.toString();
	}
	
	

}
