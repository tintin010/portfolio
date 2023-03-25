package week05_2.jykim.konkuk;

public class TicketManager {
	String name;
	int number;
	Ticket[] tickets;
	int count = 0;
	
	public TicketManager(String name, int number) {
		super();
		this.name = name;
		this.number = number;
		tickets = new Ticket[this.number];
	}
	
	public void showGeneralTicket(boolean payByCredit) {
		for(Ticket ticket : tickets) {
			if(ticket != null & ticket instanceof GeneralTicket) {
				GeneralTicket t = (GeneralTicket)ticket;
				if(t.isPayByCredit()==payByCredit) {
					System.out.println(t);
				}
			}
		}
	}
	
	public void showAdvanceTicket(int day) { 
		for(Ticket ticket : tickets) {
			if(ticket != null & ticket instanceof AdvanceTicket) {
				AdvanceTicket a = (AdvanceTicket)ticket;
				if(a.getAdvanceDays() >= day) {
					System.out.println(a);
				}
			}
		}
	}
	
	public void register(Ticket ticket) {
		if(this.count < this.number) {
			this.tickets[this.count++] = ticket;
		}else {
			System.out.println("Ƽ���ǸſϷ�");
		}
	}
	
	public double getTotal() {
		double total = 0.0;
		for(Ticket ticket : tickets) {
			if(ticket != null) {
				total += ticket.getPrice();
			}else
				break;
		}
		return total;
	}

	@Override
	public String toString() {
		 String str = "������ : " +this.name+"\n";
		 str += "�¼��� : " + this.number + "\n";
		 str += "�� �Ǹ�Ƽ�� �� : " + this.count + "\n";
		 str +="--------------\n";
		 for(Ticket ticket : tickets) {
			 if(ticket != null)
				 str += ticket.toString() + "\n=============\n";
				 else
					 break;
		 }
		 str += "�� Ƽ�� �Ǹűݾ� : "+this.getTotal() + "\n";
		return str;
	}

}
