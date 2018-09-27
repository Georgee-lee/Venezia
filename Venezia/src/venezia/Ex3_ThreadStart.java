package venezia;

public class Ex3_ThreadStart extends Thread {

	Ex3_Vene p;
	Ex3_Word w;
	int n,t,num;  // n은 난수를 발생시켜서 배열의 주소값을 선택하고, num은 ArrayList에 크기를 20까지만
	
	Ex3_ThreadStart(Ex3_Vene p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(num < 30) {
					t = (int)(Math.random()*801+10);
					Ex3_Word w = new Ex3_Word(t,-10, p);
					n = (int)(Math.random()*45);
					w.setStr(p.word[n]);
					p.list.add(w);
					w.start();
					num++;
					Thread.sleep(1500);
					if(num > 15)
						w.speed -= 100;
				}
			} catch (Exception e) {}
		}
	}
}
