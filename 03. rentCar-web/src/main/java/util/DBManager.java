package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import member.MemberDTO;
import rentCar.RentCarDTO;

public class DBManager {
	private DBManager() {}
	private static DBManager instance = null;
	
	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	// 모든 DAO 안에서 DB 연동이 필요할 때
	// Connection conn = DBManager.getConnection()
	
	// 연동된 DB(Schema) 안에 있는 데이터 중,
	// 어떤 테이블을 쓸지 -> use스키마명(sql명령어 추가)
	Connection conn = null;
	
	public Connection getConnection() {
		// DB 연동 코드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			String url = "jdbc:mysql://localhost:3306/rentcardb04?serverTimezone=UTC";
			String id = "root";
			String pw = "mymySql00";
			
			if(conn == null) {
				conn = DriverManager.getConnection(url, id, pw);				
			}
			
			if(conn != null) {
				System.out.println("DB연동!");
			}
			else {
				System.out.println("DB연동 실패!");
			}
		} catch (Exception e) {
			System.out.println("오류오류!!");
			e.printStackTrace();
		}		
		return conn;
	}
	

	
}
