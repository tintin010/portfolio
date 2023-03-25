package last;

public abstract class jyKimitem implements Comparable<jyKimitem>{



	private String kind;
	private String place;
	private int price;
	private int num;
	private int tot;
	
	
	public jyKimitem(String kind, String place,int price, int num){
		super();
		this.kind = kind;
		this.place = place;
		this.num = num;
		this.price = price;
	}
	


	public jyKimitem(String kind, String place){
		super();
		this.kind = kind;
		this.place = place;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public int getNum() {
		return num;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice() {
		tot += price;
	}



	public void setNum(int i) {
		this.num = (this.num + i);
	}

	@Override
	public String toString() {
		String str = "제품이름 : " + this.kind;
		str += "\n회사 : " + this.place;
		str += "\n가격 : " + this.price;
		str += "\n수량 : " + this.num;
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.kind == ((jyKimitem)obj).kind) && (this.place == ((jyKimitem)obj).place);
	}
	
	@Override
	public int compareTo(jyKimitem o) {
		return (this.tot-o.tot) * -1;
	}

	
}
