
package Task3.jykim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLot {
	public static Scanner scanner = new Scanner(System.in);
	
	private String fname;	//���� �̸�
	private double cost;		//������ ����
	private int[][] map = null;
	private Car[][] parkingspace = null;
	private double allc =0;	// ������ �� ����
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
	         System.out.println("������ �������� �ʽ��ϴ�.");
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
		            case 0:System.out.print("��	"); break;//����� ����
		            case 1:System.out.print("��	"); break;//������ ����
		            case 2:System.out.print("��	"); break;//�Ϲ����� ����
		            case 3:System.out.print("��	"); break;
		            case 4:System.out.print("��	"); break;
		            case 5:System.out.print("��	"); break;
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
			   System.out.print("������ �ڸ��� �����ϼ���(ex 1 1) : ");
			   int a = scanner.nextInt();
			   int b = scanner.nextInt();
			   int p = map[a-1][b-1];
			   
			   switch(p) {
			   case 0:
				   if(car instanceof CarForDisabledPerson) {
					   parkingspace[a-1][b-1] = car;
					   map[a-1][b-1] = 3;
					   System.out.println("������ �Ϸ�Ǿ����ϴ�");
					   showmap(map);
					   return;
				   }else {
					   System.out.println("����� ���� �����Դϴ�. �ٸ� �ڸ��� �������ּ���.");
					   System.out.println();
				   }
				   break;
			   case 1:
				   if(car instanceof SmallCar) {
					   parkingspace[a-1][b-1] = car;
					   map[a-1][b-1] = 4;
					   System.out.println("������ �Ϸ�Ǿ����ϴ�");
					   showmap(map);
					   return;
//					   cnt++;
//					   break;
				   }else {
					   System.out.println("������ ���� �����Դϴ�. �ٸ� �ڸ��� �������ּ���.");
					   System.out.println();
				   }
				   break;
			   case 2:
				   parkingspace[a-1][b-1] = car;
				   map[a-1][b-1] = 5;
				   System.out.println("������ �Ϸ�Ǿ����ϴ�");
				   showmap(map);
				   return;
//				   cnt++;
//				   break;
			   case 3:
			   case 4:
			   case 5:
				   System.out.println("�̹� ������ �����Դϴ�. �ٸ� �ڸ��� �������ּ���.");
				   break;
			   }
		   }
	   }
		public void PullOut() {
			System.out.print("������ ������ȣ�� �Է��ϼ��� : ");
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
							System.out.println("���� ������ " + amount + "�Դϴ�. �ȳ��� ������.");
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
				System.out.println("���� ��ȣ�� Ȯ�����ּ���.");
			}
		}

	public double calcost(Car car) {
		if(car instanceof CarForDisabledPerson) {
			String h = ((CarForDisabledPerson) car).getHandi();
			if(h.equals("����")) {
				return cost * 0.3;
			}else return cost * 0.4;
		}else if(car instanceof SmallCar) {
			return cost * 0.5;
		}else return cost;
	}
	
	@Override
	public String toString() {
		String str = "�⺻ ������ : "+this.cost + "��";
		str += "\n��ü ���� : " + this.allc + "��";
		return str;
	}

	
}
