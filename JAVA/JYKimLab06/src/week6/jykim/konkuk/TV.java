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
			System.out.println(this.getHaName() + ", �������� : "+ this.isHaPower() + ", ä�� : " + this.channel + "��û��");
		else
			System.out.println(this.getHaName() + "���� ��������");

	}

	@Override
	public void menu() {
		System.out.println("TV�� �����մϴ�.");
		System.out.println("1) ����, 2) ä�� \n ���ϴ� �޴��� �����ϼ���.");
		int choice = scan.nextInt();
		switch(choice) {
		case 1 : 
			this.setHaPower(!this.isHaPower());
			break;
		case 2 : 
			if(this.isHaPower()) {
				System.out.println("ä�� �Է� : ");
				setChannel(scan.nextInt());
			}else
				System.out.println("���� ����");
			break;
			default : 
				System.out.println("�Է¸޴��� Ȯ���ϼ���");
		}
		System.out.println("TV ��������");
		showStatus();
	}


}
