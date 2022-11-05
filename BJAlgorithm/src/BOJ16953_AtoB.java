import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16953_AtoB {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		if (A == B) {
			System.out.println(1);
			return;
		}
		
		Queue<long[]> queue = new LinkedList<>();
		queue.offer(new long[] { A, 1 });
		
		while (!queue.isEmpty()) {
			long[] arr = queue.poll();
			long num = arr[0];
			long cnt = arr[1];
			
			long newNum = num * 2;
			if (newNum == B) {
				System.out.println(cnt + 1);
				return;
			}
			else if (newNum < B) {
				queue.offer(new long[] { newNum, cnt + 1 });
			}
			
			newNum = num * 10 + 1;
			if (newNum == B) {
				System.out.println(cnt + 1);
				return;
			}
			else if (newNum < B) {
				queue.offer(new long[] { newNum, cnt + 1 });
			}
		}
		
		System.out.println(-1);
	}
}