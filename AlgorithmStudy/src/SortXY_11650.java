// https://www.acmicpc.net/problem/11650

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SortXY_11650 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// int[] => { x좌표, y좌표 }
		PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> {
			if (arr1[0] == arr2[0])			// x좌표가 같으면
				return arr1[1] - arr2[1];	// y좌표로 비교
			return arr1[0] - arr2[0];		// 다르면 x좌표 비교
		});
		
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] { x, y });
		}
		
		while (!pq.isEmpty()) {
			int[] temp = pq.poll();
			out.write(temp[0] + " " + temp[1] + "\n");
			out.flush();
		}
	}
}