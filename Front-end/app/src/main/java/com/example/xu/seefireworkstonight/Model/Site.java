package com.example.xu.seefireworkstonight.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site implements Serializable{

	private long id;

	private String siteName;

	private double latitude;

	private double longitude;

	private byte[] siteImage;

	private int averageNote;

	private String address;

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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return " id "+id+ " siteName "+siteName+
				" latitude "+latitude+" longitude "+longitude+" siteImage "+
				siteImage+ " averageNote "+averageNote;
	}
}
