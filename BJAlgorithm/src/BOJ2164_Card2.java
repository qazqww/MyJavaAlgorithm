// https://www.acmicpc.net/problem/2164

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2164_Card2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			queue.offer(i);
		
		int num = -1;
		while (!queue.isEmpty()) {
			num = queue.poll();
			if (queue.isEmpty())
				break;
			queue.offer(queue.poll());
		}
		
		System.out.println(num);
	}
}
