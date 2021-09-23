// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=99&sfl=wr_hit&stx=1681

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1681_Hamilton {
	
	static int N, dist;
	static int min = 100_000_000;
	static int[][] map;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isSelected[0] = true;
		permu(1, 0);
		System.out.println(min);
	}
	
	static void permu(int index, int pre) {

		if (index == N) {
			if (map[pre][0] == 0)
				return;
			
			dist += map[pre][0];
			min = Math.min(dist, min);
			dist -= map[pre][0];
			return;
		}
		
		for (int i = 1; i < N; i++) {
			if (isSelected[i] || map[pre][i] == 0)
				continue;
			
			dist += map[pre][i];
			isSelected[i] = true;

			if (dist < min)
				permu(index+1, i);
			
			dist -= map[pre][i];
			isSelected[i] = false;
		}
	}
}