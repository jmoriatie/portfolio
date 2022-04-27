package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.Data;
import util.Util;

public class InGame extends Util implements Runnable{

	private Controller c = new Controller();
	
	JLabel text;
	JLabel time;
	JButton reset;
	
	public static boolean gameRun;
	
	public InGame() {
		setLayout(null);
		setBounds(0, 0, CrazyAcade.FIELDHOR, CrazyAcade.FIELDVER);
		
		addKeyListener(this);
		gameRun = true;
		c.init();
		c.setMap();
		
		setGame();
		
		setVisible(true);
	}
	
	private void setGame() {
		time = new JLabel();
		reset = new JButton();
		text = new JLabel();
		
		time.setBounds(CrazyAcade.FIELDHOR - 150, 100, 100, 30);
		reset.setBounds(CrazyAcade.FIELDHOR - 160, 150, 80, 50);
		text.setBounds(CrazyAcade.FIELDHOR - 150, 200, 100, 60);
		
		reset.setText("RESET");
		
		// 테스트 텍스트
		time.setText("timeTest");
		text.setText("폭탄: " + c.bomb + "개"); // 폭탄 개수 셋팅
		
		time.setVerticalAlignment(JLabel.TOP);
		
		reset.addKeyListener(this);
		reset.addActionListener(this);
		
		add(text);
		add(time);
		add(reset);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// 상(38), 하(40), 좌(37), 우(39)
		// + 폭탄 놓기(b,66), 터트리기(f,70) 
		if(gameRun) {
			if(e.getKeyCode() == 38) c.up();
			else if(e.getKeyCode() == 40) c.down();
			else if(e.getKeyCode() == 37) c.left();
			else if(e.getKeyCode() == 39) c.right();
			if(e.getKeyCode() == 66) c.bomb();
			if(e.getKeyCode() == 70) c.fire();
			
			text.setText("폭탄: " + c.bomb + "개");
		}
		
		if(!gameRun){
			text.setText("GAME OVER"); 
			System.out.println("[GAME OVER]");
			JOptionPane.showMessageDialog(null, "배찌가 죽었습니다");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.reset) {
			gameRun = true;
			c.reset();
			text.setText("폭탄: " + c.bomb + "개"); 
			System.out.println("[RESET]");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0; i<c.MAPSIZE; i++) {
			for(int j=0; j<c.MAPSIZE; j++) {
				Data data = c.map.get(i).get(j);
				if(data.getImage() == c.im.bazzi) g.drawImage(c.im.bazzi, data.getX(), data.getY(), null);
				if(data.getImage() == c.im.bomb) g.drawImage(c.im.bomb, data.getX(), data.getY(), null);
				if(data.getImage() == c.im.brick) g.drawImage(c.im.brick, data.getX(), data.getY(), null);
				if(data.getImage() == c.im.grass) g.drawImage(c.im.grass, data.getX(), data.getY(), null);
				if(data.getImage() == c.im.item) g.drawImage(c.im.item, data.getX(), data.getY(), null);
			}
		}
		
		repaint();
	}
}
