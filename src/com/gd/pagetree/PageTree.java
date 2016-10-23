package com.gd.pagetree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.tree.DefaultMutableTreeNode;

import com.gd.driver.PageNode;
import com.google.gson.Gson;

public class PageTree {
	
	
	private String PagePath;
	private POMutableTreeNode pageNode;

	public PageTree(String pagePath) {
		this.PagePath = pagePath;
		init();
		
	}
	
	private void init()
	{		
		//PageBean page = readPageFile(new File(PagePath));
		PageBean page = PageNode.getPageBean(PagePath);
		pageNode = new POMutableTreeNode();
		pageNode.setUserObject(page);
		
		
		for(ElementBean e : page.getElements())
		{
			POMutableTreeNode elementNode = new POMutableTreeNode();
			elementNode.setUserObject(e);
			this.pageNode.add(elementNode);
		}		
	}

	public String getPagePath() {
		return PagePath;
	}

	public void setPagePath(String pagePath) {
		PagePath = pagePath;
	}
	
	public POMutableTreeNode getPageNode() {
		return pageNode;
	}

	public void setPageNode(POMutableTreeNode pageNode) {
		this.pageNode = pageNode;
	}
	
	private PageBean readPageFile(File file)
	{
		//read a json format file and then convert to PageBean
		String pagejson = null;
		try {
			pagejson = new Scanner(file).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Gson().fromJson(pagejson, PageBean.class);
	}

}
