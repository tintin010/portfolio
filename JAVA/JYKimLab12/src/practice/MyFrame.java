package practice;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame implements ActionListener, ItemListener{
	
	JTextField text1;
	JLabel label;
	JPasswordField text3;
	JTextArea text4;
	JComboBox<String> combo1, combo2;
	DefaultComboBoxModel<String> c1model, c2model;
	String[] data = {"Bold", "Italic"};
	JCheckBox[] check = new JCheckBox[2];
	String str, str1, str3, str4, style;
	Object str2, size;
	int s;
	Font f = new Font("µ∏øÚ√º", Font.PLAIN, 12);
	 int valBold = Font.PLAIN;
	 int valItalic = Font.PLAIN;
	
	
	JPanel p1, p2;
	
	public MyFrame() {
		this("202011272 ±Ë¿Á¿±");
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
		
		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new FlowLayout());
		//p2.setLayout(null);
		initco1();
		initco2();
		initch1();
		inittx();
		str = "202011272 ±Ë¿Á¿±";
		label = new JLabel(str, SwingConstants.CENTER);
		label.setFont(f);
		
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
		//p2.add(label);
		this.add(label);
	}

	public void inittx() {
		
		text1 = new JTextField(15);
		text1.setFont(new Font("πŸ≈¡√º", Font.PLAIN, 20));
		text1.addActionListener(this);	
		p1.add(text1);
	}
	
	public void initch1() {
		for(int i=0; i<data.length; i++) {
			check[i] = new JCheckBox(data[i]);	
			check[i].setBorderPainted(true);
			check[i].addItemListener(this);
			p1.add(check[i]);
		}
	}

	public void initco2() {
		c2model = new DefaultComboBoxModel<>();
		c2model.addElement("10");
		c2model.addElement("12");
		c2model.addElement("14");
		c2model.addElement("16");
		c2model.addElement("18");
		c2model.addElement("20");
		c2model.addElement("22");
		c2model.addElement("24");
		c2model.addElement("26");
		
		combo2 = new JComboBox<>(c2model);
		combo2.setSelectedIndex(1);
		combo2.addActionListener(this);
		p1.add(combo2);
	}

	public void initco1() {
		c1model = new DefaultComboBoxModel<>();
		c1model.addElement("πŸ≈¡√º");
		c1model.addElement("µ∏øÚ√º");
		c1model.addElement("±º∏≤√º");
		c1model.addElement("±√º≠√º");
		
		combo1 = new JComboBox<>(c1model);
		combo1.setSelectedIndex(1);
		combo1.addActionListener(this);
		p1.add(combo1);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==text1) {
			str1 = text1.getText();
			label.setText(str1);
		} if(e.getSource() == combo1) {
			str2 = combo1.getSelectedItem();
			style = String.valueOf(str2);
			s = f.getSize();
			f = new Font(style, valBold + valItalic, s);
		} if(e.getSource() == combo2) {
			size = combo2.getSelectedItem();
			s =  Integer.parseInt((String) size);
			style = f.getFontName();
			f = new Font(style, valBold + valItalic, s);
		}
		label.setFont(f);
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == check[0]) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				valBold = Font.BOLD;
			}else valBold = Font.PLAIN;
		}else if(e.getSource() == check[1]) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				valItalic = Font.ITALIC;
			}else  valItalic = Font.PLAIN;
		}
		style = f.getFontName();
		s = f.getSize();
		f = new Font(style, valBold + valItalic, s);
		label.setFont(f);
	}
}
