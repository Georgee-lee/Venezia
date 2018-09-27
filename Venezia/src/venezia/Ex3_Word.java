package venezia;

import java.awt.Color;

public class Ex3_Word extends Thread {
	
	int x,y,speed=250;
	String str;
	Color color;
	Ex3_Vene p; // ���� p�� ���� ��ü�� ����Ű�µ�, �� ĵ������ arraylist�� �����ϱ� ���� �ʿ��ϴ�. (= p.��¼��)
	boolean flag = true; //false�� �����Ͽ� �������� �����带 �Ҹ��ų �� �ֵ��� �ϴ� ����
	
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
			if(y > p.can.getSize().height - 20)  //ĵ���� �ٴڿ� ���ڰ�ü�� ������ ��� �����带 �Ҹ��Ű�� ���� �ݺ��� Ż��
				break;
			
			y += 10;
			
			//p.can.repaint();
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {}  
		}
		p.list.remove(this);  //���� ��ü�� ����� ArrayList���� ���� ��ü�� �ּҸ� ���ڷ� �־� �����ϵ��� ���ͷ����� ���
	}

}
