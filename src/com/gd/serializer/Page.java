package com.gd.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page {
	public final static String SharedElements = "SharedElements";
	
	private String PageName;
	private String Url;
	
	private String PagePath;	
	private ArrayList<Element> elements = new ArrayList<Element>();
	private ArrayList<String> pageFamilies = new ArrayList<String>();
	private List<Page> families;
	
	private Element currentElement;
	


	public List<Page> getFamilies() {
		return families;
	}

	public String getPageName() {
		return PageName;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}

	public ArrayList<String> getPageFamilies() {
		return pageFamilies;
	}

	public void setPageFamilies(ArrayList<String> pageFamilies) {
		this.pageFamilies = pageFamilies;
	}

	public void setPageName(String pageName) {
		PageName = pageName;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getPagePath() {
		return PagePath;
	}

	public void setPagePath(String pagePath) {
		PagePath = pagePath;
	}
	
	 public Page()
	 {
		 
	 }
	
	 public Page(String pageName)
	 {
	     this.PageName = pageName;
	 }
		

	 
     public void ProcessPage()
     {
    	 
    	 BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(PagePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

         String line;
         int lineCount = 0;
         try {
			while ((line = br.readLine()) != null)
			 {
			     String action = "None";
			     lineCount++;

			     if (line.trim().startsWith("#"))
			     {
			         action = "None";
			     }
			     // Handle processing of GDPage.new declaration
			     else if (line.contains("GdPage.new"))
			     {
			         action = "GdPage";

			     }
			     // Handle page family assignment
			     else if (line.contains(":pageFamily") || line.contains(".addPageFamily("))
			     {
			         action = "PageFamily";
			     }
			     // Handle element declaration
			     else if (line.contains(".addElement("))
			     {
			         action = "AddElement";
			     }
			     // Handle retrieving Page name from addPage action
			     else if (line.contains("Pages.addPage"))
			     {
			         action = "AddPage";
			     }
			     // Handle retrieving processing addition of a shared element
			     else if (line.contains("Pages.addSharedElement("))
			     {
			         action = "AddSharedElement";
			     }
			     // Handle adding a page family element
			     else if (line.contains("Pages.addFamilyElement("))
			     {
			         action = "AddFamilyElement";
			     }
			     // Handle element metadata
			     else if (line.contains("=>"))
			     {
			         action = "AddElementMeta";
			     }
			     
			     processLine(action, line, br);
			 }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     }
     
     
     private void processLine(String actionType, String line, BufferedReader br)
     {
    	 switch (actionType)
         {
             case "Comment":
                 break;
             case "GdPage":
                 String[] pageSplit = line.split("\"");
                 // Get rid of $URL hack for GoBank
//                 if (pageSplit.length > 2 && pageSplit[1] != "$URL+")
//                 {
//                     rewritten = MalformedLine(1, lineCount, line, out newLine);
//                 }
                 if (pageSplit[1] == "$URL+")
                 {
                     this.Url = pageSplit[1] + pageSplit[2];
                 }
                 else
                 {
                	 this.Url = pageSplit[1];
                 }
                 
                 if(line.contains(":pageFamily"))
                 {
                	 String[] pageFamily = line.split("=>")[1].split("\"");
                	 this.pageFamilies.add(pageFamily[1].trim());
                 }
                 
                 break;
             case "PageFamily":
                 String[] familySplit = line.split("\"");
                 pageFamilies.add(familySplit[1].trim());
                 break;
             case "AddElement":
                 String[] elementSplit = line.split("\"");
                 currentElement = new Element(elementSplit[1].trim(), line);
                 elements.add(currentElement);
//                 if (elementSplit.length > 3)
//                 {
//                     rewritten = MalformedLine(2, lineCount, line, out newLine);                        
//                 }
                 // Deal with element name syntax warnings - WHP3
                 break;
             case "AddPage":
                 String[] pageNameSplit = line.split("\"");
                 PageName = pageNameSplit[1];
                 break;
             case "AddSharedElement":
                 String[] sharedElementSplit = line.split("");
                 currentElement = new Element(sharedElementSplit[1].trim(), line);
                 Page SharedPage = new Page(SharedElements);
                 if(families==null)
                 {
                	 families = new ArrayList<Page>();
                 }
                 if(!families.contains(SharedPage))
                 {
                	 families.add(SharedPage);
                 }
                 for(Page page : families)
                 {
                	 if(page.equals(SharedPage))
                	 {
                		 page.elements.add(currentElement);
                	 }
                 }               
                 
                 break;
             case "AddFamilyElement":
                 String[] familyElementSplit = line.split("\"");
                 Page familyPage = new Page(familyElementSplit[1].trim());
                 currentElement = new Element(familyElementSplit[3].trim(), line);
                 if(families==null)
                 {
                	 families = new ArrayList<Page>();
                 }
                 if(!families.contains(familyPage))
                 {
                	 families.add(familyPage);
                 }
                 for(Page page : families)
                 {
                	 if(page.equals(familyPage))
                	 {
                		 page.elements.add(currentElement);
                	 }
                 }    
                 break;
             case "AddElementMeta":
                 //What causes meta to break need to put out these conditions
                 String[] elementMetaSplit = line.split("=>");

                 int valueIndex = 1;

                 boolean hasExtraQuote = (line.contains("\\\"") || (elementMetaSplit.length > 2 && elementMetaSplit[1].trim().startsWith("'")));
                 boolean contained = currentElement.hasElementMetaKey(elementMetaSplit[0].trim());
                 boolean multiElementLine = line.indexOf("=>") != line.lastIndexOf("=>");
                 boolean containsComment = false;

                 String comment = "";
                 String metaKey = elementMetaSplit[0].replace(":", "").trim();
                 String metaValue = elementMetaSplit.length < 2 ? "" : elementMetaSplit[1].trim();
                 if(!"".equals(metaValue))
                 {
                	 metaValue = metaValue.replace("\\s+", " ");
                	 metaValue = metaValue.replace("\" ,", "\",");
                	 //deal with multiple lines of metavalue like text...
                	 if(!metaValue.endsWith("\"") && !metaValue.endsWith("\","))
                	 {
                		 String nextline = "";
                    	 boolean toReturn = true;
                    	 while(toReturn)
                    	 {
                    		 try {
    							nextline = br.readLine().trim().replace("\\s+", " ").replace("\" ,", "\",");
    							metaValue = metaValue + " " + nextline;
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
                    		if(nextline.endsWith("\"") ||nextline.endsWith("\","))
                    		{
                    			toReturn = false;
                    		}                    		 
                    	 }
                	 }
                	 
                	 
                	 metaValue = metaValue.substring(metaValue.indexOf('"') + 1, metaValue.lastIndexOf('"'));
                 }
                 

                 if (elementMetaSplit.length == 1)
                 {
                    //log error message here
                 }
                 else if (!contained && hasExtraQuote)
                 {
                     for (int i = 2; i < elementMetaSplit.length; i++)
                     {
                         if (elementMetaSplit[i].trim() != "," && elementMetaSplit[i].trim() != ")")
                         {
                             metaValue += "\"" + elementMetaSplit[i] ;
                         }
                     }
                     if (line.endsWith("\"\""))
                     {
                         metaValue += "\"";
                     }
                     if (elementMetaSplit[1].startsWith("'"))
                     {
                         //log error message here
                     }
                 }
                 else if (elementMetaSplit.length > 2 && "".equals(metaValue))
                 {
                     for (int i = 2; i < elementMetaSplit.length; i++)
                     {
                         if ("".equals(metaValue) && !"".equals(elementMetaSplit[i]) && elementMetaSplit[i].trim() != "," && elementMetaSplit[i].trim() != ")")
                         {
                             valueIndex = i;
                             metaValue = elementMetaSplit[i];
                         }
                     }
                     //log error message here
                     
                     if (!contained && elementMetaSplit.length > 2)
                     {
                         for (int i = valueIndex + 1; i < elementMetaSplit.length; i++)
                         {
                             if (elementMetaSplit[i].contains("#") || containsComment)
                             {
                                 comment += elementMetaSplit[i];
                                 containsComment = true;
                             }
                         }
                     }
                 }

                 if (contained)
                 {
                	//log error message here
                 }
                 else if (multiElementLine)
                 {
                	//log error message here
                 }
                 else if (elementMetaSplit.length > 2 && !hasExtraQuote && elementMetaSplit[2] != ",")
                 {
                	//log error message here
                 }
                 else
                 {
                	 currentElement.addElementMeta(metaKey, metaValue, comment);
                 }
                 break;
         }

    	 
     }
     
     
     public void addElement(Element element)
     {
    	 //the element in pageFamilies should be included
    	 if(!elements.contains(element))
    	 {
    		 this.elements.add(element);
    	 }
    		 	 
     }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PageName == null) ? 0 : PageName.hashCode());
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
		Page other = (Page) obj;
		if (PageName == null) {
			if (other.PageName != null)
				return false;
		} else if (!PageName.equals(other.PageName))
			return false;
		return true;
	}
     
     
}
