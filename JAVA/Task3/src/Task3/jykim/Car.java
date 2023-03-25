package Task3.jykim;

public class Car {
	protected String name;
	protected String car_num;
	
	public Car(String name, String car_num) {
		this.name = name;	//차량소유주
		this.car_num = car_num;	//차량번호
	}

	@Override
	public String toString() {
		String str = "차량소유주 : "+this.name + ", 차량번호 : "+ this.car_num;
		return str;
	}
	
}
