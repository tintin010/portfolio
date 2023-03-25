package week05.jykim.konkuk;

import java.util.Scanner;

public class SalesReport {
	
	public static Scanner scan = new Scanner(System.in);


	private salesman[] team;
	private double highestSales;
	private double averageSales;
	private int number;
	
	public SalesReport(int number) {
		this.number = number;
		this.team = new salesman[this.number];
		for(int i = 0;i<number; i++) {
			System.out.println(i+1 + "번째 팀원 정보 입력");
			team[i] = new salesman();
			team[i].readInput();
		}
	}
	
	public void computeStats() {
		double ss = team[0].getSales();
		for(int i=1; i<number; i++) {
			if(ss <= team[i].getSales())
				ss = team[i].getSales();
		}
		highestSales = ss;
		double sum = 0;
		for(int i=0; i<number ;i++) {
			sum += team[i].getSales();
		}
		double avr = sum/number;
		averageSales = avr;
	}
	
	public salesman getBestClerk() {
		for(int i=0; i<number ;i++) {
			if(highestSales == team[i].getSales()) {
				return team[i];
			}
		}
		return null;
		
	}

	@Override
	public String toString() {
		String str = "총 팀원 수 : "+this.number;
		str += "\n최고 판매액 : " + this.highestSales;
		str += "\n평균 판매액 : " + this.averageSales;
		str += "\n------------------------------------\n";
		for(salesman man : this.team) {
			if(man!=null) {
				str += man.toString();
				str +="\n";
			}
		}
		str+="\n";
		return str;
	}
	
}
