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
		String str = "ȸ���̸� : " + this.name;
		str += "\n �ڿ����� ���� Ƚ�� : " + this.cnt + "ȸ";
		str += "\n ��� �Ѿ� : " + this.total + "��";
		return str;
	}
}
