package week6.jykim.konkuk;


public class jykim06 {
	
	public static void main(String[] args) {
		System.out.println("202011272 ������");
		Home h = new Home(10);
		h.buyHA(new Vacuum("û�ұ�1", 1));
		h.buyHA(new Vacuum("û�ұ�2", 1));
		h.buyHA(new Vacuum("û�ұ�3", 1));
		IOTInterface iot = h.connect();
		if(iot != null) {
			iot.turnOn();
			iot.control();
			iot.turnOff();
		}else {
			System.out.println("connect ����");
		}
	}

}
