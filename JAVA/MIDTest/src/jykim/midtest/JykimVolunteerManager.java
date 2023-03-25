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
			System.out.println("등록이 잘 되었습니다.");
		}else if(curnum>0 && curnum <3) {
			for(int i=0;i<curnum;i++) {
				if((vol.getVolname()).equals(volunteer[i].getVolname())) {
					System.out.println("같은 봉사활동은 중복저장할 수 없습니다.");
					return;
				}
			}
			volunteer[curnum++] = vol;
			System.out.println("등록이 잘 되었습니다.");
		}else {
			System.out.println("더 이상 추가할 수 없습니다.");
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
		String str = "봉사단체명 : " + this.volteamname;
		str += "\n 등록된 봉사활동 : "+ this.volnum;
		str += "\n ------ 봉사활동 현황 리스트 ------";
		if(this.curnum == 0) {
			str += "\n 등록된 봉사활동이 없습니다.";
		}else {
			str += "\n 등록된 봉사활동이 없습니다???.";
		}
		return str;
	}

}
