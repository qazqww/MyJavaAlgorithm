// https://www.acmicpc.net/problem/10989

/* [issue] 메모리 초과
 * 합병정렬은 메모리를 2배로 잡아먹기 때문에 뭔 짓을 해도 메모리 초과가 발생
 * Collections.sort()와 BufferedI/O로는 해결 불가
 * 
 * => 카운팅하여 순서대로 출력하는 방법을 사용
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SortNum3_10989 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N  = Integer.parseInt(br.readLine());
		
		int[] count = new int[10001];	// 1 ~ 10000이 나온 개수를 카운트
		
		for (int i = 0; i < N; i++) {
			count[ Integer.parseInt(br.readLine()) ]++;
		}
		
		for (int i = 1; i < 10001; i++) {
			for (int j = count[i]; j > 0; j--) {	// count[i]만큼 i를 출력
				bw.write(i + "\n");
			}
		}
		bw.flush();
	}
}