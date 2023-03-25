package week02.jykim.konkuk;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class JYKimLab02 {

	   public static Scanner scanner = new Scanner(System.in);
	   
	   static final int ROW = 5;
	   static final int COL = 4;   
	   
	   public static void main(String[] args) {
	      // TODO Auto-generated method stub
	      String[][] parkingSpace = new String[ROW][COL];
	      
	      String filename = "map.txt";
	      
	      int[][] map = readMap(filename);
	      while (true) {
	      System.out.println("�������� (202010122 ������)");
	      
	      
	      if(map!=null)
	         showMap(map);
	      
	      System.out.println("1) �����ϱ� 2) �����ϱ� 3) ����");
	      System.out.print("�޴��� �����ϼ��� : ");
	      
	      int input = scanner.nextInt();
	      
	         if (input == 1) {
	            while(true) {
	               System.out.print("������ ��ġ�� ������ �ּ��� (�Է� �� : 2 1) :");
	               //�������� �̿��� ��ġ�� ������ ��� ����ó��
	               
	               int row = scanner.nextInt();
	               int col = scanner.nextInt();
	               
	               if (( row>4 || row < 1 ) || (col>3 || col < 1)) { // D
	                  System.out.println("��ġ ��ȣ�� Ȯ���� �ּ���. ó������ �ٽ� ������ �ּ���.");
	                  row = 0;
	                  col = 0;
	                  break;
	                  }
	               else if (map[row][col]==6) {
	                   System.out.println("�ٸ� ������ �����Ǿ� �ֽ��ϴ�. ó������ �ٽ� �������ּ���.");
	                   break;
	                }
	               else {
	                  System.out.print("���� ��ȣ�� �Է����ּ���(�Է¿� : 20��1234): ");
	                  String carNum = scanner.next();
	                  System.out.print("���� ��ȣ "+carNum+" �½��ϱ�(y/n)?");
	                  char answer = scanner.next().charAt(0);
	                  if (answer == 'y') { //E�Ϸ�
	                     System.out.println(carNum+"������ ������ �Ϸ��Ͽ����ϴ�.");
	                     parkingSpace[row][col] = carNum;
	                     map[row][col] = 6;
	                     showMap(map);
	                     break;
	                     
	                  }
	                  else if (answer == 'n') {
	                     System.out.println("ó������ �ٽ� �������ּ���.");
	                     break;
	                  }
	                  }
	               }
	               
	            }
	         else if(input == 2) {
	        	 int c = 0;	//������ ���翩�� Ȯ��
	        	 int cut = ROW * COL;
	        	 scanner.nextLine();
	            System.out.println("���� ��ȣ�� �Է����ּ���: ");
	             String outcar = scanner.nextLine();
	             for (int i=0; i<5; i++) {
	                for (int j=0; j<4; j++) {
	                   if (outcar.equals(parkingSpace[i][j])) {
	                      System.out.println(outcar+" ������ �����Ǿ����ϴ�. �ȳ��� ������.");
	                      break;
	                   }
	                   c++;
	                }
	             }
	             if(c == cut)
	             System.out.println("������ �������� �ʽ��ϴ�. ������ȣ Ȯ�� �� ó������ �ٽ� ������ �ּ���.");
//	             if ((parkingSpace[i][j]!=outcar)&&(parkingSpace[i][j]==null)) {
//                     System.out.println("������ �������� �ʽ��ϴ�. ������ȣ Ȯ�� �� ó������ �ٽ� ������ �ּ���.");
//                     break;
//                  }
	         }
	         else if(input == 3) {
	            scanner.close();
	            System.out.println("�ý����� �����մϴ�.");
	            System.exit(0); //��¥ �� ���α׷��� �����Ѵٴ� ��. 
	            break;
	         }
	         else {
	         System.out.println("�޴� ��ȣ�� Ȯ�� �� �ٽ� �Է����ּ���.");
	         }
	      }
	      
	   }
	   public static void showMap(int[][] map) {
	      // TODO Auto-generated method stub
	      for(int[] row : map) {
	         for (int col : row) {
	            switch(col) {
	            case 0:System.out.print(" "); break;
	            case 1:System.out.print("1"); break;
	            case 2:System.out.print("2"); break;
	            case 3:System.out.print("3"); break;
	            case 4:System.out.print("4"); break;
	            case 5:System.out.print("��"); break;
	            case 6:System.out.print("��"); break;
	            }
	         }
	         
	         System.out.println();
	      } 
	   }
	   public static int[][] readMap(String filename) {
	      int[][] map = null;
	      File file = new File(filename); //file��ü ����(map.txt)
	      
	      try {
	         Scanner fileScan = new Scanner(file);
	         final int ROW = fileScan.nextInt();
	         final int COL = fileScan.nextInt();
	         map = new int[ROW][COL];
	         for (int i = 0; i<map.length;i++) {
	            for (int j=0;j<map[i].length;j++) {
	               map[i][j] = fileScan.nextInt();
	            }
	         }
	         
	         fileScan.close();
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         System.out.println("������ �������� �ʽ��ϴ�.");
	         e.printStackTrace();
	      }
	      
	      return map;
	   }

	}
