package jykim0412.finaltest;


public class Member implements Comparable<Member>{
	String name; // 회원이름
	int gender; // 성별 --> 1(남), 2(여)
	int age; // 나이 
	int totalDonationTime=0; //자원봉사 참여 시간
	
	public Member(String name, int gender, int age) {
		super();
		this.name = name;		
		this.gender = gender;
		this.age = age;
	}
	
	public void addDonation(int donationTime) {
		this.totalDonationTime += donationTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "회원 이름 : "+this.name+"\n";
		str += "성별 : " + ((this.gender==1)?"남":"여") +"\n";
		str += "나이 : " + this.age +"\n";
		str += "자원봉사 참여시간 : " + this.totalDonationTime +"시간\n";		
		return str;
	}
//	@Override
//	public boolean equals(Object obj) {
//		return (this.name == ((Member)obj).name);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Member o) {
		return (this.totalDonationTime-o.totalDonationTime) * -1;
	}

}
