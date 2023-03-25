package jykim.midtest;

import java.util.Scanner;

public class JYkimDonation extends JYkimVolunteer {
	static Scanner scanner = new Scanner(System.in);

	private String volname;
	private int memnum=0;
	private int curmem=0;
	private int tot=0;
	


	public JYkimDonation(String volname, int memnum) {
		super(volname, memnum);
		this.volname = volname;
		this.memnum = memnum;
	}
	
	public void join(JYkimMember m) {
		curmem++;
		m.setCnt(curmem);
		String name = m.getName();
		System.out.print(name + "�� ����� �ݾ��� �Է��� �ּ��� : ");
		int cost = scanner.nextInt();
		if(cost<100) {
			System.out.println(name + "��, �ּ� ��α��� 100�� �̻��Դϴ�. ");
		}else {
			System.out.println(name + "�� ��ΰ� �Ϸ�Ǿ����ϴ�.");
		}
		tot +=cost;
		
		
	}
	
	public String getVolname() {
		return volname;
	}

	public void setVolname(String volname) {
		this.volname = volname;
	}

	public int getMemnum() {
		return memnum;
	}

	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}

	public int getCurmem() {
		return curmem;
	}

	public void setCurmem(int curmem) {
		this.curmem = curmem;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	@Override
	public String toString() {

		String str = "�ڿ������ : "+this.volname;
		str += "\n �������� �ο� : " + this.memnum+"��";
		str += "\n ���� �����ο� : " + this.curmem+"��";
		str += "\n ��α� �Ѿ� : " + this.tot+"��";
		str += "\n ������ ��� (��αݾ��� ū ����)";
		str += "\n --------------------------";
		if(this.curmem == 0) {
			str += "\n ��û�ڰ� �����ϴ�.";//�̵��� �ٲٱ�
		}	
		else {
			str += "\n ��û�ڰ� �����ϴ�.";//�̵��� �ٲٱ�
		}
		return str;
	}
	
}
