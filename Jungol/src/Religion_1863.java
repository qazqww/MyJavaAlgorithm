// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1136&sca=99&sfl=wr_hit&stx=1863

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Religion_1863 {

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()) + 1;	// 학생의 수
		int M = Integer.parseInt(st.nextToken());		// 쌍의 수
		parents = new int[N];
		for (int i = 1; i < N; i++)
			parents[i] = i;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			
			int p1Root = findRoot(p1);
			int p2Root = findRoot(p2);
			if (p1Root != p2Root) {
				parents[p2Root] = p1Root;
			}
		}
		
		int answer = 0;
		for (int i = 1; i < N; i++) {
			if (parents[i] == i)
				answer++;
		}
		
		System.out.println(answer);
	}
	
	static int findRoot(int n) {
		if (n == parents[n])
			return n;
		return parents[n] = findRoot(parents[n]);
	}
}