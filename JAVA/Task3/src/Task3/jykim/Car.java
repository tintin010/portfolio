package Task3.jykim;

public class Car {
	protected String name;
	protected String car_num;
	
	public Car(String name, String car_num) {
		this.name = name;	//����������
		this.car_num = car_num;	//������ȣ
	}

	@Override
	public String toString() {
		String str = "���������� : "+this.name + ", ������ȣ : "+ this.car_num;
		return str;
	}
	
}
