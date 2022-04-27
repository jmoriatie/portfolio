package model.dto;

import java.sql.Timestamp;

public class CommentDTO {
	
	
	private int postNo; // 부모 포스트
	private int cmtNo; //  댓글 번호
	private String cmtUser;
	private String cmtContent;
	private Timestamp cmtRegDate;
	private boolean depth; // true(댓글), false(대댓글,댓글을 달 수 x)
	
	public CommentDTO(int postNo, int cmtNo, String cmtUser, String cmtContent, Timestamp cmtRegDate, boolean depth) {
		this.postNo = postNo;
		this.cmtNo = cmtNo;
		this.cmtUser = cmtUser;
		this.cmtContent = cmtContent;
		this.cmtRegDate = cmtRegDate;
		this.depth = depth;
	}

	public int getPostNo() {
		return postNo;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public String getCmtUser() {
		return cmtUser;
	}

	public void setCmtUser(String cmtUser) {
		this.cmtUser = cmtUser;
	}

	public String getCmtContent() {
		return cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	public Timestamp getCmtRegDate() {
		return cmtRegDate;
	}

	public boolean isDepth() {
		return depth;
	}

	public void setDepth(boolean depth) {
		this.depth = depth;
	}

}
