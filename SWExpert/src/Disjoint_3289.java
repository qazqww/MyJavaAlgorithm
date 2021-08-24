// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Disjoint_3289 {
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			System.out.printf("#%d ", t+1);
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()) + 1;		// 수의 개수
			int cnt = Integer.parseInt(st.nextToken());			// 연산의 개수
			
			parents = new int[num];		// parents[i] => i index의 부모
			for (int i = 1; i < num; i++) {
				parents[i] = i;		// 초기값으로 자기 자신을 부모로 설정
			}
			
			for (int i = 0; i < cnt; i++) {
				st = new StringTokenizer(in.readLine());
				int op = Integer.parseInt(st.nextToken());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				
				switch(op) {
				case 0:		// union 연산
					int n1Root = findHead(n1);
					int n2Root = findHead(n2);
					if (n1Root == n2Root)	// root가 같을 경우 : 이미 같은 집합이다
						continue;
					parents[n1Root] = n2Root;	// 아닐 경우 : 한놈의 root의 부모를 다른 쪽 root로 바꾼다
					break;
				case 1:		// find set 연산
					System.out.print((findHead(n1) == findHead(n2)) ? 1 : 0);
					break;
				}
			}
			System.out.println();
		}
	}
	
	static int findHead(int n) {
		if (n == parents[n])
			return n;
		return parents[n] = findHead(parents[n]);
	}
}