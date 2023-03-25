package week6.jykim.konkuk;

import java.util.Scanner;

public class Vacuum extends HomeAppliance implements IOTInterface{
	static Scanner scan = new Scanner(System.in);

	private int level = 1;

	public Vacuum(String haName, int level) {
		super(haName);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void showStatus() {
		if(this.isHaPower())
			System.out.println(this.getHaName() + ", �������� : "+ this.isHaPower() + ", û�ұ� ���� : " + this.level);
		else
			System.out.println(this.getHaName() + "���� ��������");
	}

	@Override
	public void menu() {
		System.out.println("û�ұ⸦ �����մϴ�.");
		System.out.println("1) ����, 2) û�ұ� ���� \n ���ϴ� �޴��� �����ϼ���.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("û�� ���� �Է�(1 ~ 3) : ");
				int level = scan.nextInt();
				if(level >=1 && level <=3) {
					this.setLevel(level);
				}else {
					this.setLevel(1);
				}
				System.out.println("û�� ���� " + this.level + "�� �������Դϴ�.");
			}else
				System.out.println("���� ����");
			break;
			default : 
				System.out.println("�Է¸޴��� Ȯ���ϼ���");
		}
		System.out.println("û�ұ� ��������");
		showStatus();
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
