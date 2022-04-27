package manager;

public class ManagerInfo {
		
	private static ManagerInfo instance = null;
	
	private String id;
	private String pw;
	
	public ManagerInfo() {}
	
	public static ManagerInfo getInstance() {
		if(instance == null) {
			instance = new ManagerInfo();
		}
		return instance;
	}
	
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
}
