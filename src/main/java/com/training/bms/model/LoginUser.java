package com.training.bms.model;

public class LoginUser {
	
	private Integer userId;
	private String pwd;
	
	
	public LoginUser(Integer userId, String pwd) {
		this.userId = userId;
		this.pwd = pwd;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", pwd=" + pwd + "]";
	}
	
	

}
