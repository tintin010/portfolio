package jykim.konkuk.week12;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MyFrame extends JFrame implements ItemListener{
	String[] data = {"사과", "포도", "배" };
	ButtonGroup g = new ButtonGroup();
	
	JRadioButton[] check = new JRadioButton[3]; 
	JLabel[] label = new JLabel[3];
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	
	public MyFrame() {
		this("200000 홍길동");
	}
	
	public MyFrame(String title) {
		super(title);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}
	
	public void init() {
		for(int i=0; i<data.length; i++) {
			check[i] = new JRadioButton(data[i]);
			g.add(check[i]);
			//check[i] = new JCheckBox(data[i]);	
			check[i].setBorderPainted(true);
			check[i].addItemListener(this);
			north.add(check[i]);
			label[i] = new JLabel(data[i]);
//			label[i].setVisible(false);
//			center.add(label[i]);
		}
		
		this.getContentPane().add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int i = -1;
		if(e.getSource()==check[0]) {
			i =0;
		}else if(e.getSource()==check[1]) {
			i =1;
		}else if(e.getSource()==check[2]) {
			i =2;
		}
		
		if(i>=0) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				//label[i].setVisible(true);	
				center.add(label[i]);
			}else {
				//label[i].setVisible(false);
				center.remove(label[i]);
			}
			center.revalidate();
			center.repaint();
		}
	}
}
