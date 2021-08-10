// https://www.acmicpc.net/problem/1158

import java.util.LinkedList;
import java.util.Scanner;

public class Josephus_1158 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> list = new LinkedList<>();
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int index = 0;					// 주어진 배열에서 제거할 위치를 담는 인덱스
		int[] deleted = new int[N];		// 요세푸스 순열을 담는 배열
		int delIndex = 0;				// 위 배열의 담을 차례 인덱스
		for (int i = 0; i < N; i++) {
			list.add(i+1);
		}
		
		while (!list.isEmpty()) {
			index = (index + K - 1) % list.size();
			deleted[delIndex] = list.get(index);
			delIndex++;
			list.remove(index);
		}

		sb.append("<");
		for (int i : deleted) {
			sb.append(i + ", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}
}