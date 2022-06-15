// https://www.acmicpc.net/problem/12865
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865_NormalBag {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for (int k = K; k >= w; k--) {
				if (dp[k - w] + v > dp[k]) {
					dp[k] = dp[k - w] + v;
				}
			}
		}
		
		System.out.println(dp[K]);
	}
}
