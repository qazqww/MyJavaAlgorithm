// https://www.acmicpc.net/problem/16508

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// 가격과 필요한 문자를 담는 책 클래스
class Book {
	int price;
	String name;
	
	public int getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public Book(int price, String name) {
		this.price = price;
		this.name = name;
	}
}

public class MajorBook_16508 {

	static List<Book> bookList;							// 입력된 책의 정보를 담는 리스트
	static List<char[]> charNow = new ArrayList<>();	// 부분집합 책 제목의 스펠링을 담는 리스트
	static int totalPrice = Integer.MAX_VALUE;			// 단어를 만들기 위한 가장 적은 비용
	static int nowPrice = 0;							// 부분집합 책들의 총 가격
	static char[] T;									// 만들고자 하는 단어
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.next().toCharArray();
		
		Set<Character> chrs = new HashSet<>();	// 책 속에 필요한 알파벳이 있는지 확인하기 위한 임시 Set (중복 제거, 빠른 탐색을 위해)
		for (char t : T) {
			chrs.add(t);
		}
		
		int N = sc.nextInt();						// 입력할 책의 개수
		StringBuilder sb = new StringBuilder();		// 책 제목에서 필요한 문자를 걸러낼 String
		bookList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int price = sc.nextInt();
			char[] temp = sc.next().toCharArray();
			
			// 필요한 문자만 String에 담도록 처리
			for (int j = 0; j < temp.length; j++) {
				if (chrs.contains(temp[j])) {
					sb.append(temp[j]);
				}
			}
			
			// 필요한 문자가 있는 책만 북 리스트에 추가
			if (sb.length() > 0) {
				bookList.add(new Book(price, sb.toString()));
				sb.setLength(0);
			}
		}
		
		powerSet(0);	// 부분집합 실행
		System.out.println((totalPrice == Integer.MAX_VALUE) ? -1 : totalPrice);
	}
	
	static void powerSet(int index) {
		if (index == bookList.size()) {
			
			// 단어 완성이 되는지 체크
			List<Character> charTemp = new LinkedList<>();		// 담은 책의 문자들을 복제해올 리스트
			for (int i = 0; i < charNow.size(); i++) {
				for (char ch : charNow.get(i)) {
					charTemp.add(ch);
				}
			}

			boolean success = true;
			for (char t : T) {
				// 필요한 단어의 각 문자가 있는지 체크
				int chIndex = charTemp.indexOf(t);
				if (chIndex != -1) {
					charTemp.remove(chIndex);
				} else {
					success = false;
					break;
				}
			}
			
			if (success && nowPrice < totalPrice) {
				totalPrice = nowPrice;
			}
			return;
		}
		
		Book book = bookList.get(index);
		// 책을 넣고 다음 단계로
		charNow.add(book.getName().toCharArray());
		nowPrice += book.getPrice();
		powerSet(index+1);
		
		// 책을 넣지 않고 다음 단계로
		charNow.remove(charNow.size() - 1);
		nowPrice -= book.getPrice();
		powerSet(index+1);
	}
}
