package contents;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import data.Coffee;
import main.Controller;
import util.Util;

public class MenuCoffee extends Util {
	
	// 커피 종류 , 가격 => 설정, add  
	// ㄴ 가격 parseInt로 사용 
	// ㄴ 수량, 가격 체크 
	// ㄴ (데이터이동: 여기 -> 컨트롤러 -> 테이블) 
	// 커피벡터 사이즈만큼, 버튼그리기 
	// 커피버튼 벡터에서 사이즈 가져와서 그림
	
	public int coffeeKinds;
	public Coffee cs; 

	public Vector<JButton> buttons;
	public JScrollPane js;
	
	public MenuCoffee() {
		setLayout(null);
		setBounds(50, 60, 500, 400);
		
		init();
		setButton();
		
		setVisible(true);
		revalidate();
	}
	
	private void init() {
		cs = new Coffee();
		coffeeKinds = cs.getBaseDrink().length; // 최초커피개수
		buttons = new Vector<JButton>();
		
		js = new JScrollPane();
	}
	
	// 버튼 달아주기
	private void setButton() {
		int x = 0;
		int y = 0;
		for(int i=0; i<coffeeKinds; i++) {
			if(i != 0 && i % 4 == 0) {
				x = 0;
				y+= 100;
			}
			// 이미지 
			String tmp = (i+1) +"";
			if(i < 9) tmp = "0"+(i+1);
			Image tempIm = new ImageIcon("kiosk/coffee/coffee_sub"+tmp+".png").getImage().getScaledInstance(80, 90, Image.SCALE_SMOOTH);	
			ImageIcon im = new ImageIcon(tempIm);	
			// JButton 이미지 붙여주기
			JButton b = new JButton();
			b.setName(cs.getBaseDrink()[i]); // 이름 동일하게 붙여놓기
			b.setIcon(im);
			b.setBounds(x, y, 125, 100);
			b.addActionListener(this);
			buttons.add(b);
			add(b);
			x+=125;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<coffeeKinds; i++) {
//			Vector<String> temp = new Vector<String>();

			if(e.getSource() == buttons.get(i) && buttons.get(i).getName().equals(cs.getBaseDrink()[i])) {
//				0(이름), 1(수량), 2(가격)
				boolean check = false; // 없는 제품
				Vector<String> temp = new Vector<String>();
				temp.add(cs.getBaseDrink()[i]); 
				// 이름 먼저 셋팅, 이름과 비교 있으면 객체 바꿔줌
				for(int j=0; j<Controller.drink.size(); j++) {
					if(Controller.drink.get(j).get(0).equals(temp.get(0))) {
						check = true;
						temp = Controller.drink.get(j);
					}
				}
				int plusPrice = 0;
				if(!check) { // 없는 제품
					System.out.println("[없는제품]" + temp.get(0));
					temp.add("1");
					temp.add(cs.getBasePrice()[i]);
					plusPrice = Integer.parseInt(cs.getBasePrice()[i]);
					Controller.drink.add(temp);
				}
				else { // 있는 제품
					System.out.println("[중복제품]" + temp.get(0));
					int plusCnt = Integer.parseInt(temp.get(1))+1;
					int curPrice = Integer.parseInt(temp.get(2)); // 기존 가격
					plusPrice = Integer.parseInt(cs.getBasePrice()[i]); // 음료가격
					
					temp.set(1, plusCnt+"");
					temp.set(2, (curPrice+plusPrice)+"");
				}
				// 테스트 출력
				int testIdx = Controller.drink.indexOf(temp);
				System.out.println(Controller.drink.get(testIdx));
				// 전체 값 변경
				Controller.count.setData(plusPrice);
				Controller.getInstance().table.revalidate();
				Controller.getInstance().table.repaint();				
			}
		}
	}
}
