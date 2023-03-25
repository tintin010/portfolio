package week03.jykim.konkuk;

public class TV {
	public boolean power = false;
	public int channel = 10;
	public int volume = 5;
	
	public void show() {
		if(power) {
			System.out.println("���� "+channel+"�� ��û��");
			System.out.println("�������� : "+volume);
		}else {
			System.out.println("TV���� ��������.");
		}
	}
	
	public void powerOnOff() {
		power = !power;
		show();
	}
	public void channelUp() {
		if(power)
			++channel;
		show();
	}
	public void channelDown() {
		if(power)
			--channel;
		show();
	}
	public void volumeUp() {
		if(power)
			if(volume<20) ++volume;
		show();
	}
	public void volumeDown() {
		if(power)
			if(volume>0) --volume;
		show();
	}
	
	public void changeChannel(int ch) {
		channel = ch;
		show();
	}
	
	public void changeVolume(int vol) {
		if(vol >=0 && vol <= 20)
			volume = vol;
		show();
	}
}
