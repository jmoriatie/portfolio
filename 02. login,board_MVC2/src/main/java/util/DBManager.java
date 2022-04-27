package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	
	// 1번만 연결 되는지 확인 ==> 한번만 연결되는거 확인 완료
	private static Connection conn = null;

	// try catch문 없이, throws Exception로 처리 가능
	public static Connection getConnection() throws Exception {
		if (conn == null) {
			// 드라이버 연동 (jdbc mysql connector (.jar)를 -> WEB-INF/lib 폴더로 넣어주기)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// jdbc:mysql://localhost:3306/데이터베이스스키마명?serverTimezone=UTC
			String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC"; // 데이터베이스 주소
			String id = "root";
			String pw = "mymySql00";

			conn = DriverManager.getConnection(url, id, pw);

			if (conn != null) { // null 이 뜨면 성공이 될 예정임
				System.out.println("데이터베이스 연동 성공!");
			} else {
				System.out.println("DB연동 실패!");
			}
		}

		return conn;
	}
}
