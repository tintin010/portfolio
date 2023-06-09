package jykim0412.finaltest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VolunteerManager {
	String vName; //단체 이름		
	Set<Volunteer> volunteer=new HashSet<>() ; //봉사활동 저장 구조
	Map<String, Member> member = new HashMap<>(); //회원정보 저장 구조
	String mainkey = "a";
	
	public VolunteerManager(String vName) {
		super();
		this.vName = vName;	
		System.out.println(addMember("김재윤", 1, 21)); 
		System.out.println(addMember("송중기", 1, 19));
		System.out.println(addMember("송중기", 1, 19));
		System.out.println(addMember("정우성", 1, 17));
		System.out.println(addMember("김태희", 2, 25));
		System.out.println(addMember("아이유", 2, 16));
	}
	
	// 구현할 기능 : 회원정보 저장하기 ==> 반환값 : "회원가입 완료" 또는 "중복가입 불가"
	public String addMember(String name, int gender, int age) {
		Member m1 = new Member(name, gender, age);
		Iterator<String> keys = member.keySet().iterator();
		while(keys.hasNext()){
		    String key = keys.next();
		    Member m2 = member.get(key);
		    if(m1.getName().equals(m2.getName())) {
		    	String str = "중복가입 불가";
		    	return str;
		    }
		}
		member.put(name, m1);	// key 값을 어떻게 해주어야 할까 ?
		//mainkey +="a";
		String str = "회원가입 완료";
		return str;
	}
	
	
	public Member getMember(String name) {		
		return member.get(name);
	}
	
	// 구현할 기능 : 봉사활동 저장하기 ==> 반환값 : "등록완료" 또는 "중복 등록 불가"
	public String addVolunteer(Volunteer v) {
		String s = v.volName;
			for(Volunteer vol : volunteer) {
				String s1 = vol.volName;
				if(s.equals(s1)) {
					String str = "중복 등록 불가";
					return str;
				}
			}
			volunteer.add(v);
			String str = "등록완료";
			return str;
	}
	
	// 봉사활동 검색해서 반환하기
	public Volunteer findVolunteer(String vName) {
		for(Volunteer vlr : volunteer) {
			if(vlr==null) break;
			if(vName.equals(vlr.volName)) {				
				return vlr;
			}
		}
		System.out.println("해당하는 봉사활동을 찾을 수 없습니다.");
		return null;
	}		
	
	@Override
	public String toString() {
		
		String str = "봉사단체명 : " + this.vName +"\n";
		str += "등록된 봉사활동 : " + this.volunteer.size() +"\n";
		str += "------ 봉사활동 현황 리스트 -------\n";
		if(this.volunteer.size() ==0) {
			str+="등록된 봉사활동이 없습니다.\n";
			return str;
		}			
			
		for(Volunteer vlr : volunteer) {			
			if(vlr!=null) {				
				str += vlr.toString()+"\n";
			}else
				break;
		}
		
		return str;
	}
	
}
