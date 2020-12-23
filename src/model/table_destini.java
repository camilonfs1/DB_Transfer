package model;

import java.util.ArrayList;

public class table_destini {
	String name;
	ArrayList<column> columns;
	
	public table_destini() {
		
	}
	public table_destini(String name,ArrayList<column> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<column> getColumns() {
		return columns;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setColumns(ArrayList<column> columns) {
		this.columns = columns;
	}

}
