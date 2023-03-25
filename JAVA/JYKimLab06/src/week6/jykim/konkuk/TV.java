package week6.jykim.konkuk;

import java.util.Scanner;

public class TV extends HomeAppliance {
	static Scanner scan = new Scanner(System.in);
	
	private int channel = 10;

	public TV(String haName, int channel) {
		super(haName);
		this.channel = channel;
	}
	
	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	@Override
	public void showStatus() {
		if(this.isHaPower())
			System.out.println(this.getHaName() + ", 전원상태 : "+ this.isHaPower() + ", 채널 : " + this.channel + "시청중");
		else
			System.out.println(this.getHaName() + "전원 꺼져있음");

	}

	@Override
	public void menu() {
		System.out.println("TV를 제어합니다.");
		System.out.println("1) 전원, 2) 채널 \n 원하는 메뉴를 선택하세요.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("채널 입력 : ");
				setChannel(scan.nextInt());
			}else
				System.out.println("전원 꺼짐");
			break;
			default : 
				System.out.println("입력메뉴를 확인하세요");
		}
		System.out.println("TV 제어종료");
		showStatus();
	}


}
