// https://www.acmicpc.net/problem/1803

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1803_MartialArtsPrac {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int aLen = Integer.parseInt(st.nextToken());
		int bLen = Integer.parseInt(st.nextToken());
		
		Info[] a = new Info[aLen];
		Info[] b = new Info[bLen];
		for (int i = 0; i < aLen; i++)
			a[i] = new Info();
		for (int i = 0; i < bLen; i++)
			b[i] = new Info();
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < aLen; i++) {
			int t = Integer.parseInt(st.nextToken()) - 1;
			a[i].myTarget = t;
			b[t].targeted++;
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < bLen; i++) {
			int t = Integer.parseInt(st.nextToken()) - 1;
			b[i].myTarget = t;
			a[t].targeted++;
		}
		
		boolean updated = true;
		while (updated) {
			updated = false;
			for (int i = 0; i < aLen; i++) {
				if (a[i].result != -1)
					continue;
				
				if (a[i].targeted == 0) {
					a[i].result = 1;
					updated = true;
					
					Info aimed = b[a[i].myTarget];
					if (aimed.result == -1) {
						aimed.result = 0; // a가 가리킨 b에게 방패
						a[aimed.myTarget].targeted--; // a가 가리킨 b가 가리킨 a의 지목을 취소
					}
				}
			}
			
			for (int i = 0; i < bLen; i++) {
				if (b[i].result != -1)
					continue;
				
				if (b[i].targeted == 0) {
					b[i].result = 1;
					updated = true;
					
					Info aimed = a[b[i].myTarget];
					if (aimed.result == -1) {
						aimed.result = 0;
						b[aimed.myTarget].targeted--;
					}
				}
			}
			
			if (!updated) { // 서로를 겨누고 있는 경우
				for (int i = 0; i < aLen; i++) {
					if (a[i].result == -1) {
						a[i].result = 1;
						updated = true;
						
						Info aimed = b[a[i].myTarget];
						if (aimed.result == -1) {
							aimed.result = 0;
							a[aimed.myTarget].targeted--;
						}
					}
				}
				
				for (int i = 0; i < bLen; i++) {
					if (b[i].result == -1) {
						b[i].result = 1;
						updated = true;
						
						Info aimed = a[b[i].myTarget];
						if (aimed.result == -1) {
							aimed.result = 0;
							b[aimed.myTarget].targeted--;
						}
					}
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < aLen; i++) {
			answer.append(a[i].result);
		}
		answer.append("\n");
		for (int i = 0; i < bLen; i++) {
			answer.append(b[i].result);
		}
		System.out.println(answer);
	}
	
	static class Info {
		int myTarget;
		int targeted;
		int result;
		public Info() {
			this.myTarget = -1;
			this.targeted = 0;
			this.result = -1;
		}
	}
}