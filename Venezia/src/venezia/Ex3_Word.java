package venezia;

import java.awt.Color;

public class Ex3_Word extends Thread {
	
	int x,y,speed=250;
	String str;
	Color color;
	Ex3_Vene p; // 변수 p는 베네 객체를 가리키는데, 즉 캔버스와 arraylist를 접근하기 위해 필요하다. (= p.어쩌구)
	boolean flag = true; //false로 변경하여 언제든지 스레드를 소멸시킬 수 있도록 하는 변수
	
	public Ex3_Word(int x, int y, Ex3_Vene p) {
		this.p = p;
		this.x = x;
		this.y = y;
		
		color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	
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
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Ex3_Vene getP() {
		return p;
	}
	public void setP(Ex3_Vene p) {
		this.p = p;
	}
	@Override
	public void run() {
		while(flag) {
			if(y > p.can.getSize().height - 20)  //캔버스 바닥에 글자객체가 도달할 경우 스레드를 소멸시키기 위해 반복문 탈출
				break;
			
			y += 10;
			
			//p.can.repaint();
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {}  
		}
		p.list.remove(this);  //현재 객체가 저장된 ArrayList에서 현재 객체의 주소를 인자로 주어 삭제하도록 이터레이터 사용
	}

}
