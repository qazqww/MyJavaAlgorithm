import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236_BabyShark {
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		boolean[][] visited;
		
		int[] dy = new int[] { -1, 0, 0, 1 };
		int[] dx = new int[] { 0, -1, 1, 0 };
		
		int sharkY = -1;
		int sharkX = -1;
		int size = 2;
		int stack = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					map[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<Point> pq;
		int time = 0;
		
		boolean isEnd = false;
		
		while (!isEnd) {
			pq = new PriorityQueue<>();
			pq.offer(new Point(sharkY, sharkX, 0));
			visited = new boolean[N][N];
			visited[sharkY][sharkX] = true;
			int dist = -1;
			isEnd = true;
			
			loop: while (!pq.isEmpty()) {
				dist++;
				int cnt = pq.size();
				
				for (int i = 0; i < cnt; i++) {
					Point p = pq.poll();
					
					if (map[p.y][p.x] > 0 && map[p.y][p.x] < size) {
						sharkY = p.y;
						sharkX = p.x;
						time += dist;
						
						map[p.y][p.x] = 0;
						isEnd = false;
						
						stack++;
						if (stack == size) {
							size++;
							stack = 0;
						}
						
						break loop;
					}
					
					for (int d = 0; d < 4; d++) {
						int ny = p.y + dy[d];
						int nx = p.x + dx[d];
						
						if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx] || map[ny][nx] > size) {
							continue;
						}
						
						pq.offer(new Point(ny, nx, dist));
						visited[ny][nx] = true;
					}
				}
			}
		}
		
		System.out.println(time);
		
	}
	
	static class Point implements Comparable<Point> {
		int y;
		int x;
		int dist;
		
		public Point(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			if (dist != o.dist)
				return dist > o.dist ? 1 : -1;
			else if (y != o.y)
				return y > o.y ? 1 : -1;
			return x > o.x ? 1 : -1;
		}
	}
}
