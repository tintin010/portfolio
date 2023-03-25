package week6.jykim.konkuk;

import java.util.Scanner;

public class Home {
	static Scanner scan = new Scanner(System.in);
	
	private HomeAppliance[] apps;
	private int capacity;
	private int count = 0;
	
	public Home(int capacity) {
		super();
		this.capacity = capacity;
		apps = new HomeAppliance[this.capacity];
	}
	
	public void buyHA(HomeAppliance ha) {
		if (this.count < this.capacity) {
			apps[count++] = ha;
		}else {
			System.out.println("��������");
		}
	}
	
	public void open() {
		while(true) {
				System.out.println("������ǰ�� �����ϼ���");
				for(int i=0; i<count;i++) {
					System.out.println((i+1)+")"+apps[i].getHaName());
				}
				System.out.println("��ǰ�� �������ּ��� : ");
				int index= scan.nextInt();
				if(index >= 1 && index <= count) {
					apps[index-1].menu();
					//apps[index-1].showStatus();
			}else {
				System.out.println("����");
				break;
			}
		}	
	}
	
	public void scanIOTDevice() {
		System.out.println("���� ���� ���� ��ǰ");
		for(int i= 0; i < count; i++) {
			if(apps[i] instanceof IOTInterface) {
				System.out.println((i+1)+")"+apps[i].getHaName());

			}
		}
	}
	
	public IOTInterface connect() {
		
		System.out.println("���� ���� ���� ��ǰ");
		
		for(int i= 0; i < count; i++) {
			
			if(apps[i] instanceof IOTInterface) {
				System.out.println((i+1)+")"+apps[i].getHaName());

			}
		}
		System.out.println("��ǰ�� �������ּ��� : ");
		int index= scan.nextInt();
		if(index >= 1 && index <= count) {
		}else {
			System.out.println("����");
			return null;
		}
		return (IOTInterface) apps[index-1];
	}

}
