package reserve;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.text.DateFormatter;

import rentCar.RentCarDAO;
import util.DBManager;

public class ReserveDAO {
	private static ReserveDAO instance = null;
	private ReserveDAO() {};
	public static ReserveDAO getInstance() {
		if(instance == null) {
			instance = new ReserveDAO();
		}
		return instance;
	}
	
	ArrayList<ReserveDTO> reservations = new ArrayList<ReserveDTO>();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// no 증가시켜주는 메서드
	private int getIdx() {
		// 불러오기 필요
		if(reservations != null && reservations.size() != 0) { 
			int num = 0;
			for(ReserveDTO r : reservations) {
				if(r.getReserve_seq() > num) {
					num = r.getReserve_seq();
				}
			}
			return num+1; // 제일 큰 자동차 번호
		}
		else { // 상품 0개일 때 예외처리
			return 1;
		}
	}

	// 종료된 예약 삭제 => 전역리스트 사용 (최초 홈페이지 들어올때 사용됨)
	public void removeEndReservation() {
		for(int i=0;  i<reservations.size(); i++) {
			if(reservations.get(i).getD_day() <= 0) {
				System.out.println("[삭제]["+reservations.get(i).getReserve_seq()+"번][예약차량:"+reservations.get(i).getNo()+"번][예약자:"+reservations.get(i).getId()+"]");
				deleteOneReservation(reservations.get(i).getReserve_seq()); // db에서 삭제
				System.out.println("DB정리");
			}
		}
	}
	
	public ArrayList<ReserveDTO> getReservations(){
		try {
			reservations = new ArrayList<ReserveDTO>();
			
			String sql = "select * from car_reserve";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int reserve_seq = rs.getInt(1);
				int no = rs.getInt(2);
				String id = rs.getString(3);
				int qty = rs.getInt(4);
				int d_day = rs.getInt(5);
				String r_day = rs.getString(6);
				int use_in = rs.getInt(7);
				int use_wifi = rs.getInt(8);
				int use_navi = rs.getInt(9);
				int use_seat = rs.getInt(10);

				reservations.add(new ReserveDTO(reserve_seq, no, id, qty, d_day, r_day, use_in, use_wifi, use_navi, use_seat)); 
			}
			removeEndReservation(); // 종료된 예약 삭제

			System.out.println("예약 불러오기 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약 불러오기 실패!");
		}
		return reservations;
	}
	
	public ReserveDTO getOneReservation(int seq) {
		getReservations();
		for(ReserveDTO r : reservations) {
			if(r.getReserve_seq() == seq) {
				return r;
			}
		}
		return null;
	}
	
	
	// String -> LocalDate
	private LocalDate toLocalDateReserveDate(String dateStr) {
		System.out.println("dateStr: " + dateStr);
		LocalDate reserveDate = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
		return reserveDate;
	}
	
	// LocalDate -> formatter 
	public String printLocalDate(LocalDate localDate) {
		String str = localDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		return str;
	}

	// 예약날과의 차이
	public int getRemainingDay(String reserveDay){
		// 현재, 예약날짜 비교 => 차이 날 반환
		int diffDay = (int)ChronoUnit.DAYS.between(LocalDate.now(), toLocalDateReserveDate(reserveDay));
		return diffDay;
	}
	
	public int checkToInt(String checkbox) {
		if(checkbox != null) return 1; // 사용
		else return 0; // 사용안함
	}
	
	private String convertDate(String date) {
		String content = "";
		String [] sub = date.split("-");
		for(String c : sub) {
			content += c;
		}
		return content;
	}
	
	// 날짜 저장 YYYYMMdd (String) 형태로 저장
	// 부수적인 기기 사용 1 , 사용안함 0
	public boolean addReservation(ReserveDTO dto) {
		try {
			getReservations();
			
			String strDate = convertDate(dto.getR_day()); // 예약날(가공할 수 있게 변환)
			System.out.println(strDate);
			int remainingDay = getRemainingDay(strDate); // 예약날->잔여날(int)로 변환
			if(remainingDay <= 0) {
				System.out.println("동일 또는 이전 날짜 지정됨");
				return false;
			}
			
			String sql = "insert into car_reserve values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			
			pstmt.setInt(1, getIdx()); // 인덱스 하나씩 증가
			pstmt.setInt(2, dto.getNo());
			pstmt.setString(3, dto.getId());
			pstmt.setInt(4, dto.getQty());
			pstmt.setInt(5, remainingDay); // 남은날
			pstmt.setString(6, dto.getR_day() );
			pstmt.setInt(7, dto.getUse_in());
			pstmt.setInt(8, dto.getUse_wifi());
			pstmt.setInt(9, dto.getUse_navi());
			pstmt.setInt(10, dto.getUse_seat());
			
			pstmt.executeUpdate();

			System.out.println("예약 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약 실패!");
		}
		return false;
	}
	
	// 아이디 받아서 다 삭제
	// ㄴ 계정 삭제시 사용
	public boolean deleteAllReservation(String id) {
		try {
			String sql = "delete from car_reserve where id=?";
			
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, id);

			pstmt.executeUpdate();
			System.out.println("예약내용 전체 삭제 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약내용 전체 삭제 실패!");
			return false;
		}
	}
	

	public boolean deleteOneReservation(int reserve_seq) {
		try {
			String sql = "delete from car_reserve where reserve_seq=?";
			
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setInt(1, reserve_seq);

			pstmt.executeUpdate();

			System.out.println("삭제 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패!");
			return false;
		}
	}
}
