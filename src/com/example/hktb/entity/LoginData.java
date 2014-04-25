package com.example.hktb.entity;

public class LoginData {
	private Doctor doctor;
	private Nurse nurse;
	private String id;
	// name : t ,
	// password_digest :
	// $2a$10$RjQerapzz/Ou9k9e9AG3X.5flBPYK02kWZZAtg/ALlneCOifz1xC6 ,
	private String patient_id;
	private String doctor_id;
	private String nurse_id;
	// is_enabled : true,
	// created_at : 2014-04-24T09:44:44.708+08:00 ,
	// updated_at : 2014-04-25T13:31:59.802+08:00 ,
	private String remember_token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getNurse_id() {
		return nurse_id;
	}

	public void setNurse_id(String nurse_id) {
		this.nurse_id = nurse_id;
	}

}
