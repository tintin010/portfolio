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
		System.out.println("------ ���� ���� -------");
		if(number < NUM) {
			System.out.print("�̸� : ");
			String name = scan.next();
			System.out.print("�Ա��� �ݾ� : ");
			double amount = scan.nextDouble();
			bankAccount[number++] = new BankAccount(name,amount);
			System.out.println("���� ���� �Ϸ�");
		}else
			System.out.println("���� ���� �Ұ�");
	}
	
	public void deposit() {
		System.out.println("------ �Ա� -------");
		System.out.print("���¹�ȣ : ");
		int acc = scan.nextInt();
		BankAccount target = findAccount(acc);
		if(target!=null) {
			System.out.println("�Ա��� �ݾ� : ");
			double amount = scan.nextDouble();
			target.deposit(amount);
		}else
			System.out.println("���� ��ȣ Ȯ�� ����!");
	}
	
	public void withdraw() {
		System.out.println("------ ��� -------");
		System.out.print("���¹�ȣ : ");
		int acc = scan.nextInt();
		BankAccount target = findAccount(acc);
		if(target!=null) {
			System.out.println("����� �ݾ� : ");
			double amount = scan.nextDouble();
			target.withdraw(amount);
		}else
			System.out.println("���� ��ȣ Ȯ�� ����!");
	}



	public void transfar() {
		System.out.println("------ ���� �˻� -------");
		System.out.print("�۱��ϴ� ���¹�ȣ : ");
		int acc1 = scan.nextInt();
		System.out.print("�۱ݹ޴� ���¹�ȣ : ");
		int acc2 = scan.nextInt();
		BankAccount target1 = findAccount(acc1);
		BankAccount target2 = findAccount(acc2);
		if(target1 != null && target2 != null) {
			System.out.println("��ü�� �ݾ� : ");
			double amount = scan.nextDouble();
			if(target1.accountBalance >= amount) {
				target1.withdraw(amount);
				target2.deposit(amount);
			}else {
				System.out.println("���� ��ȣ Ȯ�� ����!");
			}
			
		}else
			System.out.println("���� ��ȣ Ȯ�� ����!");
			
	}

	public BankAccount findAccount(int target) {
		System.out.println("------ ���� �˻� ------");
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
		
		String str = "������ : "+this.branchName+"\n";
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
