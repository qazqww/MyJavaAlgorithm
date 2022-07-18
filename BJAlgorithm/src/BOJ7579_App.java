import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7579_App {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int appNum = Integer.parseInt(st.nextToken());
		int needByte = Integer.parseInt(st.nextToken());
		
		int[] using = new int[appNum];
		int[] reuse = new int[appNum];
		int reuseSum = 0;
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < appNum; i++) {
			using[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < appNum; i++) {
			reuse[i] = Integer.parseInt(st.nextToken());
			reuseSum += reuse[i];
		}
		
		int[] dp = new int[reuseSum + 1];
		for (int i = 0; i < appNum; i++) {
			for (int j = dp.length - 1; j >= reuse[i]; j--) {
				if (dp[j - reuse[i]] + using[i] > dp[j])
					dp[j] = dp[j - reuse[i]] + using[i];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			if (dp[i] >= needByte) {
				System.out.println(i);
				break;
			}
		}
	}
}