package Task3.jykim;

public class SmallCar extends Car {
	
	private int dpsm;	//��ⷮ

	public SmallCar(String name, String car_num, int dpsm) {
		super(name, car_num);
		this.dpsm = dpsm;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += " , ��ⷮ : "+this.dpsm + "cc";
		return str;
	}

}
