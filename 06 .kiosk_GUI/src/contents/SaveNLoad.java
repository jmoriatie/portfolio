package contents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import main.Controller;
import manager.ManagerInfo;

public class SaveNLoad {

	private static SaveNLoad instance = null;
	
	String fileName = "kioskData";
	File kioskData = new File(fileName);
	
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
//	int countAll = 0;
	// 계산(컨트롤러) -> sumData -> 전체 저장
	public Vector<Vector<String>> sumData;
	
	private SaveNLoad() {
		sumData = new Vector<Vector<String>>();
		loadAll();
		System.out.println("매니저id/pw: " + ManagerInfo.getInstance().getId()+":"+ManagerInfo.getInstance().getPw());
	}
	
	public static SaveNLoad getInstance() {
		if(instance == null) {
			instance = new SaveNLoad();
		}
		return instance;
	}
	
	public void save() {
		for(int i=0; i<Controller.drink.size(); i++) {
			sumData.add(Controller.drink.get(i));
		}
	}
	
	public void saveAll() {
		String data = "";
		int cnt = sumData.size();
		try {
			fw = new FileWriter(kioskData);
			data += ManagerInfo.getInstance().getId() + "/";
			data += ManagerInfo.getInstance().getPw() + "\n";
			for(int i=0; i<cnt; i++) {
				for(int j=0; j<3; j++) {
					data += sumData.get(i).get(j);
					if(j != 2) data +="/";
				}
				if(i != cnt-1) data +="\n";
			}
			fw.write(data);			
			fw.close();
			System.out.println("저장 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAll() {
		try {
			if(kioskData.exists()) {
				fr = new FileReader(kioskData);
				br = new BufferedReader(fr);
				
				int cnt = 0;
				while(br.readLine() != null) {
					cnt++;
				}
				
				fr = new FileReader(kioskData);
				br = new BufferedReader(fr);
				
				for(int i=0; i<cnt; i++) {
					String[] tempArr = br.readLine().split("/");
					if(i == 0) {
						ManagerInfo.getInstance().setId(tempArr[0]);
						ManagerInfo.getInstance().setPw(tempArr[1]);
					}else {
						Vector<String> tempVec = new Vector<String>();
						tempVec.add(tempArr[0]); // 제품명
						tempVec.add(tempArr[1]); // 수량
						tempVec.add(tempArr[2]); // 가격
						
						sumData.add(tempVec);
					}
				}
				
				br.close();
				fr.close();
				System.out.println("불러오기 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
