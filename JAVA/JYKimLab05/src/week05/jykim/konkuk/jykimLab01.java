package week05.jykim.konkuk;

import java.util.Scanner;

public class jykimLab01 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("202011272 ������");
		System.out.println("***** �� ���� *****");
		System.out.print("���� �� : ");
		int num = scan.nextInt();
		SalesReport team1 = new SalesReport(num);
		
		team1.computeStats();
		
		System.out.println();
		System.out.println("***** �ְ��� ���� *****");
		System.out.println(team1.getBestClerk());
		
		System.out.println();
		System.out.println("***** �� ���� *****");
		System.out.println(team1);
		
		
	}
}
