package week05.jykim.konkuk;

import java.util.Scanner;

public class salesman {
	
	private String name;
	private double sales;
	
	public static Scanner scan = new Scanner(System.in);
	
	public salesman() {
		
	}

	public void readInput() {
		System.out.print("이름을 입력하세요 : ");
		name = scan.next();
		System.out.print("판매금액을 입력하세요 : ");
		sales = scan.nextDouble();
	}
	
	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		String str = "이름 : " + this.name;
		str +=" / 실적 : " + this.sales;
		str += "\n";
		return str;
	}
	


}
