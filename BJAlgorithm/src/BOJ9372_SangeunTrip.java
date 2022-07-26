import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9372_SangeunTrip {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			for (int m = 0; m < M; m++)
				in.readLine();
			
			System.out.println(N-1);
		}
	}
}