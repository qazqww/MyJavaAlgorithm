// https://www.acmicpc.net/problem/13300

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AssignRoom_13300 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());
		int[] students = new int[12];	// 1여 1남 2여 2남, .... 6여 6남
		int room = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int sex = Integer.parseInt(st.nextToken());		// 여 0, 남 1
			int grade = Integer.parseInt(st.nextToken());
			students[(grade-1) * 2 + sex]++;
		}
		
		for (int i = 0; i < 12; i++) {
			if (students[i] == 0)
				continue;
			room += (students[i]-1) / limit + 1;
		}
		
		System.out.println(room);
	}
}