package com.example.hktb.entity;

public class UsReports {

	private String id;
	private String appointment_time;
	private String approval_status;
	private String report_document_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppointment_time() {
		return appointment_time;
	}

	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public String getReport_document_id() {
		return report_document_id;
	}

	public void setReport_document_id(String report_document_id) {
		this.report_document_id = report_document_id;
	}

}
