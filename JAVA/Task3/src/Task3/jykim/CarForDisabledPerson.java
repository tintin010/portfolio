package Task3.jykim;

public class CarForDisabledPerson extends Car {
	
	private String h_num;	//����� ��ȣ
	private String handi;	//��� ����


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
		str += " , ����ε�Ϲ�ȣ : " + this.h_num + ", ������� : " + this.handi;
		return str;
	}

}
