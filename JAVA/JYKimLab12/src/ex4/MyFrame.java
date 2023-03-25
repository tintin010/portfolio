package ex4;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	JSlider fontsize;
	JLabel label;
	
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
		label = new JLabel("greenjoa", SwingConstants.CENTER);
		this.add(label, BorderLayout.CENTER);
		
		fontsize = new JSlider(JSlider.HORIZONTAL, 1, 50, 10);
		fontsize.setToolTipText("폰트 크기를 설정합니다.");
		fontsize.setPaintLabels(true);
		fontsize.setPaintTicks(true);
		fontsize.setMajorTickSpacing(10);
		fontsize.setMinorTickSpacing(2);
		fontsize.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Font font = new Font("굴림체", Font.BOLD, fontsize.getValue());
				label.setFont(font);
			}
			
		});
		this.add(fontsize, BorderLayout.NORTH);
	}

}
