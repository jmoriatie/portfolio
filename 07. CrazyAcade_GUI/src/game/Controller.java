package game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import data.Data;
import data.Images;

public class Controller extends Thread{

	public Random rn = new Random();
	public Images im = new Images();
	
	final int MAPSIZE = 10; // 가로세로 동일
	
	final int BRICKCNT = 10;
	final int BOMBCNT = 5;
	
	public int bomb;
	private int bombLabel;
	
	public ArrayList<ArrayList<Data>> map;
	private int bombs[][];
	
	private int se;
	private int ga;
	
	public Controller() {
		this.start();
	}
	
	// 벽돌 꺠지면, 아이템 확률적으로 발생
	// 폭탄 개수, 터지는거 차례대로 추적
	public void init() {
		map = new ArrayList<ArrayList<Data>>();
		bombs = new int[MAPSIZE][MAPSIZE];
	}
	
	public void setMap() {
		// 벽, 플레이어, 필드
		// ㄴ 아이템, 폭탄 별도
		bombLabel = 0;
		bomb = this.BOMBCNT;
		// 맵
		int xx = 0;
		int yy = 0;
		for(int i=0; i<this.MAPSIZE; i++) {
			ArrayList<Data> list = new ArrayList<Data>(); // 세로 초기화
			map.add(list);
			for(int j=0; j<this.MAPSIZE; j++) {
				Data data = new Data(xx, yy);
				list.add(data); // 데이터 객체 넣기
				xx += im.IMAGESIZE;
			}
			xx = 0;
			yy += im.IMAGESIZE;
		}
			
		int ridx1;
		int ridx2;
		// 벽
		for(int i=0; i<this.BRICKCNT; i++) {
			ridx1 = rn.nextInt(this.MAPSIZE);
			ridx2 = rn.nextInt(this.MAPSIZE);
			if(this.map.get(ridx1).get(ridx2).getImage() == null) {
				Data data = map.get(ridx1).get(ridx2);
				data.setImage(im.brick);
			}else i--;
		}
		// 바지(플레이어)
		for(int i=0; i<1; i++) {
			ridx1 = rn.nextInt(this.MAPSIZE-2)+1;
			ridx2 = rn.nextInt(this.MAPSIZE-2)+1;
			int check = checkFourSide(ridx1, ridx2);
			
			if(check < 2 && this.map.get(ridx1).get(ridx2).getImage() == null) {
				Data data = map.get(ridx1).get(ridx2);
				data.setImage(im.bazzi);
				this.se = ridx1;
				this.ga = ridx2;
			}else i--;
		}
		// 필드 (나머지 채우기)
		for(int i=0; i<this.MAPSIZE; i++) {
			for(int j=0; j<this.MAPSIZE; j++) {
				if(map.get(i).get(j).getImage() == null) {
					map.get(i).get(j).setImage(im.grass);
				}
			}
		}
	}
	
	// 사방검사
	private int checkFourSide(int rIdx1, int rIdx2) {
		int wallCnt = 0;
		if(map.get(rIdx1-1).get(rIdx2).getImage() == im.brick) wallCnt++;
		if(map.get(rIdx1+1).get(rIdx2).getImage() == im.brick) wallCnt++;
		if(map.get(rIdx1).get(rIdx2-1).getImage() == im.brick) wallCnt++;
		if(map.get(rIdx1).get(rIdx2+1).getImage() == im.brick) wallCnt++;
		return wallCnt; 
	}
	
	public void up() {
		Data cur = this.map.get(se).get(ga);
		if(this.se-1 >= 0) {
			Data next = this.map.get(se-1).get(ga);
			if(move(cur, next)) {
				this.se--;
				System.out.println("위로 이동");
			}
		}
	}
	public void down() {
		Data cur = this.map.get(se).get(ga);
		if(this.se+1 < this.MAPSIZE) {
			Data next = this.map.get(se+1).get(ga);
			if(move(cur, next)) {
				this.se++;
				System.out.println("아래로 이동");
			}
		}
	}
	public void left() {
		Data cur = this.map.get(se).get(ga);
		if(this.ga-1 >= 0) {
			Data next = this.map.get(se).get(ga-1);
			if(move(cur, next)) {
				this.ga--;
				System.out.println("왼쪽으로 이동");
			}
		}
	}
	public void right() {
		Data cur = this.map.get(se).get(ga);
		if(this.ga+1 < this.MAPSIZE) {
			Data next = this.map.get(se).get(ga+1);
			if(move(cur, next)) {
				this.ga++;
				System.out.println("오른쪽으로 이동");
			}
		}
	}
	
	private boolean move(Data cur, Data next) {
		boolean check = false;
		if(next.getImage() == im.grass || next.getImage() == im.item) {
			// 이동장소 아이템일 경우 => 바뀌기 전에 먼저
			if(next.getImage() == im.item) {
				this.bomb++;
				System.out.println("아이템 습득 폭탄+1");
			}
			if(cur.getImage() == im.bomb) { // 현재장소 폭탄이면
				next.setImage(im.bazzi);
			}
			else { // 아니면 잔디
				next.setImage(im.bazzi);
				cur.setImage(im.grass);				
			}
			check = true;
		}
		return check;
	}

	// 폭탄 놓기
	public void bomb() {
		// 저장 BOMBCNT 예외처리
		if(bomb != 0) {
			Data cur = this.map.get(se).get(ga);
			cur.setImage(im.bomb);
			checkBombs();
			this.bomb--;
			System.out.println("폭탄 설치: " +(this.BOMBCNT-this.bomb)+ "개");
		}
	}

	private void checkBombs() {
		// 설치지점에 사방검사 => 폭탄 먼저께 있으면 그 폭탄과 같은 라벨로 변화 ( 터질 때 같이 터져 )
		// ㄴ 양쪽이 같으면 O
		// ㄴ 옆옆게 같으면... => 별도의 쓰레드로 전체검사 => 폭파라벨 계속 맞춰줌 ( 시도 )
		boolean temp[][] = new boolean[MAPSIZE][MAPSIZE];
		int ii = bombLabel+1; // 기존 라벨 => 더 낮은 숫자가 있는지 찾아서
		if(se-1 >= 0 && bombs[se-1][ga] != 0) {
			ii = bombs[se-1][ga]; // 제일 작은걸로 바꿔줌
			temp[se-1][ga] = true; // 바뀐 애들넣어놓기
		}
		if(se+1 < MAPSIZE && bombs[se+1][ga] != 0 && ii > bombs[se+1][ga]) {
			ii = bombs[se+1][ga]; 
			temp[se+1][ga] = true; 
		}
		if(ga-1 >= 0 && bombs[se][ga-1] != 0 && ii > bombs[se][ga-1]) {
			ii = bombs[se][ga-1]; 
			temp[se][ga-1] = true;
		}
		if(ga+1 < MAPSIZE && bombs[se][ga+1] != 0 && ii > bombs[se][ga+1]) {
			ii = bombs[se][ga+1]; 
			temp[se][ga+1] = true;
		}
		
		// 변동이 없으면, bombLabel 더해줌
		// 변동 있으면, bombLabel변동 없이 돌림 ( 원래 있던 라벨의 인덱스를 썼기 떄문)
		System.out.println("bombLabel+1: " + (bombLabel+1));
		System.out.println("ii: " + ii);
		if(bombLabel+1 == ii) {
			bombLabel++;
			bombs[se][ga] = bombLabel;
			
		}
		else {
			// 사방에 있는 애들 순서 다 맞춰줌
			bombs[se][ga] = ii;
			for(int i=0; i<MAPSIZE; i++) {
				for(int j=0; j<MAPSIZE; j++) {
					if(temp[i][j]) {
						bombs[i][j] = ii;
					}
					System.out.print(bombs[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	
	@Override
	public void run() {
		int ii = bombLabel; // 기존 라벨 => 더 낮은 숫자가 있는지 찾아서
		boolean temp[][] = new boolean[MAPSIZE][MAPSIZE];

		if(InGame.gameRun) {
			for(int i=0; i<MAPSIZE; i++) {
				for(int j=0; j<MAPSIZE; j++) {
					if(bombs[i][j] != 0) {
						if(i-1 >= 0 && bombs[i-1][j] != 0) {
							ii = bombs[i-1][j]; // 제일 작은걸로 바꿔줌
							temp[i-1][j] = true; // 바뀐 애들넣어놓기
						}
						if(i+1 < MAPSIZE && bombs[i+1][j] != 0 && ii > bombs[i+1][j]) {
							ii = bombs[i+1][j]; 
							temp[i+1][j] = true; 
						}
						if(j-1 >= 0 && bombs[i][j-1] != 0 && ii > bombs[i][j]) {
							ii = bombs[i][j]; 
							temp[i][j] = true;
						}
						if(j+1 < MAPSIZE && bombs[i][j+1] != 0 && ii > bombs[i][j+1]) {
							ii = bombs[i][j+1]; 
							temp[i][j+1] = true;
						}
					}
				}
				System.out.println();
			}
	
			// 사방에 있는 애들 순서 다 맞춰줌
			for(int i=0; i<MAPSIZE; i++) {
				for(int j=0; j<MAPSIZE; j++) {
					if(temp[i][j]) {
						bombs[i][j] = ii;
					}
					System.out.print(bombs[i][j] + " ");
				}
				System.out.println();
			}
			bombLabel = ii;
		}
	}
	
	
	public void fire() {
		if(bombLabel != 0) {
			Data nextData;
			for(int i=0; i<this.MAPSIZE; i++) {
				for(int j=0; j<this.MAPSIZE; j++) {
					if(bombs[i][j] == 1) {
						if(i-1 >= 0) {
							nextData = map.get(i-1).get(j);
							dropItem(nextData);
						}
						if(i+1 < MAPSIZE) {
							nextData = map.get(i+1).get(j);
							dropItem(nextData);
						}
						if(j-1 >= 0 ) {
							nextData = map.get(i).get(j-1);
							dropItem(nextData);
						}
						if(j+1 < MAPSIZE) {
							nextData = map.get(i).get(j+1);
							dropItem(nextData);
						}
						map.get(i).get(j).setImage(im.grass);
						bombs[i][j] = 0;
					}
				}
			}
			System.out.println("[" + bombLabel + "번 라인 폭탄 폭발]");
			bombLabel--;

			// 폭탄순서 조정 - 하나일떄?
			for(int i=0; i<this.MAPSIZE; i++) {
				for(int j=0; j<this.MAPSIZE; j++) {
					if(bombs[i][j] != 0) {
						bombs[i][j]--;
					}
				}
			}
		}
	}
	
	private void dropItem(Data nextData) {
		if(nextData.getImage() == im.brick) {
			int rItem = rn.nextInt(1); // 0,1  => 아이템 확률
			if(rItem == 0) {
				nextData.setImage(im.item);		
				System.out.println("[item]");
			}
			else {
				nextData.setImage(im.grass);
			}
		}
		if(nextData.getImage() == im.bazzi || map.get(se).get(ga).getImage() == im.bomb) {
			InGame.gameRun = false;
			map.get(se).get(ga).setImage(im.grass);
			System.out.println("[DEAD]");
		}
	}
	
	public void reset() {
		this.map.clear();
		init();
		setMap();
	}
	
	
}
