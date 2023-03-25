
package Task3.jykim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLot {
	public static Scanner scanner = new Scanner(System.in);
	
	private String fname;	//파일 이름
	private double cost;		//주차장 가격
	private int[][] map = null;
	private Car[][] parkingspace = null;
	private double allc =0;	// 주차장 총 수입
	private int ROW;
	private int COL;
	

	
	public ParkingLot(String fname, int cost) {
		this.cost = cost;
		this.fname = fname;
		makemap();
	}


	public void makemap() {
		
		File file = new File(fname);
		 try {
			 
	         Scanner fileScan = new Scanner(file);
	         ROW = fileScan.nextInt();
	         COL = fileScan.nextInt();
	         map = new int[ROW][COL];
	         for (int i = 0; i<map.length;i++) {
	            for (int j=0;j<map[i].length;j++) {
	               map[i][j] = fileScan.nextInt();
	            }
	         }
	         parkingspace = new Car[ROW][COL];
	         fileScan.close();
	      } catch (FileNotFoundException e) {
	         System.out.println("파일이 존재하지 않습니다.");
	         e.printStackTrace();
	      }
			
	}
	
	   public void showmap(int[][] map) {
		  System.out.println("===============================");
		  System.out.print("\t");
		  for(int i=1; i<=COL; i++) {
			  System.out.print(i + "\t");
		  }
		  System.out.println();
		  System.out.println();
		  int r = 1;
		      for(int[] row : map) {
		    	  System.out.print(r + "	");
		    	  r++;
		         for (int col : row) {
		            switch(col) {
		            case 0:System.out.print("☆	"); break;//장애인 구역
		            case 1:System.out.print("○	"); break;//소형차 구역
		            case 2:System.out.print("□	"); break;//일반차량 구역
		            case 3:System.out.print("★	"); break;
		            case 4:System.out.print("●	"); break;
		            case 5:System.out.print("■	"); break;
		            }
		         }
		         System.out.println();
		         System.out.println();
		      }
		      System.out.println();
		   System.out.println("===============================");
		   }
	   
	   public void Parking(Car car) {
		   int cnt = 1;	//
		   while(cnt == 1) {
			   showmap(map);
			   System.out.print("주차할 자리를 선택하세요(ex 1 1) : ");
			   int a = scanner.nextInt();
			   int b = scanner.nextInt();
			   int p = map[a-1][b-1];
			   
			   switch(p) {
			   case 0:
				   if(car instanceof CarForDisabledPerson) {
					   parkingspace[a-1][b-1] = car;
					   map[a-1][b-1] = 3;
					   System.out.println("주차가 완료되었습니다");
					   showmap(map);
					   return;
				   }else {
					   System.out.println("장애인 전용 구역입니다. 다른 자리를 선택해주세요.");
					   System.out.println();
				   }
				   break;
			   case 1:
				   if(car instanceof SmallCar) {
					   parkingspace[a-1][b-1] = car;
					   map[a-1][b-1] = 4;
					   System.out.println("주차가 완료되었습니다");
					   showmap(map);
					   return;
//					   cnt++;
//					   break;
				   }else {
					   System.out.println("소형차 전용 구역입니다. 다른 자리를 선택해주세요.");
					   System.out.println();
				   }
				   break;
			   case 2:
				   parkingspace[a-1][b-1] = car;
				   map[a-1][b-1] = 5;
				   System.out.println("주차가 완료되었습니다");
				   showmap(map);
				   return;
//				   cnt++;
//				   break;
			   case 3:
			   case 4:
			   case 5:
				   System.out.println("이미 주차된 구역입니다. 다른 자리를 선택해주세요.");
				   break;
			   }
		   }
	   }
		public void PullOut() {
			System.out.print("출차할 차량번호를 입력하세요 : ");
			String num = scanner.next();
			int cut = ROW * COL;
			int lim=0;
			for(int i=0; i<ROW; i++) {
				for(int j=0; j<COL; j++) {
					if(parkingspace[i][j]==null) {
						lim++;
						continue;
					}
					else {
						if((parkingspace[i][j].car_num).equals(num)) {
							double amount = calcost(parkingspace[i][j]);
							System.out.println("주차 가격은 " + amount + "입니다. 안녕히 가세요.");
							allc += amount;
							map[i][j] = map[i][j] - 3;
							parkingspace[i][j] = null;
							return;
							//break;
						}
					}
				}
			}
			if(lim == cut) {
				System.out.println("차량 번호를 확인해주세요.");
			}
		}

	public double calcost(Car car) {
		if(car instanceof CarForDisabledPerson) {
			String h = ((CarForDisabledPerson) car).getHandi();
			if(h.equals("중증")) {
				return cost * 0.3;
			}else return cost * 0.4;
		}else if(car instanceof SmallCar) {
			return cost * 0.5;
		}else return cost;
	}
	
	@Override
	public String toString() {
		String str = "기본 주차료 : "+this.cost + "원";
		str += "\n전체 수입 : " + this.allc + "원";
		return str;
	}

	
}
