package com.gd.pagetree;

import javax.swing.tree.DefaultMutableTreeNode;

public class AuMutableTreeNode extends DefaultMutableTreeNode {

	/**
	 * Page Tree Node overrides
	 */
	private static final long serialVersionUID = -8530767558450178183L;

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
