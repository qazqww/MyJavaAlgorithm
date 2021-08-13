// https://www.acmicpc.net/problem/10867

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SortNotDupl_10867 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Set<Integer> set = new HashSet<>();
		
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			set.add(sc.nextInt());
		}

		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		
		for (int i : list) {
			System.out.print(i + " ");
		}
	}
}