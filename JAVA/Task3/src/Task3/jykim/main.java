package Task3.jykim;

public class main {

	public static void main(String[] args) {

		System.out.println("202011272 ������");
		System.out.println("2)�� �� ����");
		Car c1  = new Car("������", "5945");
		System.out.println(c1);
		System.out.println("3)�� ����ο��� ����");
		Car c2 = new CarForDisabledPerson("��浿", "1234","0001","����");			
		System.out.println(c2);
		Car c22 = new CarForDisabledPerson("�ڱ浿", "12341234","0002","����");
		System.out.println(c22);
		System.out.println("4)�� ������ ����");
		Car c3 = new SmallCar("�ڱ浿", "5678",555);
		System.out.println(c3);
		System.out.println();
		
		System.out.println("5��) ������ ����");			
		ParkingLot parking1 = new ParkingLot("parkingmap.txt", 1000);
		
		System.out.println();
		
		System.out.println("6��) �����ϱ�");			
		parking1.Parking(c1);
		parking1.Parking(c2);
		parking1.Parking(c3);
		parking1.Parking(c22);		
		
		System.out.println();
		
		System.out.println("7��) �����ϱ�");			
		parking1.PullOut();
		parking1.PullOut();
		parking1.PullOut();
		System.out.println();
		parking1.Parking(c2);
		
		System.out.println();
				
		System.out.println("8��) ������ ���� ���");				
		System.out.println(parking1);
	}

}
