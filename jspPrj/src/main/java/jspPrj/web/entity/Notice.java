package jspPrj.web.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title; 
	private Date regdate; 	
	private String writerID;
	private String hit; 
	private String content; 
	private String files;
	
	public Notice() {
		
	}
	
	public Notice(int id, String title, Date regdate, String writerID, String hit, String content, String files) {
		
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.writerID = writerID;
		this.hit = hit;
		this.content = content;
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriterID() {
		return writerID;
	}

	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", regdate=" + regdate + ", writerID=" + writerID + ", hit="
				+ hit + ", content=" + content + ", files=" + files + "]";
	}
	
	

}
