// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_mSnmKUckDFAWb

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA3124_MinSpanningTree {

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			parent = new int[V+1];
			for (int i = 1; i <= V; i++)
				parent[i] = i;
			Edge[] edges = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(s, e, w);
			}

			Arrays.sort(edges);

			long value = 0;
			int cnt = 0;
			for (Edge e : edges) {
				int parentS = getParent(e.start);
				int parentE = getParent(e.end);
				if (parentS == parentE)
					continue;

				union(parentS, parentE);
				value += e.weight;
				cnt++;
				if (cnt == V - 1)
					break;
			}

			System.out.printf("#%d %d\n", t + 1, value);
		}
	}

	static void union(int v1, int v2) {
		if (getParent(v1) == getParent(v2))
			return;
		parent[v2] = v1;
	}

	static int getParent(int v) {
		if (parent[v] == v)
			return v;
		return parent[v] = getParent(parent[v]);
	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
}