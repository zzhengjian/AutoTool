package com.gd.steps.serializer;

import java.util.ArrayList;
import java.util.List;

public class Statement {
	
	private String category;
	private String content;
	private String statement;
	private String description;
	private List<Argument> args;	
	
	public Statement(String statement, String category) {
		this.category = category;
		this.statement = statement;
		this.args = new ArrayList<Argument>();
	}
			

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<Argument> getArgs() {
		return args;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setArgs(List<Argument> args) {
		this.args = args;
	}


	public boolean add(Argument e) {
		return args.add(e);
	}


	public void clear() {
		args.clear();
	}


	public boolean isEmpty() {
		return args.isEmpty();
	}
	
	
}
