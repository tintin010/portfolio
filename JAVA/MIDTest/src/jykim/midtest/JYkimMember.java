package jykim.midtest;

public class JYkimMember {

	private String name;
	public String getName() {
		return name;
	}



	private int cnt = 0;
	private int total = 0;
	
	public JYkimMember(String name) {
		this.name = name;
	}
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt + 1;
	}

	public int getTotal() {
		return total;
	}
	
	@Override
	public String toString() {
		String str = "회원이름 : " + this.name;
		str += "\n 자원봉사 참여 횟수 : " + this.cnt + "회";
		str += "\n 기부 총액 : " + this.total + "원";
		return str;
	}
}
