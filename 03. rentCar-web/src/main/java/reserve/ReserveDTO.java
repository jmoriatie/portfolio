package reserve;

public class ReserveDTO {
	private int reserve_seq;
	private int no;
	private String id;
	private int qty;
	private int d_day;
	private String r_day;
	private int use_in;
	private int use_wifi;
	private int use_navi;
	private int use_seat;
	public ReserveDTO(int reserve_seq, int no, String id, int qty, int d_day, String r_day, int use_in, int use_wifi,
			int use_navi, int use_seat) {
		this.reserve_seq = reserve_seq; // 1 primary key(DB에서 지정하진 않음)
		this.no = no; // 2 차번호
		this.id = id; // 3 예약자
		this.qty = qty; // 4
		this.d_day = d_day; // 5
		this.r_day = r_day; // 6
		this.use_in = use_in; // 7
		this.use_wifi = use_wifi; // 8
		this.use_navi = use_navi; // 9
		this.use_seat = use_seat; // 10
	}
	
	public ReserveDTO(int no, String id, int qty, String r_day, int use_in, int use_wifi,
			int use_navi, int use_seat) {
		this.no = no; // 2 차번호
		this.id = id; // 3 예약자
		this.qty = qty; // 4
		this.r_day = r_day; // 6
		this.use_in = use_in; // 7
		this.use_wifi = use_wifi; // 8
		this.use_navi = use_navi; // 9
		this.use_seat = use_seat; // 10
	}
	
	public int getReserve_seq() {
		return reserve_seq;
	}
	public void setReserve_seq(int reserve_seq) {
		this.reserve_seq = reserve_seq;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getD_day() {
		return d_day;
	}
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}
	public String getR_day() {
		return r_day;
	}
	public void setR_day(String r_day) {
		this.r_day = r_day;
	}
	public int getUse_in() {
		return use_in;
	}
	public void setUse_in(int use_in) {
		this.use_in = use_in;
	}
	public int getUse_wifi() {
		return use_wifi;
	}
	public void setUse_wifi(int use_wifi) {
		this.use_wifi = use_wifi;
	}
	public int getUse_navi() {
		return use_navi;
	}
	public void setUse_navi(int use_navi) {
		this.use_navi = use_navi;
	}
	public int getUse_seat() {
		return use_seat;
	}
	public void setUse_seat(int use_seat) {
		this.use_seat = use_seat;
	}
	
	
}
