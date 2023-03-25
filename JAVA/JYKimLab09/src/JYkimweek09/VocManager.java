package JYkimweek09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VocManager {
		private String userName;
		private ArrayList<Word> voc = new ArrayList<>();
		int check = 0;

		
		static Scanner scan = new Scanner(System.in);
		
		VocManager(String userName) {
			this.userName = userName;
		}
		
		void addWord(Word word) {
			voc.add(word);
		}

		void makeVoc(String fileName) {
			try(Scanner scan = new Scanner(new File(fileName))){
				while(scan.hasNextLine()) {
					String str = scan.nextLine();
					String[] temp = str.split("\t");
					this.addWord(new Word(temp[0].trim(),temp[1].trim()));
				}
				System.out.println(userName+"�� �ܾ����� ������");
				menu();
				
			}catch (FileNotFoundException e) {					
				System.err.println(e.getMessage());
			};
		}
		
		void menu() {
			String Choice;
			int choice =0;
			while(choice != 3) {
				System.out.println("\n--------"+ userName +"�� �ܾ���----------");
				System.out.println("1) �ܾ�˻� , 2)�ܾ� �˻� 2, 3) ����");
				System.out.println("�޴��� �����ϼ���");

		         while(check == 0) { // ���� �Է½� �ٽ��Է¹ޱ�
		        	 Choice = scan.next();
		        	 scan.nextLine();
		        	 if(Choice.equals("1") || Choice.equals("2") || Choice.equals("3")) {
		        		 choice = Integer.parseInt(Choice);
		        		 check = 1;
		        	 }else {
		        		 System.out.println("�ٽ� �Է����ּ��� : ");
		        		 check = 0;
		        	 }
		         }
		         check = 0;
				
				System.out.println();
				
				switch (choice) {
				case 1 : 
					searchVoc();
					break;
				case 2 : 
					searchVoc2();
					break;
				case 3 : 
					System.out.println(userName + "�� �ܾ����� �����մϴ�");
					break;
				default : 
					System.out.println("�Է��� �ٽ����ּ���");
					break;
					
					
				}
			}
		}
		


		public void searchVoc() {
			// TODO Auto-generated method stub
			System.out.println("------�ܾ� �˻� -------");
			System.out.println("�˻��� �ܾ �Է��ϼ���");
			String sWord = scan.nextLine();
			sWord = sWord.trim();
			for(Word word : voc) {
				if(word != null) {
					if(word.eng.equals(sWord)) {
						System.out.println("�ܾ��� �� : "+word.kor);
						break;
					}
				}else {
					System.out.println("�ܾ��忡 ��ϵǾ� ���� �ʽ��ϴ�.");
					break;
				}
			}
			
			System.out.println("\n------------------");
			
		}
		
		public void searchVoc2() {
			System.out.println("------�ܾ� �˻� -------");
			System.out.println("�˻��� �ܾ �Է��ϼ���");
			String sWord = scan.nextLine();
			sWord = sWord.trim();
			for(Word word : voc) {
				if(word != null) {
					if(word.eng.contains(sWord)) {
						System.out.println("�ܾ� : "+word.eng+"�ܾ��� �� : "+word.kor);
						
					}
				}
			}
			System.out.println("\n------------------");
			
		}
}
