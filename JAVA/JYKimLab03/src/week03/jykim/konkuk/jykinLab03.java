package week03.jykim.konkuk;

public class jykinLab03 {

	public static void main(String[] args) {
		System.out.println("202011272 ������");

		BankManager b1 = new BankManager("�Ǳ�����",10);
		BankManager b2 = new BankManager("��������",20);
		System.out.println("======== �Ǳ����� ========");
		b1.createAccount();
		b1.deposit();
		b1.withdraw();
		System.out.println(b1);

	}
	public static void lab5() {
		
	}
}
