package com.keane.infra.domain;

public class Status {

	private int statusId;
	private String statusDesc;
	private Role role;
	
	public Status(int statusId, String statusDesc) {
		super();
		this.statusId = statusId;
		this.statusDesc = statusDesc;
	}
	public Status(int statusId, String statusDesc, Role role) {
		super();
		this.statusId = statusId;
		this.statusDesc = statusDesc;
		this.role = role;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusDesc=" + statusDesc
				+ ", role=" + role + "]";
	}
	
	
}
