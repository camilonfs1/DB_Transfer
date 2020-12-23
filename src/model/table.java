package model;

public class table {
	String name;
	String primary;
	String[] columns;
	
	public table() {
		
	}
	public table(String name,String primary,String [] columns) {
		this.name = name;
		this.columns = columns;
	}
	public String getPrimary() {
		return primary;
	}
	
	public String getName() {
		return name;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public void setColumns(String [] columns) {
		this.columns = columns;
	}
	

}
