// https://www.acmicpc.net/problem/1931

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1931_MeetingRoom {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		
		PriorityQueue<Meeting> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Meeting(start, end));
		}
		
		int t = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			Meeting m = pq.poll();
			if (m.start >= t) {
				t = m.end;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static class Meeting implements Comparable<Meeting> {
		int start, end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			if (end == o.end)
				return Integer.compare(start, o.start);
			return Integer.compare(end, o.end);
		}
	}
}



/* 예전 코드
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
*/