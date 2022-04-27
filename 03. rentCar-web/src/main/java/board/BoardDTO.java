package board;

import java.sql.Timestamp;
import java.util.Calendar;

public class BoardDTO {
	private int no, likes;
	private String id, pw, title, contents, comments;
	private Timestamp regDate;
	
	public BoardDTO(String id, String pw, String title, String contents, Timestamp regDate) {
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.contents = contents;
		this.regDate = regDate; 
	}
	
	public BoardDTO(int no, String id, String pw, String title, String contents, int likes, Timestamp regDate, String comments) {
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.likes = likes;
		this.contents = contents;
		this.regDate = regDate;
		this.comments = comments;
	}

	public int getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}	
	
	public void addComment(String id, String comment) {
		this.comments += id+"="+comment+"/";
	}
}
