package com.gd.serializer;

import java.util.ArrayList;
import java.util.List;

public class FamilySerializer {	

    public String familyName;
    public String skin;
    public String URL;
    public List<ElementSerializer> elements;

    public FamilySerializer()
    {
        elements = new ArrayList<ElementSerializer>();
    }

}
