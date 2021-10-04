// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWajgCUaaAkDFAWM

import java.util.Scanner;

public class SWEA6026_SungsuPWAttack {
	
	static int p = 1_000_000_007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
				
		for (int t = 0; t < T; t++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			
			long answer = 0;
			for (int i = M; i > 0; i--) {
				if ((M - i) % 2 == 0)
					answer += power(i, N) % p * combi(M, i) % p;
				else
					answer -= power(i, N) % p * combi(M, i) % p;
				answer += p;
				answer %= p;
			}
			System.out.printf("#%d %d\n", t+1, answer % p);
		}
	}
	
	static long combi(int n, int r) {
		// n! * (r! * (n-r)! % p)^(p-2)
		return factorial(n) * power(factorial(r), p-2) % p * power(factorial(n-r), p-2) % p;
//		return factorial(n) % p / (factorial(r) % p * factorial(n-r) % p); 
	}
	
	static long factorial(int n) {
		long num = 1;
		for (int i = 2; i <= n; i++) {
			num *= i;
			num %= p;
		}
		return num;
	}
	
	static long power(long n, int pow) {
		if (pow == 1)
			return n;
		long temp = power(n, pow / 2) % p;
		if (pow % 2 == 1) {
			return temp * temp % p * n % p;
		}
		else {
			return temp * temp % p;
		}
	}
}