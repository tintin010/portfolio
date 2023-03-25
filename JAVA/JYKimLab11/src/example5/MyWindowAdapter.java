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
		//JOptionPane.showMessageDialog(null, "환영합니다.");
		String name = JOptionPane.showInputDialog("닉네임을 입력하세요");
		if(frame!=null)
			frame.setTitle(name+"님 환영합니다.");
			
	}
}
