package week05.jykim.konkuk;

import java.util.Scanner;

public class salesman {
	
	private String name;
	private double sales;
	
	public static Scanner scan = new Scanner(System.in);
	
	public salesman() {
		
	}

	public void readInput() {
		System.out.print("�̸��� �Է��ϼ��� : ");
		name = scan.next();
		System.out.print("�Ǹűݾ��� �Է��ϼ��� : ");
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
		String str = "�̸� : " + this.name;
		str +=" / ���� : " + this.sales;
		str += "\n";
		return str;
	}
	


}
