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
	String[] word = {"Ŀ��","��ǻ��","��Ʈ��","������","��ũ���Ľ�","�����ʹ�","������","��������","���ù���", "���ٺ��ϴ�",
			"������", "�����Ǵ�", "��Ż", "�ڵ���", "���κ���", "�ð�", "�ڵ���", "������", "���", "�����","�ɰ���", "���±�", "���´���", "���ù���", 
			"�׸�����", "�׸���", "�׸���", "���׸���", "�񳪸�", "�üֱ�", "����", "�����", "�ط����ظ�", "����", "����", "�Ƹ���", 
			"���Ǿ�", "Ǫ��", "�޺��巹�ϴ�", "�ܹ�", "����", "Ÿ��", "���", "���ζ���" 
			};


	ArrayList<Ex3_Word> list = new ArrayList<Ex3_Word>();  	//�� �ܾü���� �����ϴ� ArrayList
	
	Image buf;
	Graphics buf_g;
	
	int n=0, c=0;
	
	Canvas can = new Canvas() {

		@Override
		public void paint(Graphics g) {
			buf = createImage(this.getSize().width, this.getSize().height); //�ӽ� ĵ���� ����
			
			buf_g = buf.getGraphics();//�ӽ� ĵ������ �׸��� �׸� �� �ִ� �� ��ü ���� (=Graphics)
			
			for(Ex3_Word w : list) {
				buf_g.drawString(w.str, w.x, w.y);  //�ӽ� ĵ������ ArrayList�� ����� �ܾü�� �׸���.
			}
			
			g.drawImage(buf, 0, 0, this);  //�ӽ� ĵ������ ��¥ ĵ������ �׸���. (=draw Image)
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
		panel2.add(score_lb = new JLabel("0��"));
		panel2.add(start_bt = new JButton("����"));

		add(panel2, BorderLayout.NORTH);
		add(can);
		this.setTitle("����ġ��");
		
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
				//JTextField���� ������ �Է� �� ���͸� ���� �� ����
				String st = input.getText().trim();
				
				for(Ex3_Word w : list) {
					if(st.equals(w.getStr())) {
						w.flag = false;
						c += 10;
						score_lb.setText(c+"��");
						break;
					}
				}				
				
				//JTextField û��
				input.setText("");
			}
		});
		
		new Thread() {  //�� Thread�� ���α׷��� ����Ǹ� ���� �� ���� ��� repaint�� ����.

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
