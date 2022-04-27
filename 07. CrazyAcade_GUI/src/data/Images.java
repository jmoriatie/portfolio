package data;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	
	public final int IMAGESIZE = 50;

	public Image bazzi;
	public Image bomb;
	public Image brick;
	public Image grass;
	public Image item;
	
	public Images() {
		bazzi = new ImageIcon("images/bazzi.jpg").getImage().getScaledInstance(IMAGESIZE, IMAGESIZE, Image.SCALE_SMOOTH);
		bomb = new ImageIcon("images/bomb.jpg").getImage().getScaledInstance(IMAGESIZE, IMAGESIZE, Image.SCALE_SMOOTH);
		brick = new ImageIcon("images/brick.jpg").getImage().getScaledInstance(IMAGESIZE, IMAGESIZE, Image.SCALE_SMOOTH);
		grass = new ImageIcon("images/grass.jpg").getImage().getScaledInstance(IMAGESIZE, IMAGESIZE, Image.SCALE_SMOOTH);
		item = new ImageIcon("images/item.jpg").getImage().getScaledInstance(IMAGESIZE, IMAGESIZE, Image.SCALE_SMOOTH);
	}
	
}
