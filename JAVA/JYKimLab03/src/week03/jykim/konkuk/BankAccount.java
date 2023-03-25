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
			System.out.println("��� �ܾ� ����");
		}
	}
	
	public void transfar(BankAccount account, double amount) {
		if(this.accountBalance>=amount) {
			this.withdraw(amount);
			account.deposit(amount);
		}else {
			System.out.println("��� �ܾ� ����");
		}
	}

	@Override
	public String toString() {
		String str = "���̸� : " + this.customerName;
		str +="\n���¹�ȣ : " + this.accountNumber;
		str +="\n��   �� : " + this.accountBalance;
		return str;
	}
	
	

	

}
