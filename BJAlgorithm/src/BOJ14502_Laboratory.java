// https://www.acmicpc.net/problem/14502

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_Laboratory {
	
	static int R, C, vCnt;
	static int[][] map;
	static int[][] copyMap;
	static ArrayList<Point> emptyList;
	static ArrayList<Point> virusList;
	static int[] selected = new int[3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		copyMap = new int[R][C];
		emptyList = new ArrayList<>();	// 빈 칸의 위치 리스트
		virusList = new ArrayList<>();	// 바이러스 칸의 위치 리스트
		vCnt = 100_000;	// 총 바이러스 개수 (최소일 때를 담음)
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0)
					emptyList.add(new Point(r, c));
				else if (map[r][c] == 2)
					virusList.add(new Point(r, c));
			}
		}
		
		combi(0, 0);
		System.out.println(emptyList.size() + virusList.size() - vCnt - 3);	// -3 : 세울 벽
	}
	
	// 벽을 놓을 수 있는 조합을 모두 시도해본다
	static void combi(int index, int num) {
		
		// 조합이 완성되었을 시 직접 바이러스를 퍼뜨려 칸 수 확인
		if (index == 3) {
			int[] dy = { -1, 0, 1, 0 };
			int[] dx = { 0, 1, 0, -1 };
			
			for (int r = 0; r < R; r++)
				System.arraycopy(map[r], 0, copyMap[r], 0, C);	// 맵 복사
			
			for (int i = 0; i < 3; i++) {
				Point p = emptyList.get(selected[i]);	// 새 벽 세우기
				copyMap[p.y][p.x] = 1;
			}
			
			Queue<Point> queue = new LinkedList<>();
			for (int i = 0; i < virusList.size(); i++) {
				Point p = virusList.get(i);
				queue.offer(new Point(p.y, p.x));	// 바이러스의 위치를 받아 큐에 넣기
			}
			
			// 바이러스의 확산을 BFS로 처리
			int virusCnt = 0;
			while(!queue.isEmpty()) {
				virusCnt++;
				Point p = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if (ny >= R || ny < 0 || nx >= C || nx < 0 || copyMap[ny][nx] != 0)
						continue;
					
					copyMap[ny][nx] = 2;
					queue.offer(new Point(ny, nx));
				}
			}
			
			// 바이러스 확산이 가장 약했을 경우를 저장
			if (virusCnt < vCnt)
				vCnt = virusCnt;
			
			return;
		}
		
		// 조합 생성 부분
		for (int i = num; i < emptyList.size(); i++) {
			selected[index] = i;
			combi(index+1, i+1);
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