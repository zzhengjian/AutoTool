package com.gd.serializer;

import java.util.ArrayList;
import java.util.List;

public class PageSerializer {
	
	
    public String pageName;
    public String familyName;
    public List<String> families;
    public String skin;
    public String URL;
    public String screenShot;
    public List<ElementSerializer> elements;
    public String createBy;

    public PageSerializer()
    {
        families = new ArrayList<String>();
        elements = new ArrayList<ElementSerializer>();
    }

}
