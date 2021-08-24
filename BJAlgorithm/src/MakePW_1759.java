// https://www.acmicpc.net/problem/1759

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MakePW_1759 {
	
	static int len;			// 암호의 길이
	static int charCnt;		// 알파벳의 개수
	static ArrayList<Character> charList;	// 어떤 알파벳이 있는지
	static char[] pw;		// 완성된 암호

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		len = sc.nextInt();
		charCnt = sc.nextInt();
		charList = new ArrayList<>();
		pw = new char[len];
		
		for (int i = 0; i < charCnt; i++) {
			charList.add(sc.next().charAt(0));
		}
		
		Collections.sort(charList);		// 사전 순으로 출력하기 위해 미리 정렬
		combi(0, 0);
	}
	
	static void combi(int index, int num) {
		if (index == len) {
			int moumCnt = 0;
			for (int i = 0; i < pw.length; i++) {	// 모음의 개수를 구함
				if (pw[i] == 'a' || pw[i] == 'e' || pw[i] == 'i' || pw[i] == 'o' || pw[i] == 'u')
					moumCnt++;
			}
			if (moumCnt >= 1 && moumCnt <= len - 2)	// 모음이 1개 이상이고 자음(모음을 제외한 나머지)이 2개 이상인지 체크
				System.out.println(new String(pw));
			
			return;
		}
		
		// 조합 구하기
		for (int i = num; i < charCnt; i++) {
			pw[index] = charList.get(i);
			combi(index+1, i+1);
		}
	}
}