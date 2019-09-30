package model;

import java.sql.Date;

public class User {

	private int id;
	private String username;
	private int cc, pts;
	private Date regDate;
	
	public User(int id, String username, int cc, int pts, Date regDate) {
		super();
		this.id = id;
		this.username = username;
		this.cc = cc;
		this.pts = pts;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", cc=" + cc + ", pts=" + pts + ", regDate="
				+ regDate + "]";
	}
	
}
