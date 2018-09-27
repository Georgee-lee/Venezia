package venezia;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex3_Vene extends JFrame {
	String[] word = {"커피","컴퓨터","노트북","개발자","인크레파스","차사고싶다","가라사니","가무리다","가시버시", "각다분하다",
			"갈개꾼", "간정되다", "가탈", "핸드폰", "메인보드", "시계", "자동차", "데이터", "어레이", "가즈아","꽃가람", "가온길", "가온누리", "가시버시", 
			"그린나래", "그린비", "그린내", "예그리나", "비나리", "늘솔길", "윤슬", "물비늘", "해류뭄해리", "헤윰", "나린", "아리아", 
			"수피아", "푸실", "달보드레하다", "단미", "아토", "타니", "까미", "꼬두람이" 
			};


	ArrayList<Ex3_Word> list = new ArrayList<Ex3_Word>();  	//각 단어객체들을 저장하는 ArrayList
	
	Image buf;
	Graphics buf_g;
	
	int n=0, c=0;
	
	Canvas can = new Canvas() {

		@Override
		public void paint(Graphics g) {
			buf = createImage(this.getSize().width, this.getSize().height); //임시 캔버스 생성
			
			buf_g = buf.getGraphics();//임시 캔버스에 그림을 그릴 수 있는 붓 객체 생성 (=Graphics)
			
			for(Ex3_Word w : list) {
				buf_g.drawString(w.str, w.x, w.y);  //임시 캔버스에 ArrayList에 저장된 단어객체를 그린다.
			}
			
			g.drawImage(buf, 0, 0, this);  //임시 캔버스를 진짜 캔버스에 그린다. (=draw Image)
		}
		
		@Override
		public void update(Graphics g) {
			paint(g);
		}
	};
	
	JTextField input;
	JPanel panel, panel2;
	JButton start_bt;
	JLabel score_lb;
	
	public Ex3_Vene() {
		
		panel = new JPanel();
		panel.add(input = new JTextField(17));
		panel.setBackground(Color.BLACK);

		add(panel, BorderLayout.SOUTH);
		
		panel2 = new JPanel();
		panel2.add(score_lb = new JLabel("0점"));
		panel2.add(start_bt = new JButton("시작"));

		add(panel2, BorderLayout.NORTH);
		add(can);
		this.setTitle("베네치아");
		
		start_bt.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Ex3_ThreadStart st = new Ex3_ThreadStart(Ex3_Vene.this);
				st.start();
			}
		});
		
		this.setBounds(560, 130, 900, 700);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JTextField에서 데이터 입력 후 엔터를 쳤을 때 수행
				String st = input.getText().trim();
				
				for(Ex3_Word w : list) {
					if(st.equals(w.getStr())) {
						w.flag = false;
						c += 10;
						score_lb.setText(c+"점");
						break;
					}
				}				
				
				//JTextField 청소
				input.setText("");
			}
		});
		
		new Thread() {  //이 Thread는 프로그램이 실행되면 끝날 때 까지 계속 repaint만 실행.

			@Override
			public void run() {
				while(true) {
					can.repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {}
				}
			}
		}.start();;
		
	}
	

	public static void main(String[] args) {
		new Ex3_Vene();
	}

}
