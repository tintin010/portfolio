package jykim.week11.lab01;
	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Container;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	public class MyFrame extends JFrame {
		
		int height;
		int width;
		int xpos = 100, ypos = 100;
		JPanel p1, p2;
		JLabel imgChar = new JLabel();
		ImageIcon img = new ImageIcon("img/char1.png");
		
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
			
			initp1();
			initp2();
			
			c.add(p1, BorderLayout.WEST);
			c.add(p2, BorderLayout.CENTER);
			
		}

		private void initp2() {
			p2 = new JPanel();
			p2.setLayout(null);
			imgChar.setIcon(img);
			imgChar.setBounds(xpos, ypos, img.getIconWidth(), img.getIconHeight());
			p2.add(imgChar);
		}

		private void initp1() {

			p1 = new JPanel(new GridLayout(4,1,10,10));
			JButton btn1 = new JButton("À§");
			btn1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(ypos >= 10) {
						ypos -=10;
						imgChar.setBounds(xpos, ypos, img.getIconWidth(), img.getIconHeight());
					}
				}
				
			});
			JButton btn2 = new JButton("¾Æ·¡");
			btn2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(ypos <= (p2.getHeight() - img.getIconHeight() - 10)) {
						ypos += 10;
						imgChar.setBounds(xpos, ypos, img.getIconWidth(), img.getIconHeight());
					}
				}
				
			});
			JButton btn3 = new JButton("¿ÞÂÊ");
			btn3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(xpos >= 10) {
						xpos -= 10;
						imgChar.setBounds(xpos, ypos, img.getIconWidth(), img.getIconHeight());
					}
				}
				
			});
			JButton btn4 = new JButton("¿À¸¥ÂÊ");
			btn4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(xpos <= (p2.getWidth() - img.getIconWidth() - 10)) {
						xpos += 10;
						imgChar.setBounds(xpos, ypos, img.getIconWidth(), img.getIconHeight());
					}
				}
				
			});
			
			p1.add(btn1);
			p1.add(btn2);
			p1.add(btn3);
			p1.add(btn4);
		}
	}
