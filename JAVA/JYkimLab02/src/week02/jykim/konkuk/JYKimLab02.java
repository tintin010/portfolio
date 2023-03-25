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
	      System.out.println("주차관리 (202010122 나예담)");
	      
	      
	      if(map!=null)
	         showMap(map);
	      
	      System.out.println("1) 주차하기 2) 출차하기 3) 종료");
	      System.out.print("메뉴를 선택하세요 : ");
	      
	      int input = scanner.nextInt();
	      
	         if (input == 1) {
	            while(true) {
	               System.out.print("주차할 위치를 선택해 주세요 (입력 예 : 2 1) :");
	               //주차공간 이외의 위치를 선택한 경우 예외처리
	               
	               int row = scanner.nextInt();
	               int col = scanner.nextInt();
	               
	               if (( row>4 || row < 1 ) || (col>3 || col < 1)) { // D
	                  System.out.println("위치 번호를 확인해 주세요. 처음부터 다시 진행해 주세요.");
	                  row = 0;
	                  col = 0;
	                  break;
	                  }
	               else if (map[row][col]==6) {
	                   System.out.println("다른 차량이 주차되어 있습니다. 처음부터 다시 시작해주세요.");
	                   break;
	                }
	               else {
	                  System.out.print("차량 번호를 입력해주세요(입력예 : 20가1234): ");
	                  String carNum = scanner.next();
	                  System.out.print("차량 번호 "+carNum+" 맞습니까(y/n)?");
	                  char answer = scanner.next().charAt(0);
	                  if (answer == 'y') { //E완료
	                     System.out.println(carNum+"차량의 주차를 완료하였습니다.");
	                     parkingSpace[row][col] = carNum;
	                     map[row][col] = 6;
	                     showMap(map);
	                     break;
	                     
	                  }
	                  else if (answer == 'n') {
	                     System.out.println("처음부터 다시 진행해주세요.");
	                     break;
	                  }
	                  }
	               }
	               
	            }
	         else if(input == 2) {
	        	 int c = 0;	//차량의 존재여부 확인
	        	 int cut = ROW * COL;
	        	 scanner.nextLine();
	            System.out.println("차량 번호를 입력해주세요: ");
	             String outcar = scanner.nextLine();
	             for (int i=0; i<5; i++) {
	                for (int j=0; j<4; j++) {
	                   if (outcar.equals(parkingSpace[i][j])) {
	                      System.out.println(outcar+" 차랑이 출차되었습니다. 안녕히 가세요.");
	                      break;
	                   }
	                   c++;
	                }
	             }
	             if(c == cut)
	             System.out.println("차량이 존재하지 않습니다. 차량번호 확인 후 처음부터 다시 진행해 주세요.");
//	             if ((parkingSpace[i][j]!=outcar)&&(parkingSpace[i][j]==null)) {
//                     System.out.println("차량이 존재하지 않습니다. 차량번호 확인 후 처음부터 다시 진행해 주세요.");
//                     break;
//                  }
	         }
	         else if(input == 3) {
	            scanner.close();
	            System.out.println("시스템을 종료합니다.");
	            System.exit(0); //진짜 이 프로그램을 종료한다는 뜻. 
	            break;
	         }
	         else {
	         System.out.println("메뉴 번호를 확인 후 다시 입력해주세요.");
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
	            case 5:System.out.print("♡"); break;
	            case 6:System.out.print("♥"); break;
	            }
	         }
	         
	         System.out.println();
	      } 
	   }
	   public static int[][] readMap(String filename) {
	      int[][] map = null;
	      File file = new File(filename); //file객체 생성(map.txt)
	      
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
	         System.out.println("파일이 존재하지 않습니다.");
	         e.printStackTrace();
	      }
	      
	      return map;
	   }

	}
