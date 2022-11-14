// https://www.acmicpc.net/problem/5525

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ5525_IOIOI {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		String S = in.readLine();
		
		List<Integer> lens = new ArrayList<>();
		int curLen = 0;
		int answer = 0;
		
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == 'I') {
				if (i < S.length() - 2 && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'I') {
					curLen++;
					i++;
				}
				else {
					if (curLen > 0) {
						lens.add(curLen);
						curLen = 0;
					}
				}
			}
		}
		
		lens.add(curLen);
		for (int len : lens) {
			if (len >= N) {
				answer += len - N + 1;
			}
		}
		
		System.out.println(answer);
	}
}