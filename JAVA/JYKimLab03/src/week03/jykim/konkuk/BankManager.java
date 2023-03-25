package week03.jykim.konkuk;

import java.util.Scanner;

public class BankManager {

	public String branchName;
	
	public final int NUM;
	public BankAccount[] bankAccount;
	
	public int number = 0;
	
	public static Scanner scan = new Scanner(System.in);
	
	public BankManager(String branchName, int NUM) {
		this.NUM = NUM;
		this.bankAccount = new BankAccount[this.NUM];
		this.branchName = branchName;
	}
	
	public void createAccount() {
		System.out.println("------ 계좌 개설 -------");
		if(number < NUM) {
			System.out.print("이름 : ");
			String name = scan.next();
			System.out.print("입금할 금액 : ");
			double amount = scan.nextDouble();
			bankAccount[number++] = new BankAccount(name,amount);
			System.out.println("게좌 개설 완료");
		}else
			System.out.println("게좌 개설 불가");
	}
	
	public void deposit() {
		System.out.println("------ 입금 -------");
		System.out.print("계좌번호 : ");
		int acc = scan.nextInt();
		BankAccount target = findAccount(acc);
		if(target!=null) {
			System.out.println("입금할 금액 : ");
			double amount = scan.nextDouble();
			target.deposit(amount);
		}else
			System.out.println("계좌 번호 확인 요함!");
	}
	
	public void withdraw() {
		System.out.println("------ 출금 -------");
		System.out.print("계좌번호 : ");
		int acc = scan.nextInt();
		BankAccount target = findAccount(acc);
		if(target!=null) {
			System.out.println("출금할 금액 : ");
			double amount = scan.nextDouble();
			target.withdraw(amount);
		}else
			System.out.println("계좌 번호 확인 요함!");
	}



	public void transfar() {
		System.out.println("------ 계좌 검색 -------");
		System.out.print("송금하는 계좌번호 : ");
		int acc1 = scan.nextInt();
		System.out.print("송금받는 계좌번호 : ");
		int acc2 = scan.nextInt();
		BankAccount target1 = findAccount(acc1);
		BankAccount target2 = findAccount(acc2);
		if(target1 != null && target2 != null) {
			System.out.println("이체할 금액 : ");
			double amount = scan.nextDouble();
			if(target1.accountBalance >= amount) {
				target1.withdraw(amount);
				target2.deposit(amount);
			}else {
				System.out.println("계좌 번호 확인 요함!");
			}
			
		}else
			System.out.println("계좌 번호 확인 요함!");
			
	}

	public BankAccount findAccount(int target) {
		System.out.println("------ 계좌 검색 ------");
		if(number != 0) {
			for(int i=0; i<this.bankAccount.length; i++) {
				if(bankAccount[i].accountNumber == target) {
					return bankAccount[i];
				}
			}
			return null;
		}else
			return null;
	}
	
	@Override
	public String toString() {
		
		String str = "지점명 : "+this.branchName+"\n";
		str +="-------------\n";
		for(BankAccount acc : this.bankAccount) {
			if(acc!=null) {
				str += acc.toString();
				str +="\n";
			}
		}
		str+="\n";
		return str;
	}
	
	

}
