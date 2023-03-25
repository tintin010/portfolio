package jykim.midtest;

public class JYkimTestMain {

	public static void main(String[] args) {

		System.out.println("202011272 김재윤");
		System.out.println();
		
		System.out.println("2) Member 객체 생성 및 출력하기");
		JYkimMember kim = new JYkimMember("김재윤");
		System.out.println(kim);
		JYkimMember h = new JYkimMember("홍길동");
		JYkimMember choi = new JYkimMember("최길동");
		JYkimMember park = new JYkimMember("박길동");
		JYkimMember lee = new JYkimMember("이길동");
		System.out.println();

		System.out.println("3) Volunteer 추상 클래스 만들기");
		System.out.println();

		System.out.println("4) Donation (물적기부) 객체 만들기");
		JYkimVolunteer v1 = new JYkimDonation("김길동 어린이 돕기",5);
		System.out.println(v1);
		System.out.println();

		System.out.println("5) TalentDonation (재능기부) 객체 만들기");
		JYkimVolunteer v2 = new JYkimTalentDonation("농촌 미용 봉사활동",2, "미용기술");
		System.out.println(v2);
		System.out.println();

		System.out.println("6) VolunteerManager 객체 만들기");
		JykimVolunteerManager vm = new JykimVolunteerManager("그린세상만들기");
		System.out.println(vm);
		System.out.println();

		System.out.println("7) 봉사활동 추가하기");
		vm.addVolunteer(new JYkimDonation("김길동 어린이 돕기",5));
		vm.addVolunteer(new JYkimDonation("김길동 어린이 돕기",2));
		vm.addVolunteer(new JYkimTalentDonation("독거노인 도배 봉사활동",5, "도배기술"));
		vm.addVolunteer(new JYkimTalentDonation("섬마을 어린이 봉사활동",5, "교육기술"));
		vm.addVolunteer(new JYkimDonation("엄길동 어린이 돕기",55));
		
	}

}
