package jykim.midtest;

import java.util.Scanner;

public class JYkimTalentDonation extends JYkimVolunteer {
	static Scanner scanner = new Scanner(System.in);
	
	private String volname;
	private String talname;
	private int memnum=0;
	private int curmem=0;
	
	public JYkimTalentDonation(String volname, int memnum, String talname) {
		super(volname, memnum);
		this.volname = volname;
		this.memnum = memnum;
		this.talname = talname;
	}

	public String getVolname() {
		return volname;
	}

	public void setVolname(String volname) {
		this.volname = volname;
	}

	public String getTalname() {
		return talname;
	}

	public void setTalname(String talname) {
		this.talname = talname;
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

	@Override
	public String toString() {

		String str = "�ڿ������ : "+this.volname;
		str += "\n �������� �ο� : " + this.memnum+"��";
		str += "\n ���� �����ο� : " + this.curmem+"��";
		str += "\n ������ ��� : " + this.talname;
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
