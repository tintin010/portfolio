package last;

public class jykimsnack extends jyKimitem {
	


	int cal;

	public jykimsnack(String kind, String place, int price, int num, int kcal) {
		super(kind, place, price, num);
		this.cal = kcal;
		
	}

	public jykimsnack(String kind, String place, int kcal) {
		super(kind, place);
		this.cal = kcal;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += "\n칼로리 : "+this.cal;
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)&&this.cal ==((jykimsnack)obj).cal;
	}

}
