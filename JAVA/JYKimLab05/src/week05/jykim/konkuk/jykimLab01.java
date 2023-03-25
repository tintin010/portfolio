package week05.jykim.konkuk;

import java.util.Scanner;

public class jykimLab01 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("202011272 ±èÀçÀ±");
		System.out.println("***** ÆÀ »ý¼º *****");
		System.out.print("ÆÀ¿ø ¼ö : ");
		int num = scan.nextInt();
		SalesReport team1 = new SalesReport(num);
		
		team1.computeStats();
		
		System.out.println();
		System.out.println("***** ÃÖ°í»ç¿ø Á¤º¸ *****");
		System.out.println(team1.getBestClerk());
		
		System.out.println();
		System.out.println("***** ÆÀ Á¤º¸ *****");
		System.out.println(team1);
		
		
	}
}
