// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Changyong_7465 {
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()) + 1;		// 마을 사람 수
			int relation = Integer.parseInt(st.nextToken());	// 사람 관계 수
			
			parents = new int[num];			// 원소의 부모를 담는 배열
			for (int i = 1; i < num; i++)
				parents[i] = i;				// 자신밖에 없는 무리로 시작. 자신을 대표자로 초기화
			
			for (int i = 0; i < relation; i++) {
				st = new StringTokenizer(in.readLine());
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				
				int p1Root = findRoot(p1);
				int p2Root = findRoot(p2);
				if (p1Root == p2Root)		// 두 원소의 부모가 같다 => 이미 하나의 무리이다
					continue;
				parents[p1Root] = p2Root;	// 두 원소의 부모가 다르다 => 한 무리를 다른 무리에 속하게 한다
			}
			
			int answer = 0;
			for (int i = 1; i < num; i++) {
				if (i == parents[i])		// 무리마다 하나의 대표자가 존재하므로, 대표자의 개수를 세면 무리 개수를 알 수 있다
					answer++;
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
	
	// 무리의 대표자를 찾는 find set 함수
	static int findRoot(int num) {
		if (num == parents[num])	// 자신이 대표자인 경우 자신을 리턴
			return num;
		parents[num] = findRoot(parents[num]);	// 아닐 경우 자신의 부모를 통해 찾아본다
		return parents[num];
	}
}