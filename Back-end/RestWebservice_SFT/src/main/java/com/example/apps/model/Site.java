package com.restbody.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "site")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Site implements Serializable {

	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;

	@Column(name = "site_image")
	private byte[] siteImage;

	@Column(name = "average_note")
	private int averageNote;
	
	@Column(name = "address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String commentName) {
		this.siteName = commentName;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public byte[] getSiteImage() {
		return siteImage;
	}

	public void setSiteImage(byte[] siteImage) {
		this.siteImage = siteImage;
	}

	public int getAverageNote() {
		return averageNote;
	}

	public void setAverageNote(int averageNote) {
		this.averageNote = averageNote;
	}

	
}
