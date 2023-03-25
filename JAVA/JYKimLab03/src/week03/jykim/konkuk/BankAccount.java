package week03.jykim.konkuk;

public class BankAccount {
	public static int count = 0;
	public int accountNumber;
	public String customerName;
	public double accountBalance;
	
	{
		this.accountNumber = ++count;
	}
	
	public BankAccount(String customerName) {	
		this(customerName, 0.0);
	}

	public BankAccount(String customerName, double accountBalance) {
		this.customerName = customerName;
		this.accountBalance = accountBalance;
	}
	
	public void deposit(double amount) {
		this.accountBalance +=amount;
	}
	
	public void withdraw(double amount) {
		if(this.accountBalance>=amount) {
			this.accountBalance -= amount;
		}else {
			System.out.println("출금 잔액 부족");
		}
	}
	
	public void transfar(BankAccount account, double amount) {
		if(this.accountBalance>=amount) {
			this.withdraw(amount);
			account.deposit(amount);
		}else {
			System.out.println("출금 잔액 부족");
		}
	}

	@Override
	public String toString() {
		String str = "고객이름 : " + this.customerName;
		str +="\n계좌번호 : " + this.accountNumber;
		str +="\n잔   액 : " + this.accountBalance;
		return str;
	}
	
	

	

}
