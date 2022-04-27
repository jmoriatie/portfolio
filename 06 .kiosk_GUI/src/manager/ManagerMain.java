package manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import contents.MenuCoffee;
import contents.MenuTeaNAde;
import contents.SaveNLoad;

public class ManagerMain extends JFrame implements ActionListener{

	DecimalFormat formatter = new DecimalFormat("###,###,###");
	SaveNLoad fc;
	
	JFrame changeF;
	JTextField inId, inPw;
	JLabel id, pw, tempL;
	JButton submit;
	
	MenuCoffee coffee;
	MenuTeaNAde teaAde;
	
	JButton exit, sale, changeInfo, temp2,temp3;
	
	JScrollPane js;
	JTable table;
	
	JLabel text;
	
	Vector<String> line;
	
	public ManagerMain() {
		super("키오스크 매니저");
		setLayout(null);
		setBounds(100, 100, 500, 500);
		
		init();
		setTable();
		
		setVisible(true);
		revalidate();
		repaint();
		js.revalidate();
		js.repaint();
	}
	
	private void init() {
		fc = SaveNLoad.getInstance();
		
		exit = new JButton("EXIT");
		exit.setBounds(250-40, 500-70, 70, 30);
		exit.addActionListener(this);
		add(exit);
		
		sale = new JButton("판매 조회");
		sale.setBounds(30, 10, 80, 40);
		sale.addActionListener(this);
		add(sale, 0);

		changeInfo = new JButton("정보 변경");
		changeInfo.setBounds(120, 10, 80, 40);
		changeInfo.addActionListener(this);
		add(changeInfo, 0);

		temp2 = new JButton("미정");
		temp2.setBounds(210, 10, 80, 40);
		temp2.addActionListener(this);
		add(temp2, 0);

		temp3 = new JButton("미정");
		temp3.setBounds(300, 10, 80, 40);
		temp3.addActionListener(this);
		add(temp3, 0);
		
		text = new JLabel();
		text.setBounds(10, 50, 490, 40);
		add(text, 1);
	}

	private void setTable() {
		line = new Vector<String>();
		line.add("제품명");
		line.add("수량");
		line.add("가격");
		
		table = new JTable(fc.sumData, this.line);
		table.setBounds(0, 100, 500, 300);
		add(table);
	
		js = new JScrollPane(table);
		js.setBounds(0, 110, 500, 290);
		js.setAutoscrolls(true);
		add(js);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			System.out.println("매니저모드 종료<<<<<<<<<<<");
			this.dispose();
		}
		if(e.getSource() == sale) {
			System.out.println("[판매조회]");
			showAmount();
		}
		if(e.getSource() == changeInfo) {
			System.out.println("[매니저 정보변경]");
			setLogin();
		}
		if(e.getSource() == submit) {
			if(changeManagerInfo()) {
				System.out.println("[매니저 정보변경 완료]");
			}
		}
	}

	private void showAmount() {
		int saleCount = 0;
		int saleAmount = 0;
		for(int i=0; i<fc.sumData.size(); i++) {
			saleCount+= Integer.parseInt(fc.sumData.get(i).get(1));
			saleAmount+= Integer.parseInt(fc.sumData.get(i).get(2));
		}
		System.out.println(String.format("매출액 %s원, 판매수량 %d개",formatter.format(saleAmount),saleCount));
		text.setText(String.format("매출액 %s원, 판매수량 %d개",formatter.format(saleAmount),saleCount));
	}
	
	private boolean changeManagerInfo() {
		boolean check = false;
		
		if(!inId.getText().equals("") && !inId.getText().equals("변경할 id 입력")) {
			if(!inPw.getText().equals("") && !inPw.getText().equals("변경할 pw 입력")) {
				ManagerInfo.getInstance().setId(inId.getText());
				ManagerInfo.getInstance().setPw(inPw.getText());
				System.out.println("변경된 id: " + ManagerInfo.getInstance().getId());
				System.out.println("변경된 pw: " + ManagerInfo.getInstance().getPw());
				
				
				changeF.dispose();
				tempL.setText("");
				check = true;
			} else {
				System.out.println("[pw 확인 필요]");
				tempL.setText("pw를 확인하세요");
			}
		} else {
			System.out.println("[id 확인 필요]");
			tempL.setText("id를 확인하세요");
		}
		return check;		
	}
	
	private void setLogin() {
		changeF = new JFrame("매니저 정보변경");
		changeF.setBounds(100, 100, 200, 150);
				
		inId = new JTextField();
		inId.setBounds(50, 10, 100, 20);
		inId.setText("변경할 id 입력");
		
		inPw = new JTextField();
		inPw.setBounds(50, 40, 100, 20);
		inPw.setText("변경할 pw 입력");

		id = new JLabel("ID");
		id.setBounds(10, 10, 30, 20);
		
		pw = new JLabel("pw");
		pw.setBounds(10, 40, 30, 20);

		submit = new JButton("확인");
		submit.addActionListener(this);
		submit.setBounds(70, 65, 50, 30);
		
		tempL = new JLabel();
		tempL.setBounds(0, 0, 200, 150);
		tempL.setHorizontalAlignment(JLabel.CENTER);
		tempL.setVerticalAlignment(JLabel.BOTTOM);
		tempL.setForeground(Color.red);
			
		changeF.add(inId, 0);
		changeF.add(inPw, 1);
		changeF.add(id, 2);
		changeF.add(pw, 3);
		changeF.add(submit, 4);
		changeF.add(tempL, 5);
		
		changeF.setVisible(true);
	}
	

}
