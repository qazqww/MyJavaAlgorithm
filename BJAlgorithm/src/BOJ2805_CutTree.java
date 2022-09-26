import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2805_CutTree {
	
	static Integer[] trees;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int max = -1;
		trees = new Integer[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}
		
		int low = 0;
		int high = max;
		while (low < high) {
			int mid = (low + high) / 2;
			long sum = 0;
			
			for (int i = 0; i < trees.length; i++) {
				if (trees[i] > mid)
					sum += trees[i] - mid;
			}
			
			if (sum < M)
				high = mid;
			else
				low = mid + 1;
				
		}
		
		System.out.println(low - 1);
	}
}
