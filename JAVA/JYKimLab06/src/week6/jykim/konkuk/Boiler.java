package week6.jykim.konkuk;

import java.util.Scanner;

public class Boiler extends HomeAppliance implements IOTInterface{
	static Scanner scan = new Scanner(System.in);

	private int temperature = 2;

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public void showStatus() {
		// TODO Auto-generated method stub
		if(this.isHaPower())
			System.out.println(this.getHaName() + ", 전원상태 : "+ this.isHaPower() + ", 보일러온도 : " + this.temperature + "도");
		else
			System.out.println(this.getHaName() + "전원 꺼져있음");
	}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		System.out.println("보일러를 제어합니다.");
		System.out.println("1) 전원, 2) 보일러 온도 \n 원하는 메뉴를 선택하세요.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("보일러 온도 입력 : ");
				setTemperature(scan.nextInt());
			}else
				System.out.println("전원 꺼짐");
			break;
			default : 
				System.out.println("입력메뉴를 확인하세요");
		}
		System.out.println("보일러 제어종료");
		showStatus();
	}

	public Boiler(String haName, int temperature) {
		super(haName);
		this.temperature = temperature;
	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		this.setHaPower(true);
		
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		this.setHaPower(false);
		
	}

	@Override
	public void control() {
		// TODO Auto-generated method stub
		menu();
		
	}

}
