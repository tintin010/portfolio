package week03.jykim.konkuk;

public class jykinLab03 {

	public static void main(String[] args) {
		System.out.println("202011272 김재윤");

		BankManager b1 = new BankManager("건국은행",10);
		BankManager b2 = new BankManager("대학은행",20);
		System.out.println("======== 건국은행 ========");
		b1.createAccount();
		b1.deposit();
		b1.withdraw();
		System.out.println(b1);

	}
	public static void lab5() {
		
	}
}
