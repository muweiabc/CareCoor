package cis573.carecoor.bean;

public class Game {
	
	private String name;
	private String detail;
	public Game(String n,String d){
		name=n;
		detail=d;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
