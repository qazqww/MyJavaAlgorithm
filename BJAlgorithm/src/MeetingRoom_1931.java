// https://www.acmicpc.net/problem/1931

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MeetingRoom_1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		ArrayList<int[]> time = new ArrayList<>();	// { 시작시간, 끝시간 } 의 리스트
		
		for (int i = 0; i < N; i++) {
			String[] temp = in.readLine().split(" ");
			time.add(new int[] { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) });
		}
		
		// 종료시간 순서(주), 시작시간 순서(부) 순으로 정렬
		Collections.sort(time, (int[] t1, int[] t2) -> {
			if (t1[1] - t2[1] != 0)
				return t1[1] - t2[1];
			return t1[0]-t2[0];
		});

		int now = -1;
		int answer = 0;
		for (int[] t : time) {
			if (t[0] >= now) {	// 현재 시간보다 시작 시간이 늦으면 시작 가능 -> 삽입
				now = t[1];		// 현재 시간을 회의 끝나는 시간으로 설정
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}
