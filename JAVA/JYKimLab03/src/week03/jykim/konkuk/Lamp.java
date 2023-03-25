package week03.jykim.konkuk;

public class Lamp {
	boolean isOn = false;
	int illuminance = 2;
	public Lamp() {
		this(false , 2);
	}
	
	public void show() {
		if(!isOn) {
			System.out.println("전원이 꺼져있습니다.");
		}else {
			System.out.println("현재 밝기 : " + illuminance);
		}
	}
	public Lamp(boolean isOn, int illuminance) {
		this.isOn = isOn;
		this.illuminance = illuminance;
	}
	public void turnOnOff() {
		isOn = !isOn;
		if(isOn) {
			show();
		}else System.out.println("전원이 꺼져있습니다.");
	}

	public void changeLight() {
		if(isOn) {
			if(illuminance<3) ++illuminance;
			else illuminance = 1;
			show();
		}else System.out.println("전원이 꺼져있습니다.");
	}
}
