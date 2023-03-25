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
			System.out.println(this.getHaName() + ", �������� : "+ this.isHaPower() + ", ���Ϸ��µ� : " + this.temperature + "��");
		else
			System.out.println(this.getHaName() + "���� ��������");
	}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		System.out.println("���Ϸ��� �����մϴ�.");
		System.out.println("1) ����, 2) ���Ϸ� �µ� \n ���ϴ� �޴��� �����ϼ���.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("���Ϸ� �µ� �Է� : ");
				setTemperature(scan.nextInt());
			}else
				System.out.println("���� ����");
			break;
			default : 
				System.out.println("�Է¸޴��� Ȯ���ϼ���");
		}
		System.out.println("���Ϸ� ��������");
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
