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

		String str = "자원봉사명 : "+this.volname;
		str += "\n 참여가능 인원 : " + this.memnum+"명";
		str += "\n 현재 참여인원 : " + this.curmem+"명";
		str += "\n 봉사할 재능 : " + this.talname;
		str += "\n 참여자 명단 (기부금액이 큰 순서)";
		str += "\n --------------------------";
		if(this.curmem == 0) {
			str += "\n 신청자가 없습니다.";//이따가 바꾸기
		}	
		else {
			str += "\n 신청자가 없습니다.";//이따가 바꾸기
		}
		return str;
	}

}
