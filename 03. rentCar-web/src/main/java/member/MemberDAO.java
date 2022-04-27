package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class MemberDAO {
	
	private static MemberDAO instance = null; 
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if( instance == null ) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	ArrayList<MemberDTO> members = new ArrayList<MemberDTO>();
	
	PreparedStatement pstmt = null;	
	ResultSet rs = null;

	
	public ArrayList<MemberDTO> getMembers() {
		members = new ArrayList<MemberDTO>();
		
		try {
			String sql = "select * from member";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String pw1 = rs.getString(2);
				String email = rs.getString(3);
				String tel = rs.getString(4);
				String hobby = rs.getString(5);
				String job = rs.getString(6);
				int age = rs.getInt(7);
				String info = rs.getString(8);
				members.add(new MemberDTO(id, pw1, email, tel, hobby, job, age, info ));
			}
			System.out.println("데이터 불러오기 완료!");
			return members;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public MemberDTO getOneMember(String id) {
		getMembers();
		for(MemberDTO m : members) {
			if(m.getId().equals(id)) {
				// ArrayList안에 있는 거 말고 별도의 member 1개를 반환
				// ㄴ update시 new 해서 넣어야 됨
				return m;
			}
		}
		return null;
	}
	
	public boolean addMember(MemberDTO dto) {
		try {
			int age = dto.getAge();
			System.out.println("들어온 age: " + age);
			
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			Connection conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw1());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getHobby());
			pstmt.setString(6, dto.getJob());
			pstmt.setInt(7, age);
			pstmt.setString(8, dto.getInfo());

			pstmt.executeUpdate();
			System.out.println("추가 완료");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateMember(MemberDTO dto) {
		try {
			String id = dto.getId();
			String sql = "update member set id=?, pw1=?, email=?, tel=?, hobby=?, job=?, age=?, info=? where id=?";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, dto.getPw1());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getHobby());
			pstmt.setString(6, dto.getJob());
			pstmt.setInt(7, dto.getAge());
			pstmt.setString(8, dto.getInfo());
			pstmt.setString(9, id);

			pstmt.executeUpdate();
			System.out.println("추가 완료");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteMember(MemberDTO dto) {
		try {
			String sql ="delete from member where id=?";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.executeUpdate();
			
			System.out.println("삭제 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
