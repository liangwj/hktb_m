package com.example.hktb.entity;

public class LoginData {
	private Position posr;
	private String id;
	// name : t ,
	// password_digest :
	// $2a$10$RjQerapzz/Ou9k9e9AG3X.5flBPYK02kWZZAtg/ALlneCOifz1xC6 ,
	// patient_id : null,
	// doctor_id : 113932081080012,
	// nurse_id : null,
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

	public Position getPosr() {
		return posr;
	}

	public void setPosr(Position posr) {
		this.posr = posr;
	}

}
