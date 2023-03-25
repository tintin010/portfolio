package week6.jykim.konkuk;

import java.util.Scanner;

public class Refrigerator extends HomeAppliance {
	static Scanner scan = new Scanner(System.in);
	private int temperature = 2;

	public Refrigerator(String haName, int temperature) {
		super(haName);
		this.temperature = temperature;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public void showStatus() {
		if(this.isHaPower())
			System.out.println(this.getHaName() + ", �������� : "+ this.isHaPower() + ", ����µ� : " + this.temperature + "��");
		else
			System.out.println(this.getHaName() + "���� ��������");
		
	}

	@Override
	public void menu() {
		System.out.println("����� �����մϴ�.");
		System.out.println("1) ����, 2) ����µ� \n ���ϴ� �޴��� �����ϼ���.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("����µ� �Է� : ");
				setTemperature(scan.nextInt());
			}else
				System.out.println("���� ����");
			break;
			default : 
				System.out.println("�Է¸޴��� Ȯ���ϼ���");
		}
		System.out.println("����� ��������");
		showStatus();
	}
	

}
