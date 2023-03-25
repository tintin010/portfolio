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
				System.out.println(userName+"의 단어장이 생성됨");
				menu();
				
			}catch (FileNotFoundException e) {					
				System.err.println(e.getMessage());
			};
		}
		
		void menu() {
			String Choice;
			int choice =0;
			while(choice != 3) {
				System.out.println("\n--------"+ userName +"의 단어장----------");
				System.out.println("1) 단어검색 , 2)단어 검색 2, 3) 종료");
				System.out.println("메뉴를 선택하세요");

		         while(check == 0) { // 문자 입력시 다시입력받기
		        	 Choice = scan.next();
		        	 scan.nextLine();
		        	 if(Choice.equals("1") || Choice.equals("2") || Choice.equals("3")) {
		        		 choice = Integer.parseInt(Choice);
		        		 check = 1;
		        	 }else {
		        		 System.out.println("다시 입력해주세요 : ");
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
					System.out.println(userName + "의 단어장을 종료합니다");
					break;
				default : 
					System.out.println("입력을 다시해주세요");
					break;
					
					
				}
			}
		}
		


		public void searchVoc() {
			// TODO Auto-generated method stub
			System.out.println("------단어 검색 -------");
			System.out.println("검색할 단어를 입력하세요");
			String sWord = scan.nextLine();
			sWord = sWord.trim();
			for(Word word : voc) {
				if(word != null) {
					if(word.eng.equals(sWord)) {
						System.out.println("단어의 뜻 : "+word.kor);
						break;
					}
				}else {
					System.out.println("단어장에 등록되어 있지 않습니다.");
					break;
				}
			}
			
			System.out.println("\n------------------");
			
		}
		
		public void searchVoc2() {
			System.out.println("------단어 검색 -------");
			System.out.println("검색할 단어를 입력하세요");
			String sWord = scan.nextLine();
			sWord = sWord.trim();
			for(Word word : voc) {
				if(word != null) {
					if(word.eng.contains(sWord)) {
						System.out.println("단어 : "+word.eng+"단어의 뜻 : "+word.kor);
						
					}
				}
			}
			System.out.println("\n------------------");
			
		}
}
