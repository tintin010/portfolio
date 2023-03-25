package week03.jykim.konkuk;

import java.util.Scanner;

public class JYkim_task2 {

	public static Scanner scanner= new Scanner(System.in);
	static final int ROW = 4;
	static final int COL = 3;

	public static void main(String[] args) {
		String[][] parkingSpace = new String[ROW][COL];
		
		boolean menu = true;
		while(menu) {
			
			System.out.println();
			printmap(parkingSpace);
			System.out.println("1) 주차하기 2) 출차하기 3) 종료");
			System.out.print("메뉴를 선택해주세요 : ");
			int sel =scanner.nextInt();
			switch(sel) {
			case 1:
				parkingSpace = Park(parkingSpace);
				break;
			case 2:
				parkingSpace = Exit_c(parkingSpace);
				break;
			case 3:
				menu = false;
				break;
				default: 
					System.out.println("메뉴 번호를 확인 후 다시 입력해 주세요.");
					break;
			}
		}
		System.out.println("프로그램이 종료되었습니다.");
	}

	private static String[][] Exit_c(String[][] out) {
		String num;
		System.out.print("차량 번호를 입력해주세요 : ");
		num = scanner.next();
		for(int i=0;i<ROW;i++) {
			for(int j=0;j<COL;j++){
				if(out[i][j] == null) continue;
				else {
					if(num.equals(out[i][j])) {
						System.out.println(num+" 차량이 출차되었습니다. 안녕히 가세요.");
						out[i][j] = null;
						return out;
					}else continue;
				}
			}
		}
		System.out.println("차량이 존재하지 않습니다. 차량번호 확인 후 처음부터 다시 진행해 주세요.");
		return out;
	}

	private static String[][] Park(String[][] in) {
		String spot;	//차량 위치
		String car_num;	//차량 번호
		char c;			// 주차 여부 확인
		System.out.print("주차할 위치를 선택해 주세요(입력 예 : 2 1) : ");
		scanner.nextLine();
		spot = scanner.nextLine();
		//System.out.println(spot.charAt(0));
		//System.out.println(spot.charAt(1));
		boolean ok = isok(spot);
		if(!ok) {
			System.out.println("위치 번호를 확인해주세요. 처음부터 다시 진행해주세요.");
			return in;
		}
		boolean ok2 = isok2(spot, in);
		if(!ok2) {
			System.out.println("다른 차량이 주차되어 있습니다. 처음부터 다시 시작해 주세요.");
			return in;
		}
		System.out.print("차량 번호를 입력해 주세요(입력 예 : 26거5945) : ");
		car_num = scanner.nextLine();
		System.out.print("차량 번호 " + car_num + " 맞습니까(y/n)?");
		c = scanner.nextLine().charAt(0);
		if(c=='n') {
			System.out.println("처음부터 다시 시작해주세요.");
			return in;
		}
		String arr[] =spot.split(" ");
		int row = Integer.parseInt(arr[0]);
		int col = Integer.parseInt(arr[1]);
		in[row-1][col-1] = car_num;
		System.out.println(car_num+"차량의 주차를 완료하였습니다.");
		return in;
	}

	public static boolean isok2(String spot, String[][] in) {
		String arr[] =spot.split(" ");
		int r = Integer.parseInt(arr[0]);
		int c = Integer.parseInt(arr[1]);
		if(in[r-1][c-1] == null) return true;
		else return false;
	}

	public static boolean isok(String spot) {
		String arr[] =spot.split(" ");
		int r = Integer.parseInt(arr[0]);
		int c = Integer.parseInt(arr[1]);
		if(r>=1 && r<=ROW) {
			if(c>=1 && c<=COL) {
				return true;
			}else return false;
		}else return false;
	}

	public static void printmap(String[][] p_space) {
		System.out.println("주차관리 (202011272 김재윤)");
		System.out.println();
		System.out.println("  1 2 3");
		for(int i=0;i<ROW;i++) {
			System.out.print((i+1) + " ");
			for(int j=0;j<COL;j++) {
				if(p_space[i][j] == null) {
					System.out.print("♡ ");
				}else {
					System.out.print("♥ ");
				}
			}
			System.out.println();
		}
		
	}

}
