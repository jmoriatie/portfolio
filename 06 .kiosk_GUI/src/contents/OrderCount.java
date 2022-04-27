package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Controller;
import manager.ManagerInfo;
import manager.ManagerMain;
import util.Util;

public class OrderCount extends Util{
	
	ManagerMain m = null;
	SaveNLoad fc;
	
	JFrame loginF;
	JTextField inId;
	JTextField inPw;
	JLabel id;
	JLabel pw;
	JLabel tempL;
	JButton submit;
	
	JLabel countLab;
	JLabel sumLab;
	JLabel count;
	JLabel sum;
	
	JButton pay;
	JButton back;
	JButton manager;
	
	public int orderCnt = 0;
	public int sumPrice = 0;
	
	public OrderCount() {
		setLayout(null);
		setBounds(50, 680, 500, 160);
		init();
		setButtons();
		setLabels();
		
		setVisible(true);
	}
	
	private void init() {
		fc = SaveNLoad.getInstance();
	}
	
	private void setLabels() {
		countLab = new JLabel();
		sumLab = new JLabel();
		count = new JLabel();
		sum = new JLabel();
		
		countLab.setBounds(10, 0, 150, 50);
		sumLab.setBounds(10, 60, 150, 50);
		count.setBounds(170, 0, 150, 50);
		sum.setBounds(170, 60, 150, 50);
		
		countLab.setText("주문수량");
		sumLab.setText("총 금액");
		count.setText(String.format("%d개", this.orderCnt));
		sum.setText(String.format("%d원", this.sumPrice));
		
		countLab.setFont(new Font("", Font.BOLD, 18));
		sumLab.setFont(new Font("", Font.BOLD, 18));
		count.setFont(new Font("", Font.BOLD, 18));
		sum.setFont(new Font("", Font.BOLD, 18));
		
		countLab.setForeground(Color.white);
		sumLab.setForeground(Color.white);
		
		add(countLab);
		add(sumLab);
		add(count);
		add(sum);
	}
	
	private void setButtons() {
		pay = new JButton("계산하기");
		back = new JButton("주문 초기화");
		manager = new JButton("매니저모드");
		
		pay.setBounds(150, 120, 60, 40);
		back.setBounds(220, 120, 80, 40);
		manager.setBounds(500-90, 130, 80, 20);
		
		manager.setForeground(Color.gray);
		
		pay.addActionListener(this);
		back.addActionListener(this);
		manager.addActionListener(this);

		add(pay);
		add(back);
		add(manager);
	}
	
	public void setData(int plusPrice) {
		orderCnt++;
		sumPrice += plusPrice;
		count.setText(String.format("%d개", this.orderCnt));
		sum.setText(String.format("%d원", this.sumPrice));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pay) {
			if(orderCnt != 0) {
				fc.save(); // 임시저장
				System.out.println("결제 완료");
				allClear();
				JOptionPane.showMessageDialog(null, "결제가 완료되었습니다");
			}
		}
		if(e.getSource() == back) {
			allClear();
			System.out.println("주문초기화");
		}
		if(e.getSource() == manager) {
			setLogin();
		}
		if(e.getSource() == submit) {
			if(login()) {
				System.out.println("매니저 모드 진입 >>>>>");
				m = new ManagerMain();
			}
		}
	}
	
	private void allClear() {
		Controller c = Controller.getInstance();
		c.state = c.COFFEE;
		c.change();
	
		Controller.drink.removeAllElements();
		orderCnt = 0;
		sumPrice = 0;
		count.setText(String.format("%d개", this.orderCnt));
		sum.setText(String.format("%d원", this.sumPrice));
	}
	
	private void setLogin() {
		loginF = new JFrame("매니저 로그인");
		loginF.setBounds(100, 100, 200, 150);
				
		inId = new JTextField();
		inId.setBounds(50, 10, 100, 20);
		inId.setText("id 입력");
		
		inPw = new JTextField();
		inPw.setBounds(50, 40, 100, 20);
		inPw.setText("pw 입력");

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
			
		loginF.add(inId, 0);
		loginF.add(inPw, 1);
		loginF.add(id, 2);
		loginF.add(pw, 3);
		loginF.add(submit, 4);
		loginF.add(tempL, 5);
		
		loginF.setVisible(true);
	}
	private boolean login() {
		boolean check = false;
		if(inId.getText().equals(ManagerInfo.getInstance().getId())) {
			if(inPw.getText().equals(ManagerInfo.getInstance().getPw())) {
				System.out.println("[로그인 성공]");
				loginF.dispose();
				tempL.setText("");
				check = true;
			} else {
				System.out.println("[pw불일치]");
				tempL.setText("pw를 확인하세요");
			}
		} else {
			System.out.println("[id불일치]");
			tempL.setText("id를 확인하세요");
		}
		return check;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 150, 50);
		g.fillRect(0, 60, 150, 50);
		g.setColor(Color.lightGray);
		g.fillRect(160, 0, 340, 50);
		g.fillRect(160, 60, 340, 50);
		repaint();
	}
	

}
