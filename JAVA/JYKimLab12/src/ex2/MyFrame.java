package ex2;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;;

public class MyFrame extends JFrame implements ActionListener{

	JTextField text1;
	JTextField text2;
	JPasswordField text3;
	JTextArea text4;
	
	
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
		this.setLayout(new FlowLayout());
		text1 = new JTextField(15);
		text1.setFont(new Font("굴림체", Font.BOLD, 20));		
		text1.addActionListener(this);
		
		text2 = new JTextField(15);
		text2.addActionListener(this);
		
		text3 = new JPasswordField(15);
		text3.setEchoChar('*');
		text3.addActionListener(this);
		
		text4 = new JTextArea(20,30);
								
		this.add(text1);
		this.add(text2);	
		this.add(text3);
		this.add(new JScrollPane(text4));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==text1 ||e.getSource()==text2 ) {
			String str1 = text1.getText();
			Font f1 = text1.getFont();
			String str2 = text2.getText();
			Font f2 = text2.getFont();
			text1.setText(str2);
			text1.setFont(f2);
			text2.setText(str1);
			text2.setFont(f1);
			if(text1.isFocusOwner()) {
				text2.requestFocus();
			}else {
				text1.requestFocus();
			}
			
			text4.append(str1+"\n");
			
		}else if(e.getSource()==text3) {
			String str1 = new String(text3.getPassword());
			if(str1.equals("greenjoa")) {
				JOptionPane.showMessageDialog(this, "일치합니다.");
			}else {
				JOptionPane.showMessageDialog(this, "일치하지 않습니다.");
			}
		}
	}
}
