import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String first = in.readLine();
		String second = in.readLine();
		
		int[][] map = new int[first.length() + 1][second.length() + 1];
		for (int i = 1; i < first.length() + 1; i++) {
			for (int j = 1; j < second.length() + 1; j++) {
				if (first.charAt(i - 1) == second.charAt(j - 1)) {
					map[i][j] = map[i-1][j-1] + 1;
				}
				else {
					map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
				}
			}
		}
		
		for (int i = 0; i < first.length() + 1; i++) {
			for (int j = 0; j < second.length() + 1; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println(map[first.length()][second.length()]);
	}
}

/*
 * X	0	s	e	c	o	n	d
 * 0	
 * f		0	0	0	0	0	0
 * i		0	0	0	0	0	0
 * r		0	0	0	0	0	0
 * s		1	1	1	1	1	1
 * t							
 * 
 */

/*
 * X	0	s	u	c	c	e	s	s
 * 0	0	0	0	0	0	0	0	0
 * s	0	1	1	1	1	1	1	1	
 * s	0	1	1	1	1	1	2	2	
 * e	0	1	1	1	1	2	2	2	
 * s	0	1	1	1	1	2	3	3	
 * s									
 * 
 * 
 */

/*
 * X	0	s	u	c	c	e	s	s
 * 0	0	0	0	0	0	0	0	0
 * s	0	1	1	1	1	1	1	1	
 * s	0	1	1	1	1	1	2	2	
 * c	0	1	1	2	2	2	2	2	
 * c	0	1	1	2	3	3	3	3	
 * e	0	1	1	2	3	4	4	4	
 * 
 * 
 */