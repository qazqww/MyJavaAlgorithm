// https://www.acmicpc.net/problem/1074

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1074_Z {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(getNum(r)*2 + getNum(c));
	}
	
	static int getNum(int num) {
		int modR = num % 2;
		int myR = num / 2;

		List<Integer> list = new ArrayList<>();
		while (true) {
			int logR = log2(myR);
			
			if (logR == -1)
				break;
			
			list.add(logR);
			myR -= Math.pow(2, logR);
		}
		
		int result = 0;
		for (int i : list) {
			result += Math.pow(2, (i + 1) * 2);
		}
		result += modR;
		
		return result;
	}
	
	static int log2(int num) {
		int result = (int)(Math.log10(num) / Math.log10(2));
		return result < 0 ? -1 : result;
	}
}