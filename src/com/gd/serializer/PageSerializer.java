package com.gd.serializer;

import java.util.ArrayList;
import java.util.List;

public class PageSerializer {
	
    public String pageName;
    public List<String> pageFamilies;
    public String skin;
    public String URL;
    public String screenShot;
    public List<ElementSerializer> elements;

    public PageSerializer()
    {
        pageFamilies = new ArrayList<String>();
        elements = new ArrayList<ElementSerializer>();
    }

}
