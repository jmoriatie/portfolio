package rentCar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class RentCarDAO {

	private static RentCarDAO instance = null;
	
	private RentCarDAO() {};
	
	public static RentCarDAO getInstance() {
		if(instance == null) {
			instance = new RentCarDAO();
		}
		return instance;
	}
	
	ArrayList<RentCarDTO> rentCars = null; 
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// no 증가시켜주는 메서드
	private int getIdx() {
		// 불러오기 필요
		if(rentCars != null) { 
			return rentCars.get(rentCars.size()-1).getNo(); // 맨마지막 등록된 자동차의번호
		}
		else { // 상품 0개일 때 예외처리
			return 1;
		}
	}
	
	// getRentCars() 불러와야 해당 java와 동기화 됨
	public ArrayList<RentCarDTO> getRentCars(){
		try {
			String sql = "select * from rentcar";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();

			rentCars = new ArrayList<RentCarDTO>();
			while(rs.next()) {
				int no =  rs.getInt(1);
				String name = rs.getString(2);
				int category =  rs.getInt(3);
				int price =  rs.getInt(4);
				int use_people =  rs.getInt(5);
				String company = rs.getString(6);
				String image = rs.getString(7);
				String info = rs.getString(8);
				
				rentCars.add(new RentCarDTO(no, name, category, price, use_people, company, image, info));
			}
			System.out.println("불러오기 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("불러오기 실패!");
		}
		return rentCars;
	}
	
	public RentCarDTO getOneRentCar(int no) {
		getRentCars();
		for(RentCarDTO car : rentCars) {
			if(car.getNo() == no) {
				return car;
			}
		}
		return null;
	}
	
	public boolean addRentCar(RentCarDTO dto) {
		try {
			getRentCars(); // getIdx동기화
			String sql = "insert into rentcar values(?,?,?,?,?,?,?,?)";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setInt(1, getIdx());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getCategory());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getUse_people());
			pstmt.setString(6, dto.getCompany());
			pstmt.setString(7, dto.getImage()); // 이미지에 대한 부분
			pstmt.setString(8, dto.getInfo());

			pstmt.executeUpdate();
			
			System.out.println("등록 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등록 실패!");
			return false;
		}
	}
	
	public boolean updateRentCar(RentCarDTO dto) {
		try {
			String sql = "update rentcar set "
					+ "name=?,"
					+ "category=?,"
					+ "price=?,"
					+ "use_people=?," // 인승정보
					+ "company=?,"
					+ "image=?,"
					+ "info=?"
					+ " where no=?,";
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getCategory());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getUse_people());
			pstmt.setString(5, dto.getCompany());
			pstmt.setString(6, dto.getImage()); // 이미지에 대한 부분
			pstmt.setString(7, dto.getInfo());
			pstmt.setInt(8, dto.getNo());

			pstmt.executeUpdate();
			System.out.println("업데이트 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("업데이트 실패!");
			return false;
		}
	}
	
	public boolean deleteRentCar(int no) {
		try {
			String sql = "delete from rentcar where no=?";
	
			pstmt = DBManager.getInstance().getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();
			System.out.println("삭제 성공!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패!");
			return false;
		}
	}
	
	
	public String selectCategory(int category) {
		switch (category) {
		case 1:
			return "중형차";
		case 2:
			return "소형차";
		case 3:
			return "SUV";
		default:
			return null;
		}
	}
}