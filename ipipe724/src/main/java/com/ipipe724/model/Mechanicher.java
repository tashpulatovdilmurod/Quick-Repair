package com.ipipe724.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "mechanicher")
public class Mechanicher {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="fname")
	private String fname;
	@Column(name="lname")
	private String lname;
	
	@Column(name="dc")
	private String dc;
	
	@Column(name="latituge")
	private String latituge;
	
	@Column(name="longitude")
	private String longitude;
	
	
	
	public Mechanicher() {
	}

	public Mechanicher(Long id, String fname, String lname, String dc, String latituge, String longitude) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.dc = dc;
		this.latituge = latituge;
		this.longitude = longitude;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLatituge() {
		return latituge;
	}
	public void setLatituge(String latituge) {
		this.latituge = latituge;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}
	
}
