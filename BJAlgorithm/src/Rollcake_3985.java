// https://www.acmicpc.net/problem/3985

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rollcake_3985 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(in.readLine());
		int N = Integer.parseInt(in.readLine());
		
		int[] start = new int[N];			// (index)번 방청객이 원하는 시작 조각
		int[] len = new int[N];				// (index)번 방청객이 원하는 케이크 길이
		int[] gotCake = new int[N];			// (index)번 방청객이 실제로 얻은 케이크 조각
		boolean[] cake = new boolean[L];	// 케이크의 상태 (true => 선점)
		
		// 원하는 케이크 입력받기
		for (int i = 0; i < N; i++) {
			String[] temp = in.readLine().split(" ");
			start[i] = Integer.parseInt(temp[0]) - 1;
			int end = Integer.parseInt(temp[1]) - 1;
			len[i] = end - start[i] + 1;
		}
		
		// 실제로 받는 케이크 계산
		for (int i = 0; i < N; i++) {	// 1번 방청객부터 순서대로 체크
			for (int j = start[i]; j < start[i] + len[i]; j++) {	// 원했던 시작 조각부터 (길이)만큼 체크
				if (!cake[j]) {			// 이미 선점당하지 않았다면
					gotCake[i]++;		// 케이크 획득!
					cake[j] = true;		// 가져갔으므로 선점 표시를 해둔다
				}
			}
		}
		
		int preMax = 0;			// 예상한 가장 많은 조각 (원하는 케이크 길이가 가장 길었던 방청객)
		int prePerson = -1;		// 을 가진 방청객 번호
		int realMax = 0;		// 실제로 가장 많은 조각
		int realPerson = -1;	// 을 가진 방청객 번호
		for (int i = 0; i < N; i++) {
			if (len[i] > preMax) {
				preMax = len[i];
				prePerson = i;
			}
			if (gotCake[i] > realMax) {
				realMax = gotCake[i];
				realPerson = i;
			}
		}
		
		System.out.printf("%d\n%d\n", prePerson+1, realPerson+1);
	}
}