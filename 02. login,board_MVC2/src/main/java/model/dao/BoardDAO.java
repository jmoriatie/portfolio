package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import model.dto.BoardDTO;
import model.dto.UserDTO;
import util.DBManager;

public class BoardDAO {

	private static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			System.out.println("게시판 인스턴스 생성");
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private BoardDAO() {
	}
	

	private ArrayList<BoardDTO> brds = new ArrayList<BoardDTO>();
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public ArrayList<BoardDTO> getBoardList(){
		
		try {
			String sql = "select * from board";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			brds = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String id = rs.getString(2);
				String pw = rs.getString(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				int likes = rs.getInt(6);
				Timestamp regDate = rs.getTimestamp(7);
				
				BoardDTO board = new BoardDTO(no, id, pw, title, contents, likes, regDate); 
				brds.add(board);
			}
			rs.close();
			pstmt.close();
			
			System.out.println("게시판 데이터 불러오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시판 데이터 불러오기 실패");
		}
		return brds;
	}
			
	
	public BoardDTO getOnePost(int no) {
		getBoardList();
		for (BoardDTO post : brds) {
			if (post.getNo() == no) {
				return post;
			}
		}
		return null;
	}
	
	public boolean boardWrite(String id, String title, String content) {
		try {
			UserDTO user = UserDAO.getInstance().getOneUser(id); 
			BoardDTO newPost = new BoardDTO(user.getId(), user.getPw(), title, content, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			
			// 자동증가 뺴고 입력?
			String sql = "insert into board(id, pw, title, content, regdate) values(?, ?, ?, ?, ?)";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			pstmt.setString(1, newPost.getId());
			pstmt.setString(2, newPost.getPw());
			pstmt.setString(3, newPost.getTitle());
			pstmt.setString(4, newPost.getContents());
			pstmt.setTimestamp(5, newPost.getRegDate());
			
			pstmt.executeUpdate();
			
			brds.add(newPost);
			
			System.out.println("게시물 작성 완료");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 작성 실패");
			return false;
		}
	}
	
	public boolean updateBoard(BoardDTO dto) {
		try {
			
			brds = getBoardList();
			for(BoardDTO p : brds) {
				if(p.getNo() == dto.getNo()) {
					p.setTitle(dto.getTitle());
					p.setContents(dto.getTitle());
					break;
				}
			}
			
			String sql ="update board set title=?, content=? where no =?";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setInt(3, dto.getNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			System.out.println("수정 성공!");
			return true;
		} catch (Exception e) {
			System.out.println("수정 실패!");
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원 탈퇴시 관련 게시글 전체 삭제
	public boolean deleteAll(String id) {
		try {
			String sql = "delete from board where id=?";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
						
			System.out.println("게시물 삭제 성공");
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
		}
		return false;
	}
	
	public boolean deleteBoard(int no) {
		try {
			String sql = "delete from board where no=?";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			System.out.println("게시물 삭제 성공");
			
			pstmt.close();
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
		}
		return false;
	}
	
	// 내 게시물 아닐때만 노출
	public boolean plusLike(BoardDTO dto) {		
		try {
			int likes = (dto.getLikes() + 1);
			System.out.println("like: " + likes);

			String sql ="update board set likes=? where no=? ";
			pstmt = DBManager.getConnection().prepareStatement(sql);
			
			pstmt.setInt(1, likes);
			pstmt.setInt(2, dto.getNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			
			System.out.println("좋아요 +1");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 유지");
		}
		return false;
	}
}
