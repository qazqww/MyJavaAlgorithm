// https://www.acmicpc.net/problem/3052

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Remainder_3052 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Integer> set = new HashSet<>();
		
		for (int i = 0; i < 10; i++) {
			int num = sc.nextInt();
			set.add(num % 42);
		}
		
		System.out.println(set.size());
	}
}