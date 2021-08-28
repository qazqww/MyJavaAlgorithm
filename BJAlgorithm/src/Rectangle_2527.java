// https://www.acmicpc.net/problem/2527

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rectangle_2527 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int sx1 = Integer.parseInt(st.nextToken());
			int sy1 = Integer.parseInt(st.nextToken());
			int ex1 = Integer.parseInt(st.nextToken());
			int ey1 = Integer.parseInt(st.nextToken());
			int sx2 = Integer.parseInt(st.nextToken());
			int sy2 = Integer.parseInt(st.nextToken());
			int ex2 = Integer.parseInt(st.nextToken());
			int ey2 = Integer.parseInt(st.nextToken());
			
			boolean hasSameX = ((sx2 <= sx1 && ex2 > sx1) || (sx2 >= sx1 && ex2 <= ex1) || (sx2 < ex1 && ex2 >= ex1)	// 겹치는 X부분이 있는가
					|| (sx1 <= sx2 && ex1 > sx2) || (sx1 >= sx2 && ex1 <= ex2) || (sx1 < ex2 && ex1 >= ex2));
			boolean hasSameY = ((sy2 <= sy1 && ey2 > sy1) || (sy2 >= sy1 && ey2 <= ey1) || (sy2 < ey1 && ey2 >= ey1)	// 겹치는 Y부분이 있는가
					|| (sy1 <= sy2 && ey1 > sy2) || (sy1 >= sy2 && ey1 <= ey2) || (sy1 < ey2 && ey1 >= ey2));
			
			if ((sx1 == ex2 && ey1 == sy2)
					|| (ex1 == sx2 && ey1 == sy2)
					|| (ex1 == sx2 && sy1 == ey2)
					|| (sx1 == ex2 && sy1 == ey2)
					) {
				System.out.println("c");
			}
			else if ((hasSameX && (sy1 == ey2 || sy2 == ey1)) || (hasSameY && (sx1 == ex2 || sx2 == ex1))) {
				System.out.println("b");
			}
			else if (hasSameX && hasSameY) {
				System.out.println("a");
			}
			else {
				System.out.println("d");
			}
					
		}
	}
}