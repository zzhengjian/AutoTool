package com.gd.pages.serializer;

import java.util.ArrayList;
import java.util.List;

public class ElementSerializer {
	
    public String elementName;
    public String page;
    public String family;
    public String frame;
    public List<ElementMetaSerializer> elementMetas;
    public String description;
    
	public ElementSerializer() {
		this.elementMetas = new ArrayList<ElementMetaSerializer>();
	}
    
    

}
