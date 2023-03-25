package example5;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame {
	
	public MyFrame() {
		this("200000 홍길동");
	}
	
	public MyFrame(String title) {
		super(title);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}
	
	public void init() {
		this.addWindowListener(new MyWindowAdapter2());
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
			
		});
	}
	
	class MyWindowAdapter2 extends WindowAdapter {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			//JOptionPane.showMessageDialog(null, "환영합니다.");
			String name = JOptionPane.showInputDialog("닉네임을 입력하세요");
			setTitle(name+"님 환영합니다.");				
		}
		
	}
}
