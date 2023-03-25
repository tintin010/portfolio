package last;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

//import task5.MyID;


public class jyKimFrame extends JFrame {
	
	JPanel p1, p2, p3;
	JButton btn0, btn1;
	jyKimID id = null;
	String path;

	public jyKimFrame(String title) {
		super(title);
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		this.setVisible(true);
	}

	private void init() {
		
		p1 = new JPanel();
		btn0 = new JButton("VM 생성");
		btn1 = new JButton("만든이");
		p1.setLayout(new FlowLayout());
		p1.add(btn0);
		p1.add(btn1);
		btn1.addActionListener(e->{
			if(id==null) {
				id = new jyKimID(jyKimFrame.this, "만든이", false);
			}else {
				id.requestFocus();
			}
		});
		
		btn0.addActionListener(e->{
			JFileChooser cho = new JFileChooser();
			FileNameExtensionFilter fil = new FileNameExtensionFilter("텍스트", "txt");
			cho.setFileFilter(fil);
			int result = cho.showSaveDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				
					path = cho.getSelectedFile().getAbsolutePath();
					if(path != null) {
						
					}
//						this.setTitle(path);
//						makeVoc(path);
//						model.setNumRows(0);	// 테이블 초기화
//						
//							int length = voc.size();
//							//System.out.println(length);
//							for(int i = 0; i< length; i++) {
//								String str[] = {voc.get(i).eng, voc.get(i).kor};
//								model.addRow(str);
//							}
//						btn2.setEnabled(true);
//						btn3.setEnabled(true);
//						btn4.setEnabled(true);
//						btn5.setEnabled(true);
//					}
				
			}
		});
		this.add(p1, BorderLayout.NORTH);
		p2 = new JPanel();
		this.add(p2, BorderLayout.CENTER);
		p3 = new JPanel();
		this.add(p3, BorderLayout.SOUTH);
		//p2.setLayout(new GridLayout(3,2));
	}
}
