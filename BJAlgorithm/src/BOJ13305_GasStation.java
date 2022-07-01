// https://www.acmicpc.net/problem/13305

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13305_GasStation {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] dist = new int[N-1];
		int[] price = new int[N];
		long total = 0L;
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.parseInt(st.nextToken());
		}

		int nowPrice = -1;
		long nowDist = 0L;
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < price.length; i++) {
			price[i] = Integer.parseInt(st.nextToken());
			
			if (i == 0)
				nowPrice = price[i];
			
			if (i == price.length - 1 || price[i] < nowPrice) {
				total += nowDist * nowPrice;
				nowPrice = price[i];
				nowDist = 0;
			}

			if (i != price.length - 1)
				nowDist += dist[i];
		}
		
		System.out.println(total);
	}
}