package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.dto.CommentDTO;
import util.DBManager;

public class CommentDAO {

	private static CommentDAO instance = null;

	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}

	private ArrayList<CommentDTO> cmts = new ArrayList<CommentDTO>();

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<CommentDTO> getComments() {
		try {
			String sql = "select * from comments";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int postNo = rs.getInt(1);
				int cmtNo = rs.getInt(2);
				String cmtUser = rs.getString(3);  
				String cmtContent = rs.getString(4);
				Timestamp cmtRegDate = rs.getTimestamp(5);
				boolean depth = rs.getBoolean(6);
				
				cmts.add( new CommentDTO(postNo, cmtNo, cmtUser, cmtContent, cmtRegDate, depth) );
			}
			System.out.println("댓글 불러오기 완료");
		} catch (Exception e) {
			System.out.println("댓글 불러오기 실패");
			e.printStackTrace();
		}
		return cmts;
	}
}
