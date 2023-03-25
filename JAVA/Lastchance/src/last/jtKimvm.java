package last;

import java.util.ArrayList;
import java.util.Collections;


public class jtKimvm {

	int number=0;
	int ing=0;
	int totcost=0;
	int count=0;
	
	private ArrayList<jyKimitem> voc = new ArrayList<>();
	
	public jtKimvm(int number) {
		super();
		this.number = number;
	}
	
	public void additem(jyKimitem k) {
		
		if(this.count < this.number) {
			for(jyKimitem v : voc) {
				if(k.equals(v)) {
					v.setNum(1);
					System.out.println(k.getKind() + "재고추가");
					return;
				}
			}
			count++;
			voc.add(k);
			String str = k.getKind();
			System.out.println(str + "제춤추가");
		}else {
			System.out.println("추가안됨");
		}
		
	}
	
	public void sellitem(jyKimitem k) {
		for(jyKimitem v : voc) {
			if(k.equals(v)) {
				v.setNum(-1);
				v.setPrice();
				System.out.println(k.getKind() + "판매");
				return;
			}else {
				System.out.println("제품이 존재하지 않습니다.");
			}
		}
	}
	
	public void showsales() {
		ArrayList<jyKimitem> voc2 = new ArrayList<>();
		for(jyKimitem k : voc) {
			voc2.add(k);
		}
		Collections.sort(voc2);
		for(jyKimitem k : voc2) {
			System.out.println(k.toString());
		}
	}

	@Override
	public String toString() {
		String str = "====================";
		str += "\n판매가능 제품 수 : "+this.number;
		str += "\n 판매중 : "+this.ing;
		str += "\n 금액 : "+this.totcost;
		str += "\n =============================";
		int len = voc.size();
		for(int i=0; i<len ;i++) {
			str += "\n---------------------\n";
			str += voc.get(i).toString();
			str += "\n---------------------\n";
		}
		return str;
	}
}
