package org.uday;

public class Triangle {
	
	private String type;
	private int Height;
	
	public Triangle(String type){
		this.type = type;
	}
	
	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public Triangle(String type, int Height){
		this.type = type;
		this.Height = Height;
	}
	
	
	public String getType() {
		System.out.println("###2.gettype");
		return type;
	}

	public void setType(String type) {
		System.out.println("###1.settype");
		this.type = type;
	}

	public void draw() {
		System.out.println( getType() + " Traingle drawn");
	}

}
