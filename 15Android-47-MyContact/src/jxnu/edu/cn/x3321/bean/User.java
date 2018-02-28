package jxnu.edu.cn.x3321.bean;

//java bean
public class User {
	private int userid;
	private String userName;
	private String phone;
	private String imageName;
	
	
	//这些方法不要自己写的
	//直接source-Generate Getters and Setters就可以啦
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
	

}
