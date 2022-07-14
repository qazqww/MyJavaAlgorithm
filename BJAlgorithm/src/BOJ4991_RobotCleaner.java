// https://www.acmicpc.net/problem/4991

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991_RobotCleaner {
	
	static int answer, totalDist;
	static ArrayList<Point> points;
	static int[][] dists;
	static int[] order;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			if (R == 0 && C == 0)
				break;
			
			int[] dy = new int[] { -1, 0, 1, 0 };
			int[] dx = new int[] { 0, 1, 0, -1 };
			
			answer = Integer.MAX_VALUE;
			char[][] map = new char[R][C];
			points = new ArrayList<>();
			points.add(new Point(-1, -1));
			
			int cnt = 1;
			for (int r = 0; r < R; r++) {
				String temp = in.readLine();
				map[r] = temp.toCharArray();
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '*') {
						points.add(new Point(r, c));
						map[r][c] = (char)('0' + cnt++);
					}
					else if (map[r][c] == 'o')
						points.set(0, new Point(r, c));
				}
			}
			
			int psize = points.size();
			order = new int[psize];
			selected = new boolean[psize];

			boolean[][] visited = null;
			dists = new int[psize][psize];
			for (int i = 0; i < psize; i++) {
				Arrays.fill(dists[i], 1_000_000);
				dists[i][i] = 0;
			}
			
			for (int i = 0; i < points.size(); i++) {

				Queue<Point> queue = new LinkedList<>();
				visited = new boolean[R][C];
				
				queue.offer(points.get(i));
				visited[points.get(i).y][points.get(i).x] = true;
				int dist = 0;
				cnt = (i == 0) ? 0 : 1;
				
				loop: while(!queue.isEmpty()) {
					dist++;
					int qsize = queue.size();
					
					for (int j = 0; j < qsize; j++) {
						Point p = queue.poll();
						for (int d = 0; d < 4; d++) {
							int ny = p.y + dy[d];
							int nx = p.x + dx[d];
							
							if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx] || map[ny][nx] == 'x')
								continue;
							
							if (map[ny][nx] >= '0' && map[ny][nx] <= '9') {
								dists[i][map[ny][nx] - '0'] = dist;
								dists[map[ny][nx] - '0'][i] = dist;
								cnt++;
							}
							
							if (cnt > points.size() - 1)
								break loop;
							
							visited[ny][nx] = true;
							queue.offer(new Point(ny, nx));
						}
					}
				}
			}
			
			permu(0);
			System.out.println(answer >= 1_000_000 ? -1 : answer);
		}
	}
	
	static void permu(int n) {
		
		if (n == points.size()) {
			answer = Math.min(answer, totalDist);
			return;
		}
		
		for (int i = 0; i < points.size(); i++) {
			
			if (selected[i])
				continue;
			
			selected[i] = true;
			order[n] = i;
			
			if (n == 0)
				totalDist += dists[0][order[0]];
			else
				totalDist += dists[order[n-1]][order[n]];
			
			if (totalDist < answer)
				permu(n+1);
			
			if (n == 0)
				totalDist -= dists[0][order[0]];
			else
				totalDist -= dists[order[n-1]][order[n]];
			
			selected[i] = false;
		}
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}