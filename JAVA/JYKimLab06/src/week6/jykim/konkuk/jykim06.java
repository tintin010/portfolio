package week6.jykim.konkuk;


public class jykim06 {
	
	public static void main(String[] args) {
		System.out.println("202011272 김재윤");
		Home h = new Home(10);
		h.buyHA(new Vacuum("청소기1", 1));
		h.buyHA(new Vacuum("청소기2", 1));
		h.buyHA(new Vacuum("청소기3", 1));
		IOTInterface iot = h.connect();
		if(iot != null) {
			iot.turnOn();
			iot.control();
			iot.turnOff();
		}else {
			System.out.println("connect 실패");
		}
	}

}
