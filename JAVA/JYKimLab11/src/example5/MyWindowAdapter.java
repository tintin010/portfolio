package example5;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class MyWindowAdapter extends WindowAdapter {

	MyFrame frame;
	public MyWindowAdapter(MyFrame f) {
		this.frame = f;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "ȯ���մϴ�.");
		String name = JOptionPane.showInputDialog("�г����� �Է��ϼ���");
		if(frame!=null)
			frame.setTitle(name+"�� ȯ���մϴ�.");
			
	}
}
