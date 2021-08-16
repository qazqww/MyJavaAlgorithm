// https:// swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWl0ZQ8qn7UDFAXz

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NoGlasses_7272 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			char[] arr1 = sc.next().toCharArray();
			char[] arr2 = sc.next().toCharArray();
			
			String answer = isSame(arr1, arr2) ? "SAME" : "DIFF";
			
			System.out.printf("#%d %s\n", t+1, answer);
		}
	}
	
	static boolean isSame(char[] arr1, char[] arr2) {
		List<Set<Character>> holes = new ArrayList<>();
		holes.add(new HashSet<>(Arrays.asList(
				'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')));
		holes.add(new HashSet<>(Arrays.asList('A','D','O','P','Q','R')));
		holes.add(new HashSet<>(Arrays.asList('B')));
		
		if (arr1.length != arr2.length) {
			return false;
		}
		
		loop: for (int i = 0; i < arr1.length; i++) {
			for (int c1 = 0; c1 < 3; c1++) {	// c1 : arr1[i]의 hole 개수
				if (holes.get(c1).contains(arr1[i]))
				{
					for (int c2 = 0; c2 < 3; c2++) {	// c2 : arr2[i]의 hole 개수
						if (holes.get(c2).contains(arr2[i])) {
							if (c1 != c2)	// hole 개수가 다르면 다른 문자로 취급
								return false;
							continue loop;
						}
					}
				}
			}
		}
		
		return true;
	}
}