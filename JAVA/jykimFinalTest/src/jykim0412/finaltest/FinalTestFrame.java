package jykim0412.finaltest;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Calendar;

@SuppressWarnings("serial")
public class FinalTestFrame extends JFrame {
	
	JPanel p1, p11, p2, p12, p13;
	JButton btn0, btn1;
	VolunteerManager jhchi = new VolunteerManager("좋은세상만들기"); // 본인이름 이니셜 객체
	boolean flag = false;
	JTextField f1, f2, f3, f4;
	VolunteerManager jyKim = new VolunteerManager("jyKim");
	JComboBox cb;
	
	public FinalTestFrame() {
		this("200000 홍길동"); // 본인 학번 이름
		
	}
	
	public FinalTestFrame(String title) {
		super(title);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}	
	
	public void init() {		
		p1 = new JPanel();
		p11 = new JPanel();
		p12 = new JPanel();
		p13 = new JPanel();
		p2 = new JPanel();
		cb = new JComboBox();
		btn0 = new JButton("STOP");
		btn1 = new JButton("추가");
		f1 = new JTextField(15);
		f2 = new JTextField(5);
		f3 = new JTextField(5);
		f4 = new JTextField(10);
		p12.add(f1);
		p12.add(f2);
		p12.add(f3);
		p13.add(cb);
		p13.add(f4);
		p1.setLayout(new GridLayout(3,1));
		p11.setLayout(new FlowLayout());
		p12.setLayout(new FlowLayout());
		p13.setLayout(new FlowLayout());
		MyLabel label = new MyLabel();
		p11.add(label);
		p11.add(btn0);
		btn0.addActionListener(e->{
			flag = true;
		});
		btn1.addActionListener(e->{
			Volunteer v = new Volunteer(f1.getText(), Integer.parseInt(f2.getText()), Integer.parseInt(f3.getText()));
			String str = jyKim.addVolunteer(v);
			JOptionPane.showMessageDialog(null, str);
		});
		p1.add(p11);
		p1.add(p12);
		p1.add(p13);
		this.add(p1);
	}
	@SuppressWarnings("serial")
	class MyLabel extends JLabel implements Runnable{	
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

		MyLabel(){
			//this.setFont(new Font("궁서체", Font.BOLD, 24));
			this.setOpaque(true);
			//this.setBackground(Color.GREEN);
			this.setHorizontalAlignment(CENTER);
			Thread th = new Thread(this);
			th.start();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				this.setText(date.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
					if(flag==true) {
						return;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}




















