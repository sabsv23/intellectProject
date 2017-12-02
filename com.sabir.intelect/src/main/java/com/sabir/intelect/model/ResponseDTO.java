package com.sabir.intelect.model;

import java.util.ArrayList;

public class ResponseDTO {
	
	private String resMsg;
	private String userId;
	private boolean isActive;
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setValErrors(ArrayList<Errors> valErrors) {
		this.valErrors = valErrors;
	}
	private ArrayList<Errors> valErrors;
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setvalErrors(ArrayList<Errors> valErrors){
		this.valErrors=valErrors;
	}
	public ArrayList<Errors> getValErrors(){
		return valErrors;
	}
	
	

}
