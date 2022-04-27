package game;

import javax.swing.JFrame;

public class CrazyAcade extends JFrame {
	
	private static CrazyAcade instance = new CrazyAcade();
	
	static final int FIELDHOR = 700;
	static final int FIELDVER = 528;
	
	private CrazyAcade() {
		setLayout(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, FIELDHOR, FIELDVER);
		
		add(new InGame());
		
		setVisible(true);
		revalidate();
	}
	
	public static CrazyAcade getInstance() {
		return instance;
	}	
}
