package com.keane.infra.domain;

import java.sql.Date;



public class RequestDetails {

	private int reqId;
	private int userId;
	private int cublicleNo;
	private String department;
	private String location;
	private Date requiredByDate;
	private int reqTypeId;
	private String justification;
	private String rejectionReason;
	private String cancellationReason;
	private int statusId;
	private Date assignedDate;
	private Date requestedDate;
	private Date committedDate;
	private Date completedDate;
	
	public void cancelRequest(int statusid, String cancellationReason){
		this.statusId = statusid;
		this.cancellationReason = cancellationReason;
	}
	
	public void recectReequest(int statusid, String rejectionReason){
		this.statusId = statusid;
		this.rejectionReason = rejectionReason;
	}
	
	public void assignRequest(int statusid, Date committedDate){
		this.statusId = statusid;
		this.committedDate = committedDate;
	}

	public RequestDetails(int reqId, int statusId, Date assignedDate,
			Date requestedDate, Date committedDate, Date completedDate) {
		super();
		this.reqId = reqId;
		this.statusId = statusId;
		this.assignedDate = assignedDate;
		this.requestedDate = requestedDate;
		this.committedDate = committedDate;
		this.completedDate = completedDate;
		this.userId = -1;
		this.cublicleNo = -1;
		this.department = "";
		this.location = "";
		this.requiredByDate = null;
		this.reqTypeId = -1;
		this.justification = "";
		this.rejectionReason = "";
		this.cancellationReason = "";
	}

	public RequestDetails(int reqId, int cublicleNo, String department,
			String location, Date requiredByDate, int reqTypeId,
			String justification, String rejectionReason,
			String cancellationReason, int statusId, Date committedDate) {
		super();
		this.reqId = reqId;
		this.cublicleNo = cublicleNo;
		this.department = department;
		this.location = location;
		this.requiredByDate = requiredByDate;
		this.reqTypeId = reqTypeId;
		this.justification = justification;
		this.rejectionReason = rejectionReason;
		this.cancellationReason = cancellationReason;
		this.statusId = statusId;
		this.committedDate = committedDate;
	}

	public RequestDetails(int userId, int cublicleNo, String department,
			String location, Date requiredByDate, int reqTypeId,
			String justification, int statusId) {
		super();
		this.userId = userId;
		this.cublicleNo = cublicleNo;
		this.department = department;
		this.location = location;
		this.requiredByDate = requiredByDate;
		this.reqTypeId = reqTypeId;
		this.justification = justification;
		this.statusId = statusId;
		
		this.reqId = -1;
		this.rejectionReason = "";
		this.cancellationReason = "";
		this.assignedDate = null;
		assignedDate = null;
		requestedDate = null;
		committedDate= null;
		completedDate= null;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCublicleNo() {
		return cublicleNo;
	}

	public void setCublicleNo(int cublicleNo) {
		this.cublicleNo = cublicleNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getRequiredByDate() {
		return requiredByDate;
	}

	public void setRequiredByDate(Date requiredByDate) {
		this.requiredByDate = requiredByDate;
	}

	public int getReqTypeId() {
		return reqTypeId;
	}

	public void setReqTypeId(int reqTypeId) {
		this.reqTypeId = reqTypeId;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getCommittedDate() {
		return committedDate;
	}

	public void setCommittedDate(Date committedDate) {
		this.committedDate = committedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	@Override
	public String toString() {
		return "RequestDetails [reqId=" + reqId + ", userId=" + userId
				+ ", justification=" + justification + ", requestedDate="
				+ requestedDate + "]";
	}
	
	
	
}
