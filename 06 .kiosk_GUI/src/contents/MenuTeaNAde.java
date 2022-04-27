package contents;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import data.TeaNAde;
import main.Controller;
import util.Util;

public class MenuTeaNAde extends Util{
	
	public int TeaNAdeKinds;
	public TeaNAde tas; 

	public Vector<JButton> buttons;
	public JScrollPane js;
	
	public MenuTeaNAde() {
		setLayout(null);
		setBounds(50, 60, 500, 400);
		
		init();
		setButton();
		
		setVisible(true);
	}
	
	
	private void init() {
		tas = new TeaNAde();
		TeaNAdeKinds = tas.getBaseDrink().length;
		buttons = new Vector<JButton>();
		
		js = new JScrollPane();
	}
	
	// 버튼 달아주기
	private void setButton() {
		int x = 0;
		int y = 0;
		for(int i=0; i<TeaNAdeKinds; i++) {
			if(i != 0 && i % 4 == 0) {
				x = 0;
				y+= 100;
			}
			// 이미지 
			String tmp = (i+1) +"";
			if(i < 9) tmp = "0"+(i+1);
			Image tempIm = new ImageIcon("kiosk/teaNAde/tea_sub"+tmp+".png").getImage().getScaledInstance(80, 90, Image.SCALE_SMOOTH);	
			ImageIcon im = new ImageIcon(tempIm);	
			// JButton 이미지 붙여주기
			JButton b = new JButton();
			b.setName(tas.getBaseDrink()[i]); // 이름 동일하게 붙여놓기
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
		for(int i=0; i<TeaNAdeKinds; i++) {
			if(e.getSource() == buttons.get(i) && buttons.get(i).getName().equals(tas.getBaseDrink()[i])) {
//				0(이름), 1(수량), 2(가격)
				boolean check = false; // 없는 제품
				Vector<String> temp = new Vector<String>();
				temp.add(tas.getBaseDrink()[i]); 
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
					temp.add(tas.getBasePrice()[i]);
					plusPrice = Integer.parseInt(tas.getBasePrice()[i]);
					Controller.drink.add(temp);
				}
				else { // 있는 제품
					System.out.println("[중복제품]" + temp.get(0));
					int plusCnt = Integer.parseInt(temp.get(1))+1;
					int curPrice = Integer.parseInt(temp.get(2)); // 기존 가격
					plusPrice = Integer.parseInt(tas.getBasePrice()[i]); // 음료가격
					
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
