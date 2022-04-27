package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import contents.MenuCoffee;
import contents.MenuTeaNAde;
import contents.OrderCount;

//[패널 변경용]
//add(패널);
//this.getContentPane().add(패널);
//setContentPane(패널);
//ㄴ 위 세개가 다 동일함
//ㄴ Pane: 쌓아올리는 유리장의 개념으로, 각 Container가 가지고 있음
//		ㄴ 따라서, 여러가지 목적이 있다면 이걸 사용해서 활용이 가능함
//ㄴ setContentPane을 이용해 패널 변경 가능(컴포넌트 같이 떨어져 나가는거 주의)

// 메인프레임 역할
public class Controller extends JFrame implements ActionListener{
	// 관리자메뉴 => 판매상품, 판매금액, 총액 출력 
	// 검색기능
	
	private static Controller instance = null;

	public final int COFFEE = 1;
	public final int TEANADE = 2;
	
	public static MenuCoffee menuCoffee = null;
	public static MenuTeaNAde menuTeaNAde = null;
	public static OrderCount count = null;
	
	JButton coffeeB;
	JButton TeaNAdeB;

	private Vector<String> line;
	public JTable table;
	JScrollPane js;

	public int state = 1;
	// Coffee -> 0(이름), 1(수량), 2(가격)
	// ㄴ> 인덱스 + 버튼 이름 함께 처리
	public static Vector<Vector<String>> drink;
	
	private Controller() {
		super("키오스크");
		setLayout(null);
		setBounds(100, 100, 600, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();
		setTable();
		
		repaint();
		revalidate();
		setVisible(true);
	}

	// 싱글톤
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	private void init() {
		drink = new Vector<Vector<String>>();
		menuCoffee = new MenuCoffee();
		menuTeaNAde = new MenuTeaNAde();
		count = new OrderCount();
		this.getContentPane().add(menuCoffee);
		this.getContentPane().add(count);
		
		coffeeB = new JButton("COFFEE");
		TeaNAdeB = new JButton("TEA & ADE");

		coffeeB.setBounds(600/2 -110, 20, 100, 30);
		TeaNAdeB.setBounds(600/2 + 10 , 20, 100, 30);

		coffeeB.addActionListener(this);
		TeaNAdeB.addActionListener(this);

		add(coffeeB);
		add(TeaNAdeB);

	}
	
	private void setTable() {
		line = new Vector<String>();
		line.add("제품명");
		line.add("수량");
		line.add("가격");
		
		// 테이블
		table = new JTable(Controller.drink, line);
		table.setBounds(50, 470, 500, 400);
		table.setFont(new Font("", Font.PLAIN, 15));
//		table.setGridColor(Color.black);
		table.setCellEditor(null);
		table.setDragEnabled(true);
		table.setCellSelectionEnabled(true);
		add(table);
		
		// 스크롤
		js = new JScrollPane(table);
		js.setAutoscrolls(true);
		js.setBounds(50, 470, 500, 200);
		add(js);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.coffeeB) {
			state = 1;
			change();
		}
		else if(e.getSource() == this.TeaNAdeB) {
			state = 2;
			change();
		}
	}
	
	public void change() {
		if(this.state == COFFEE) {
			this.getContentPane().removeAll();
			this.getContentPane().add(menuCoffee);
			this.getContentPane().add(count);
			this.getContentPane().add(coffeeB);
			this.getContentPane().add(TeaNAdeB);
			add(js);
			this.revalidate();
			this.repaint();
			state = 1;
		}
		else if(this.state == TEANADE) {
			this.getContentPane().removeAll();
			this.getContentPane().add(menuTeaNAde);
			this.getContentPane().add(count);
			add(js);
			add(coffeeB);
			add(TeaNAdeB);
			this.revalidate();
			this.repaint();
			state = 2;
		}
	}
}
