package jykimex01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	
	int height;
	int width;
	JPanel p1, p2;
	
	public MyFrame() {
		this("202011272 ±èÀçÀ±");
		
	}

	public MyFrame(String title){
		super(title);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		this.width = screensize.width;
		this.height = screensize.height;
		this.setSize(width/2, height/2);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}
	
	
	public void init() {
		Container c = this.getContentPane();
		//c.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton btn1 = new JButton("RED");
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				p2.setBackground(Color.red);
			}
			
		});
		JButton btn2 = new JButton("GREEN");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				p2.setBackground(Color.green);
			}
			
		});
		JButton btn3 = new JButton("BLUE");
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				p2.setBackground(Color.blue);
			}
			
		});
		//JButton btn4 = new JButton("4¹ø ¹öÆ°");
		//JButton btn5 = new JButton("5¹ø ¹öÆ°");
		p1 = new JPanel();
		p1.setBackground(Color.ORANGE);
		FlowLayout lm2 = (FlowLayout)p1.getLayout();
		lm2.setHgap(20);
		//lm2.setVgap(20);
		p2 = new JPanel();
		p2.setBackground(Color.red);
		p1.add(btn1);
		p1.add(btn2);
		p1.add(btn3);
		
		c.add(p1, BorderLayout.NORTH);
		
		c.add(p2, BorderLayout.CENTER);
//		c.add(btn2, BorderLayout.WEST);
//		c.add(btn3, BorderLayout.EAST);	
//		c.add(btn5, BorderLayout.SOUTH);
		
	}
}
