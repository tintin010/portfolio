package Task3.jykim;

public class CarForDisabledPerson extends Car {
	
	private String h_num;	//장애인 번호
	private String handi;	//장애 정도


	public String getHandi() {
		return handi;
	}


	public void setHandi(String handi) {
		this.handi = handi;
	}


	public CarForDisabledPerson(String name, String car_num, String h_num, String handi) {
		super(name, car_num);
		this.h_num = h_num;
		this.handi = handi;
	}


	@Override
	public String toString() {
		String str = super.toString();
		str += " , 장애인등록번호 : " + this.h_num + ", 장애정도 : " + this.handi;
		return str;
	}

}
