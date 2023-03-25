package last;

public class jyKimCoffee extends jyKimitem {
	
	private String origin;

	public jyKimCoffee(String kind, String place, int price, int num, String origin) {
		super(kind, place, price, num);
		this.origin = origin;
	}
	
	public jyKimCoffee(String kind, String place, String origin) {
		super(kind, place);
		this.origin = origin;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += "\n원산지 : "+this.origin;
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj)&&this.origin ==((jyKimCoffee)obj).origin;
	}


}
