package jykim.midtest;

public class JykimVolunteerManager {

	private String volteamname;
	private int volnum=0;
	private JYkimVolunteer[] volunteer = new JYkimVolunteer[3];
	private int curnum=0;
	
	public JykimVolunteerManager(String volteamname) {
		super();
		this.volteamname = volteamname;
	}

	public void addVolunteer(JYkimVolunteer vol) {
		if(curnum ==0) {
			volunteer[curnum++] = vol;
			System.out.println("����� �� �Ǿ����ϴ�.");
		}else if(curnum>0 && curnum <3) {
			for(int i=0;i<curnum;i++) {
				if((vol.getVolname()).equals(volunteer[i].getVolname())) {
					System.out.println("���� ����Ȱ���� �ߺ������� �� �����ϴ�.");
					return;
				}
			}
			volunteer[curnum++] = vol;
			System.out.println("����� �� �Ǿ����ϴ�.");
		}else {
			System.out.println("�� �̻� �߰��� �� �����ϴ�.");
		}
	}
	
	public JYkimVolunteer findVolunteer (String volname) {
		for(int i=0; i<curnum;i++) {
			if(volname.equals(volunteer[i].getVolname())) {
				return volunteer[i];
			}
		}
		return null;
		
		
		
	}
	

	@Override
	public String toString() {
		String str = "�����ü�� : " + this.volteamname;
		str += "\n ��ϵ� ����Ȱ�� : "+ this.volnum;
		str += "\n ------ ����Ȱ�� ��Ȳ ����Ʈ ------";
		if(this.curnum == 0) {
			str += "\n ��ϵ� ����Ȱ���� �����ϴ�.";
		}else {
			str += "\n ��ϵ� ����Ȱ���� �����ϴ�???.";
		}
		return str;
	}

}
