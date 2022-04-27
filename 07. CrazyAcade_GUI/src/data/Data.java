package data;

import java.awt.Image;

public class Data {
	private String name;
	private int x,y;
	private Image image;
	
	public Data(int x, int y) {
		this.x = x;
		this.y = y;
		image = null;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
