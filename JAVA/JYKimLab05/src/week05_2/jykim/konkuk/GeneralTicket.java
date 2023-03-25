package week05_2.jykim.konkuk;

public class GeneralTicket extends Ticket {
	
	private boolean payByCredit;
	
	public GeneralTicket(int number, boolean payByCredit) {
		super(number);
		this.payByCredit = payByCredit;
	}

	@Override
	public double getPrice() {
		if(this.isPayByCredit())
			return super.getPrice()*(1.1);
		return super.getPrice();
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += "\nī����� : " + this.isPayByCredit();
		str += "\n�����ݾ� : " + this.getPrice() + "\n";
		return str;
	}

	public boolean isPayByCredit() {
		return payByCredit;
	}

	public void setPayByCredit(boolean payByCredit) {
		this.payByCredit = payByCredit;
	}

	public GeneralTicket(int number, double price, boolean payByCredit) {
		super(number, price);
		this.payByCredit = payByCredit;
	}
}
