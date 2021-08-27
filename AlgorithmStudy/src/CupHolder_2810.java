// https://www.acmicpc.net/problem/2810

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CupHolder_2810 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		char[] line = in.readLine().toCharArray();
		int sCount = 0;
		int lCount = 0;
		int answer = 0;
		
		for (int i = 0; i < line.length; i++) {
			if (line[i] == 'S')
				sCount++;
			if (line[i] == 'L')
				lCount++;
		}
		
		answer = sCount + lCount/2 + 1;
		if (answer > N)
			answer = N;
		
		System.out.println(answer);
	}
}