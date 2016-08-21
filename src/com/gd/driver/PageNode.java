package com.gd.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

import com.gd.pagetree.ElementBean;
import com.gd.pagetree.PageBean;

public class PageNode {
	
	public File pageFile;	
	public String pageName;
	public String url;
	public HashMap<String,String> elementsMap = new LinkedHashMap<String,String>();
	
	
	public void init(File file)
	{
		this.pageFile = file;
		try {
			getElementsMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPageName()
	{
		return this.pageName;
	}
	
	public HashMap<String, String> getElementsMap() throws IOException{
		
		FileReader fr = null;
		try {
			fr = new FileReader(pageFile);
			//fw = new FileWriter("C:/out/tempPage.txt");  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        BufferedReader bufr = new BufferedReader(fr);   
        
        String sElementName = null;
        String sElementTag = null;
       
        String sline = "";
        String[] line = null;

        
        while(((sline!=null&&sline.contains("addElement")&&!sline.trim().startsWith("#"))||(sline=bufr.readLine()) != null))
        {
			if(sline.contains("https:")&&!sline.trim().startsWith("#"))
			{
				line = sline.split("=");
				pageName = line[0].trim();
				url = line[1].split("\"")[1].trim();
			}
        	
        	if(sline.contains("addElement")&&!sline.trim().startsWith("#"))
        	{
        		
	        	StringBuilder strBuilder = new StringBuilder(sline.replace("\n", "").trim());
        	
	        	
	            while((sline=bufr.readLine()) != null&&!sline.trim().startsWith("#")&&!sline.contains("addElement"))
	            {
	            	strBuilder.append(sline.replace("\n", "").trim());
                }
	            
	            
	            String elementline = strBuilder.toString();
	            if(elementline.contains(":desktop"))
				{
					//get Element Name
					line = elementline.split("\"");
					sElementName = line[1];
					
					//to get Element Locator
					line = elementline.split("GdElement.new");					
					
					sElementTag = "";					
					sElementTag = line[1].split(":desktop")[1].split("\"")[1].trim();
					
//					if(elementline.contains("desktopcss"))
//						sElementTag = "css=" + sElementTag;
//					else
//						sElementTag = "xpath=" + sElementTag;
					
					
					elementsMap.put(sElementName, sElementTag);
				}
        	}
        	
        }        

        bufr.close();
        fr.close();
		return elementsMap;        
        
	}


	public static PageBean getPageBean(String pagePath){
		
		PageBean page = null;
		FileReader fr = null;
		try {
			File file = new File(pagePath);
			fr = new FileReader(file);
			//fw = new FileWriter("C:/out/tempPage.txt");  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        
        BufferedReader bufr = new BufferedReader(fr);   
        
        
        String sElementName = null;
        String sElementTag = null;
       
        String sline = null;
		try {
			
		sline = bufr.readLine();
        String[] line = null;

        
        while(sline!=null)
        {
        	if(sline.trim().startsWith("#")||sline.trim().equals(""))
        	{
        		sline = bufr.readLine();
        		continue;
        	}
        	
        	else if(sline.contains("https:")&&!sline.trim().startsWith("#"))
			{
				line = sline.split("=");
				page = new PageBean(line[1].split("\"")[1].trim(),line[0].trim());
				sline = bufr.readLine();
				continue;
			}
        	
        	else if(sline.contains("addElement")&&!sline.trim().startsWith("#"))
        	{
        		
        		ElementBean element = null;
        		//get Element Name
				line = sline.split("\"");
				sElementName = line[1];
				element = new ElementBean(sElementName);
	            
				sline = bufr.readLine();
				while(sline != null &&  !sline.contains("addElement"))
				{
					if(sline.contains("=>") && sline.contains(":desktop"))
					{									
											
						sElementTag = sline.split("=>")[1].trim();						
						element.setSelector(sElementTag.substring(1, sElementTag.lastIndexOf('"')));

					}
		            else if(sline.contains("=>") && !sline.contains(":rwd"))
		            {
		            	String[] meta = sline.split("=>");
		            	String metavalue = meta[1].trim();
		            	element.addDefaultValue(meta[0].trim().replace(":", ""), metavalue.substring(1, metavalue.lastIndexOf('"')));
		            }
					
					sline = bufr.readLine();
				}            
	            
	            page.addElement(element);
        	}   
        	else
        	{
        		sline = bufr.readLine();
        		continue;
        	}
        		
        }        

		bufr.close();
        fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;        
        
	}
	
	@Test
	public void testPage()
	{
		String workspace = "C:/azheng-QA-Workspace/QA";
		String path = "/Cucumber/Projects/GreenDot/features/pages/Web/GreenDot/CashLoad.rb";
		getPageBean(workspace+path);
	}
	
	class MyTreeModelListener implements TreeModelListener {
	    public void treeNodesChanged(TreeModelEvent e) {
	        DefaultMutableTreeNode node;
	        node = (DefaultMutableTreeNode)
	                 (e.getTreePath().getLastPathComponent());

	        /*
	         * If the event lists children, then the changed
	         * node is the child of the node we have already
	         * gotten.  Otherwise, the changed node and the
	         * specified node are the same.
	         */
	        try {
	            int index = e.getChildIndices()[0];
	            node = (DefaultMutableTreeNode)
	                   (node.getChildAt(index));
	        } catch (NullPointerException exc) {}

	        System.out.println("The user has finished editing the node.");
	        System.out.println("New value: " + node.getUserObject());
	    }
	    public void treeNodesInserted(TreeModelEvent e) {
	    }
	    public void treeNodesRemoved(TreeModelEvent e) {
	    }
	    public void treeStructureChanged(TreeModelEvent e) {
	    }
	}
}
