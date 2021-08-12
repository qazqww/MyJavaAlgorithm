// https://www.acmicpc.net/problem/11723

// 비트 연산으로 풀어보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Set_11723 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(in.readLine());
		int S = 0;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			switch (st.nextToken()) {
			case "add":
			{
				int num = Integer.parseInt(st.nextToken()) - 1;
				S |= (1 << num);
			}
				break;
			case "remove":
			{
				int num = Integer.parseInt(st.nextToken()) - 1;
				S &= ~(1 << num);
			}
				break;
			case "check":
			{
				int num = Integer.parseInt(st.nextToken()) - 1;
				if ((S & (1 << num)) != 0) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
			}
				break;
			case "toggle":
			{
				int num = Integer.parseInt(st.nextToken()) - 1;
				S ^= (1 << num);
			}
				break;
			case "all":
				S = (int)Math.pow(2, 20) - 1;
				break;
			case "empty":
				S = 0;
				break;
			}
		}
		
		System.out.println(sb);
	}
}