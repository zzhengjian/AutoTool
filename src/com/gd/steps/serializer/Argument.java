package com.gd.steps.serializer;

public class Argument implements Comparable<Argument>{
	
	private int argindex;
	private String argtype;
	private String desc;
		

	public Argument(int argindex, String desc) {
		this(argindex, desc, "Unknown");
	}

	public Argument(int argindex, String desc, String argtype) {
		this.argindex = argindex;
		this.desc = desc;
		this.argtype = argtype;
	}
	

	public int getArgindex() {
		return argindex;
	}


	public void setArgindex(int argindex) {
		this.argindex = argindex;
	}

	public String getArgtype() {
		return argtype;
	}
	
	public void setArgtype(String argtype) {
		this.argtype = argtype;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int compareTo(Argument arg) {
		// int compareage=((Student)comparestu).getStudentage();
		int compareindex = arg.getArgindex();
		return this.argindex - compareindex;
	}
	

	@Override
	public String toString() {
		return "Argument [argindex=" + this.argindex + ", argtype=" + this.argtype + ", desc=" + this.desc + "]";
	}

}
