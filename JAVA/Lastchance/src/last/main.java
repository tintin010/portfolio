package last;

public class main {
	public static void main(String[] args) {

		jyKimitem i1 = new jyKimCoffee("A", "a", 10, 1, "코스타");
		jyKimitem i2 = new jykimsnack("새우깡", "a", 1000, 1, 500);
		System.out.println(i1);
		jtKimvm vm1 = new jtKimvm(4);
		vm1.additem(i1);
		vm1.additem(i2);
		System.out.println(vm1);
		vm1.additem(new jyKimCoffee("A", "a", 100, 1, "코스타"));
		vm1.additem(i1);
		vm1.additem(new jykimsnack("새우깡", "a", 1000, 1, 500));
		vm1.additem(new jykimsnack("새우깡", "a", 10000, 1, 500));
		vm1.additem(new jykimsnack("새우깡", "a", 100000, 1, 500));
		System.out.println(vm1);
		vm1.showsales();
		//jyKimFrame f = new jyKimFrame("202011272 김재윤 기말고사");
	}
}
