package Task3.jykim;

public class main {

	public static void main(String[] args) {

		System.out.println("202011272 김재윤");
		System.out.println("2)번 차 생성");
		Car c1  = new Car("김재윤", "5945");
		System.out.println(c1);
		System.out.println("3)번 장애인용차 생성");
		Car c2 = new CarForDisabledPerson("김길동", "1234","0001","중증");			
		System.out.println(c2);
		Car c22 = new CarForDisabledPerson("박길동", "12341234","0002","경증");
		System.out.println(c22);
		System.out.println("4)번 소형차 생성");
		Car c3 = new SmallCar("박길동", "5678",555);
		System.out.println(c3);
		System.out.println();
		
		System.out.println("5번) 주차장 생성");			
		ParkingLot parking1 = new ParkingLot("parkingmap.txt", 1000);
		
		System.out.println();
		
		System.out.println("6번) 주차하기");			
		parking1.Parking(c1);
		parking1.Parking(c2);
		parking1.Parking(c3);
		parking1.Parking(c22);		
		
		System.out.println();
		
		System.out.println("7번) 출차하기");			
		parking1.PullOut();
		parking1.PullOut();
		parking1.PullOut();
		System.out.println();
		parking1.Parking(c2);
		
		System.out.println();
				
		System.out.println("8번) 주차장 수입 출력");				
		System.out.println(parking1);
	}

}
