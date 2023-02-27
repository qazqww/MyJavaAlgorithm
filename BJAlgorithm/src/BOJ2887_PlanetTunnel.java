// https://www.acmicpc.net/problem/2887

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2887_PlanetTunnel {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		Planet[] planets = new Planet[N];
		int[] minDist = new int[N];
		boolean[] visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			planets[i] = new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		PriorityQueue<int[]> distPq = new PriorityQueue<>((a, b) -> a[1] - b[1]); 
		for (int i = 1; i < N; i++) {
			int dist = planets[0].getDist(planets[i]);
			minDist[i] = dist;
			distPq.offer(new int[] { i, dist });
		}
		
		visited[0] = true;
		
		int answer = 0;
		loop: for (int i = 0; i < N; i++) {
			int[] next = distPq.poll();
			while (visited[next[0]]) {
				if (distPq.isEmpty())
					break loop;
				
				next = distPq.poll();
			}
			
			answer += next[1];
			minDist[next[0]] = next[1];
			visited[next[0]] = true;
			
			for (int j = 0; j < N; j++) {
				if (visited[j])
					continue;
				
				int newDist = planets[next[0]].getDist(planets[j]);
				if (newDist < minDist[j]) {
					minDist[j] = newDist;
					distPq.offer(new int[] { j, newDist });
				}
			}
		}
		
		System.out.println(answer);
	}

	static class Planet {
		int x;
		int y;
		int z;
		public Planet(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		private int getDist(Planet other) {
			return Math.min(Math.min(Math.abs(x - other.x), Math.abs(y - other.y)), Math.abs(z - other.z));
		}
	}
}