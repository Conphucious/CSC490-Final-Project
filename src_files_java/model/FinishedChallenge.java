package model;

import java.sql.Date;

public class FinishedChallenge {
	
	private int id, pts;
	private String challenge_name, description;
	private Date initial_date, complete_date;
	
	public FinishedChallenge(int id, int pts, String challenge_name, String description, Date initial_date,
			Date complete_date) {
		this.id = id;
		this.pts = pts;
		this.challenge_name = challenge_name;
		this.description = description;
		this.initial_date = initial_date;
		this.complete_date = complete_date;
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

	public String getChallenge_name() {
		return challenge_name;
	}

	public void setChallenge_name(String challenge_name) {
		this.challenge_name = challenge_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInitial_date() {
		return initial_date;
	}

	public void setInitial_date(Date initial_date) {
		this.initial_date = initial_date;
	}

	public Date getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(Date complete_date) {
		this.complete_date = complete_date;
	}

	@Override
	public String toString() {
		return "FinishedChallenge [id=" + id + ", pts=" + pts + ", challenge_name=" + challenge_name + ", description="
				+ description + ", initial_date=" + initial_date + ", complete_date=" + complete_date + "]";
	}
	
}
