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
			System.out.println("1) �����ϱ� 2) �����ϱ� 3) ����");
			System.out.print("�޴��� �������ּ��� : ");
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
					System.out.println("�޴� ��ȣ�� Ȯ�� �� �ٽ� �Է��� �ּ���.");
					break;
			}
		}
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

	private static String[][] Exit_c(String[][] out) {
		String num;
		System.out.print("���� ��ȣ�� �Է����ּ��� : ");
		num = scanner.next();
		for(int i=0;i<ROW;i++) {
			for(int j=0;j<COL;j++){
				if(out[i][j] == null) continue;
				else {
					if(num.equals(out[i][j])) {
						System.out.println(num+" ������ �����Ǿ����ϴ�. �ȳ��� ������.");
						out[i][j] = null;
						return out;
					}else continue;
				}
			}
		}
		System.out.println("������ �������� �ʽ��ϴ�. ������ȣ Ȯ�� �� ó������ �ٽ� ������ �ּ���.");
		return out;
	}

	private static String[][] Park(String[][] in) {
		String spot;	//���� ��ġ
		String car_num;	//���� ��ȣ
		char c;			// ���� ���� Ȯ��
		System.out.print("������ ��ġ�� ������ �ּ���(�Է� �� : 2 1) : ");
		scanner.nextLine();
		spot = scanner.nextLine();
		//System.out.println(spot.charAt(0));
		//System.out.println(spot.charAt(1));
		boolean ok = isok(spot);
		if(!ok) {
			System.out.println("��ġ ��ȣ�� Ȯ�����ּ���. ó������ �ٽ� �������ּ���.");
			return in;
		}
		boolean ok2 = isok2(spot, in);
		if(!ok2) {
			System.out.println("�ٸ� ������ �����Ǿ� �ֽ��ϴ�. ó������ �ٽ� ������ �ּ���.");
			return in;
		}
		System.out.print("���� ��ȣ�� �Է��� �ּ���(�Է� �� : 26��5945) : ");
		car_num = scanner.nextLine();
		System.out.print("���� ��ȣ " + car_num + " �½��ϱ�(y/n)?");
		c = scanner.nextLine().charAt(0);
		if(c=='n') {
			System.out.println("ó������ �ٽ� �������ּ���.");
			return in;
		}
		String arr[] =spot.split(" ");
		int row = Integer.parseInt(arr[0]);
		int col = Integer.parseInt(arr[1]);
		in[row-1][col-1] = car_num;
		System.out.println(car_num+"������ ������ �Ϸ��Ͽ����ϴ�.");
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
		System.out.println("�������� (202011272 ������)");
		System.out.println();
		System.out.println("  1 2 3");
		for(int i=0;i<ROW;i++) {
			System.out.print((i+1) + " ");
			for(int j=0;j<COL;j++) {
				if(p_space[i][j] == null) {
					System.out.print("�� ");
				}else {
					System.out.print("�� ");
				}
			}
			System.out.println();
		}
		
	}

}
