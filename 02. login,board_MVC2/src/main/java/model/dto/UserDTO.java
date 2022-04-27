package model.dto;

import java.sql.Timestamp;

public class UserDTO {
	
	/* 
	데이터베이스에 가져온 id, pw 등록
	데이터베이스와 소통하기 위한 오브젝트
	ㄴ DTO(Data Transfer Object) = Bean, VO(read only), Entity Class
	ㄴ 자바 객체임 => src 폴더에 만듦
	*/
	
	private String id, pw, img;
	private Timestamp regDate;
	
	public UserDTO(String id, String pw, Timestamp regDate, String img) {
		this.id = id;
		this.pw = pw;
		this.regDate = regDate;
		this.img = img;
	}

	public UserDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public Timestamp getRegDate() {
		return regDate;
	}
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	@Override
	public String toString() {
		return String.format("%s/%s", id, pw);
	}
	
	
}
