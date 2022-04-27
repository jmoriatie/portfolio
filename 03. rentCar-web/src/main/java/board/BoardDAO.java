package board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import member.MemberDAO;
import member.MemberDTO;
import util.DBManager;

public class BoardDAO {

	private static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance == null) {
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
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
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
				String comments = rs.getString(8);
				
				BoardDTO board = new BoardDTO(no, id, pw, title, contents, likes, regDate, comments); 
				brds.add(board);
			}
			
			System.out.println("데이터 불러오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터 불러오기 실패");
		}
		return brds;
	}
		
	public BoardDTO getOnePost(int no) {
		brds = getBoardList();
		for(BoardDTO post : brds) {
			if(post.getNo() == no) {
				return post; 
			}
		}
		return null;
	}
	
	
	public boolean boardWrite(String id, String title, String content) {
		try {			
			MemberDTO member = checkMember(id);
			BoardDTO newPost = new BoardDTO(member.getId(), member.getPw1(), title, content, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			
			String sql = "insert into board(id, pw, title, content, regdate, comments) values(?, ?, ?, ?, ?, ?)";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, newPost.getId());
			pstmt.setString(2, newPost.getPw());
			pstmt.setString(3, newPost.getTitle());
			pstmt.setString(4, newPost.getContents());
			pstmt.setTimestamp(5, newPost.getRegDate());
			pstmt.setString(6, "");
			
			pstmt.executeUpdate();
			
			System.out.println("게시물 작성 완료");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 작성 실패");
			return false;
		}
	}
	
	private MemberDTO checkMember(String id) {
		MemberDTO member = MemberDAO.getInstance().getOneMember(id); 
		return member;
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
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setInt(3, dto.getNo());
			
			pstmt.executeUpdate();
			
			
			System.out.println("수정 성공!");
			return true;
		} catch (Exception e) {
			System.out.println("수정 실패!");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addComment(int no, String id, String comment) {
		try {
			BoardDTO post = getOnePost(no);
			
			post.addComment(id, comment);

			String sql ="update board set comments=? where no =?";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, post.getComments());
			pstmt.setInt(2, post.getNo());
			
			pstmt.executeUpdate();
			
			System.out.println("댓글달기 성공!");
			return true;
		} catch (Exception e) {
			System.out.println("댓글달기 실패!");
			e.printStackTrace();
		}
		return false;
	}
	
	// 코멘트 출력
	public ArrayList<String> printComments(int no) {
		String tempCmts = getOnePost(no).getComments();
		
		if(tempCmts == null || tempCmts.equals("")) {
			return null;
		}
		else {
			String[] tempArr1 = tempCmts.split("/");
			ArrayList<String> cmtArr = new ArrayList<String>();
			for (String cmt : tempArr1) {
				String[] tempArr2 = cmt.split("=");
				System.out.println(tempArr2[0] + ":" + tempArr2[1]);
				cmtArr.add(tempArr2[0]); // id
				cmtArr.add(tempArr2[1]); // 댓글
			}
			return cmtArr;
		}
	}
	
	public boolean deleteComment(int no, String comment) {
		BoardDTO post = getOnePost(no);
		
		// 기존 코멘트 옮기기
		String[] tempArr1 = post.getComments().split("/");
		
		// 삭제
		ArrayList<String> cmtArr = new ArrayList<String>();
		for (String cmt : tempArr1) {
			if(!cmt.equals(comment)) {
				cmtArr.add(cmt);
			}
		}
		try {
			// 다시 새로 넣기
			post.setComments(""); // 초기화

			for(String str : cmtArr) {
				String[] splitArr = str.split("=");
				post.addComment(splitArr[0], splitArr[1]);
			}

			String sql ="update board set comments=? where no =?";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, post.getComments());
			pstmt.setInt(2, post.getNo());
			
			pstmt.executeUpdate();
			
			System.out.println("댓글삭제 성공!");
			return true;
		} catch (Exception e) {
			System.out.println("댓글삭제 실패!");
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원 탈퇴시 관련 게시글 전체 삭제
	public boolean deleteAll(String id) {
		try {
			String sql = "delete from board where id=?";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
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
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			System.out.println("게시물 삭제 성공");

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

			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setInt(1, likes);
			pstmt.setInt(2, dto.getNo());
			
			pstmt.executeUpdate();

			System.out.println("좋아요 +1");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 유지");
		}
		return false;
	}
}
