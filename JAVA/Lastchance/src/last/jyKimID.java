package last;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class jyKimID extends JDialog {

	jyKimFrame parent;
	ImageIcon img;
	JPanel p1;
	JLabel l1, l2;

	public jyKimID(jyKimFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		parent = owner;
		this.setSize(500,500);				
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // dispose();
		init();	
		this.setVisible(true);
		init();
		
		
	}

	private void init() { 
		
		p1 = new JPanel();
		img = new ImageIcon("img/fimg.jpg");
		l1 = new JLabel(img);
		l2 = new JLabel("202011272 김재윤");
		p1.add(l1);
		p1.add(l2);
		this.add(p1, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				parent.id = null;
				dispose();
			}
			
		});
	}
}
