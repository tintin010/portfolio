package week05_2.jykim.konkuk;

public class jykim02 {

	public static void main(String[] args) {
		System.out.println("202011272 ±Ë¿Á¿±");
		TicketManager ticket1 = new TicketManager("æ∆¿Ã¿Ø ƒ‹º≠∆Æ", 100);
		ticket1.register(new Ticket(1,2000.0));
		ticket1.register(new AdvanceTicket(4,2000.0, 50));
		ticket1.register(new AdvanceTicket(5,1100.0, 15));
		ticket1.register(new AdvanceTicket(6,5500.0, 20));
		ticket1.register(new AdvanceTicket(7,8000.0, 37));
		ticket1.showAdvanceTicket(20);

	}

}
