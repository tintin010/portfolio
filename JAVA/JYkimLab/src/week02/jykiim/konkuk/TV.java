package week02.jykiim.konkuk;

public class TV {
		public boolean power = false;
		public int channel = 10;
		public int volume = 5;
		
		public void show() {
			if(power) {
				System.out.println("현재 "+channel+"번 시청중");
			}else {
				System.out.println("TV전원 꺼져있음.");
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
	}


