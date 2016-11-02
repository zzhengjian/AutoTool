package com.gd.steps.serializer;

import java.util.ArrayList;
import java.util.List;

public class StatementSerializer {
	
	public String statement;
	public String project;
	public String category;
	public String content = "N/A";
	public String path = "N/A";
	public String override = "true";
	public String deleted = "false";
	public String deprecated = "false";
    public String description = "N/A";
    public List<ArgumentSerializer> args;
    
	public StatementSerializer() {
		args = new ArrayList<ArgumentSerializer>();
	}
    
    

}
