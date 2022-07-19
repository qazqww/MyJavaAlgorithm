// https://www.acmicpc.net/problem/1269

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1269_SymmetrySet {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int aNum = Integer.parseInt(st.nextToken());
		int bNum = Integer.parseInt(st.nextToken());
		
		Set<Integer> set = new HashSet<>();
		int answer = 0;

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < aNum; i++) {
			set.add(Integer.parseInt(st.nextToken()));
			answer++;
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < bNum; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (set.contains(num)) {
				answer--;
			}
			else {
				set.add(num);
				answer++;
			}
		}
		
		System.out.println(answer);
		
	}
}