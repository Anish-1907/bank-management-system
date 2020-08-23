package com.training.bms.model;

import java.sql.Date;

public class Loans {
	private Integer loanId;
	private Integer userId;
	private String  loanType;
	private Integer loanAmount;
	private Date loanDate;
	private Integer rateOfInterest;
	private Integer loanDuration;
	
	
    public Loans() {
    	
    }
	public Loans(Integer loanId, Integer userId, String loanType, Integer loanAmount, Date loanDate,
			Integer rateOfInterest, Integer loanDuration) {
		this.loanId = loanId;
		this.userId = userId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.loanDate = loanDate;
		this.rateOfInterest = rateOfInterest;
		this.loanDuration = loanDuration;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Integer getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Integer loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Integer rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public Integer getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(Integer loanDuration) {
		this.loanDuration = loanDuration;
	}

	@Override
	public String toString() {
		return "Loans [loanId=" + loanId + ", userId=" + userId + ", loanType=" + loanType + ", loanAmount="
				+ loanAmount + ", loadDate=" + loanDate + ", rateOfInterest=" + rateOfInterest + ", loanDuration="
				+ loanDuration + "]";
	}
	


}
