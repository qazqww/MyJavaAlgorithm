// https://www.acmicpc.net/problem/10163

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Colorpaper_10163 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()) + 1;	// 0을 제외한 1 ~ N 인덱스 사용
		int[][] map = new int[1001][1001];
		
		for (int n = 1; n < N; n++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int sX = Integer.parseInt(st.nextToken());
			int sY = Integer.parseInt(st.nextToken());
			int eX = sX + Integer.parseInt(st.nextToken());
			int eY = sY + Integer.parseInt(st.nextToken());
			
			// 맵에 색종이 번호를 표시
			for (int y = sY; y < eY; y++) {
				for (int x = sX; x < eX; x++) {
					map[y][x] = n;
				}
			}
		}
		
		// 맵을 순회하면서 각 번호를 카운트
		int[] cnt = new int[N];
		for (int y = 0; y < 1001; y++) {
			for (int x = 0; x < 1001; x++) {
				cnt[map[y][x]]++;
			}
		}
		
		for (int i = 1; i < N; i++)
			System.out.println(cnt[i]);
	}
}