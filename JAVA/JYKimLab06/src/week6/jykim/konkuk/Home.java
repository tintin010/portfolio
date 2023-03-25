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
			System.out.println("공간없음");
		}
	}
	
	public void open() {
		while(true) {
				System.out.println("가전제품을 선택하세요");
				for(int i=0; i<count;i++) {
					System.out.println((i+1)+")"+apps[i].getHaName());
				}
				System.out.println("제품을 선택해주세요 : ");
				int index= scan.nextInt();
				if(index >= 1 && index <= count) {
					apps[index-1].menu();
					//apps[index-1].showStatus();
			}else {
				System.out.println("종료");
				break;
			}
		}	
	}
	
	public void scanIOTDevice() {
		System.out.println("원격 제어 가능 제품");
		for(int i= 0; i < count; i++) {
			if(apps[i] instanceof IOTInterface) {
				System.out.println((i+1)+")"+apps[i].getHaName());

			}
		}
	}
	
	public IOTInterface connect() {
		
		System.out.println("원격 제어 가능 제품");
		
		for(int i= 0; i < count; i++) {
			
			if(apps[i] instanceof IOTInterface) {
				System.out.println((i+1)+")"+apps[i].getHaName());

			}
		}
		System.out.println("제품을 선택해주세요 : ");
		int index= scan.nextInt();
		if(index >= 1 && index <= count) {
		}else {
			System.out.println("종료");
			return null;
		}
		return (IOTInterface) apps[index-1];
	}

}
