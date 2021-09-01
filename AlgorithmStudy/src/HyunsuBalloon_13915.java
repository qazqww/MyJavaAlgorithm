// https://www.acmicpc.net/problem/13915

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HyunsuBalloon_13915 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		bitmasking();
		//setset();
	}
	
	// 비트마스킹으로 풀이
	static void bitmasking() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String str = in.readLine();
			if (str == null || str.isEmpty())
				break;
			
			int N = Integer.parseInt(str);		// 수강생 수
			Set<Integer> set = new HashSet<>(); // 전체 수강생의 숙련도를 담을 set
			
			for (int i = 0; i < N; i++) {
				String train = in.readLine();	// 탑승 열기구 목록
				int num = 0;	// 숙련도를 나타낼 숫자
				
				for (int j = 0; j < train.length(); j++) {
					num |= 1 << (train.charAt(j) - '0');	// 탑승한 열기구에 해당하는 비트를 1로 체크
				}
				
				set.add(num);	// set에 숙련도를 등록
			}
			
			System.out.println(set.size());	// set에 담긴 숙련도의 개수가 곧 답이 된다
		}
	}
	
	// Set<Set<>> 으로 풀이
	static void setset() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String str = in.readLine();
			if (str == null || str.isEmpty())
				break;
			
			int N = Integer.parseInt(str);
			
			Set<Set<Integer>> last = new HashSet<>();	// 전체 수강생의 숙련도를 담는 set
			
			for (int i = 0; i < N; i++) {
				Set<Integer> set = new HashSet<>();	// 개인의 숙련도를 담는 set
				
				String flights = in.readLine();
				for (int j = 0; j < flights.length(); j++) {
					int num = flights.charAt(j) - '0';	// 탑승한 열기구를
					set.add(num);						// set에 추가
				}
				
				last.add(set);
			}
			
			System.out.println(last.size());
		}
	}
}