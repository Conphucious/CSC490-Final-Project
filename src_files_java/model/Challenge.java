package model;

import java.sql.Date;

public class Challenge {
	private int id, pts;
	private String name, description;
	private Date initialDate;
	private boolean is_pending;
	
	public Challenge(int id, int pts, String name, String description, Date initialDate, boolean is_pending) {
		this.id = id;
		this.pts = pts;
		this.name = name;
		this.description = description;
		this.initialDate = initialDate;
		this.is_pending = is_pending;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public boolean isIs_pending() {
		return is_pending;
	}

	public void setIs_pending(boolean is_pending) {
		this.is_pending = is_pending;
	}

	@Override
	public String toString() {
		return "Id: " + getId() + "\t\tPts: " + getPts() + "\n" + "Name: " + getName() + "\n"
				+ "Description: " + getDescription() + "\n" + "Date Posted: " + getInitialDate() + "\n\n";
	}
	
}
