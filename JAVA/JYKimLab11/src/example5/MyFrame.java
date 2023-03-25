package example5;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame {
	
	public MyFrame() {
		this("200000 ȫ�浿");
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
			//JOptionPane.showMessageDialog(null, "ȯ���մϴ�.");
			String name = JOptionPane.showInputDialog("�г����� �Է��ϼ���");
			setTitle(name+"�� ȯ���մϴ�.");				
		}
		
	}
}
