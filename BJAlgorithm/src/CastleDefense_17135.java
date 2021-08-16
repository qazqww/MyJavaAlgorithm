// https://www.acmicpc.net/problem/17135
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CastleDefense_17135 {
	
	static int yLen, xLen, range;
	static int firstEnemy;					// 적이 존재하는 가장 윗 줄
	static int totalKilled = 0;
	static int[][] map;
	static int[][] copyMap;
	static List<MyPlace> enemyPlace;		// 적들의 위치를 담는 리스트
	static int[] archer = new int[3];		// 궁수들 위치 조합에서 x좌표만 담음
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		yLen = Integer.parseInt(st.nextToken());
		xLen = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		map = new int[yLen][xLen];
		copyMap = new int[yLen][xLen];
		
		firstEnemy = -1;
		
		enemyPlace = new ArrayList<>();
		
		// 맵 입력
		for (int y = 0; y < yLen; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < xLen; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1) {
					if (firstEnemy == -1)
						firstEnemy = y;
					enemyPlace.add(new MyPlace(y, x));
				}
			}
		}
		
		Combi(0, 0);
		System.out.println(totalKilled);
	}
	
	static void Combi(int index, int num) {
		if (index == 3) {
			// 궁수 위치 : (yLen, archer[index])
			
			int killCount = 0;
			int nowRange = range;							// 사거리 복사
			List<MyPlace> nowEnemy = new ArrayList<>();		// 적들의 위치 복사
			for (MyPlace p : enemyPlace)
				nowEnemy.add(p);
			
			for (int y = 0; y < yLen; y++) {				// 맵 복사
				System.arraycopy(map[y], 0, copyMap[y], 0, xLen);
			}
			
			// 가장 윗 쪽의 적이 성에 도달할 때까지만 계산
			for (int turn = 0; turn < yLen - firstEnemy; turn++) {
				
				int[] destroyed = new int[] {-1, -1, -1};	// 공격받은 적을 담아두는 배열 -> 모든 궁수의 공격 후 한꺼번에 삭제
				
				// 궁수 0부터 차례로 공격
				for (int a = 0; a < 3; a++) {
					int min = Integer.MAX_VALUE;	// 가장 가까운 적과의 사거리
					int enemyIndex = 0;				// 적 리스트에서 현재 죽일 적을 담을 인덱스
					int xTemp = -1;					// 같은 거리일 경우 왼쪽부터 공격하므로, 적의 x위치를 담아둠
					
					// 가장 가까운 적 체크
					for (int e = 0; e < nowEnemy.size(); e++) {
						int dist = Math.abs(yLen - nowEnemy.get(e).y) + Math.abs(archer[a] - nowEnemy.get(e).x);	// 거리 계산
						
						// 거리가 더 작거나 혹은 거리가 같고 적이 더 왼쪽에 존재 => 맞출 적 갱신
						if (dist < min || (dist == min && nowEnemy.get(e).x < xTemp)) {
							min = dist;
							enemyIndex = e;
							xTemp = nowEnemy.get(e).x;
							if (min == 1)	// 적이 코앞일 경우 나머지 계산은 필요가 없으므로 생략
								break;
						}
					}
					
					if (min <= nowRange)	// 적이 사거리 내에 존재할 시 공격
						destroyed[a] = enemyIndex;
				}
				
				Arrays.sort(destroyed);		// 죽은 적은 리스트에서 삭제해야 하므로 index가 꼬이지 않게 리스트의 뒤에서부터 처리
				
				for (int i = 2; i >= 0; i--) {
					if (destroyed[i] == -1)		// 공격할 적이 더 이상 없는 경우 break
						break;
					if (i > 0 && destroyed[i] == destroyed[i-1])	// 다음 공격받는 적과 중복될 경우 continue
						continue;
					
					MyPlace d = nowEnemy.get(destroyed[i]);
					nowEnemy.remove(destroyed[i]);
					copyMap[d.y][d.x] = 0;
					killCount++;
				}
				
				// 뒤처리
				// 적이 다가오는 대신, 궁수의 사정거리가 1씩 늘어난다고 가정
				// 적이 성으로 들어오는 대신, 맨 밑에서부터 한 줄씩 남은 적들을 삭제한다
				nowRange++;
				for (int x = 0; x < xLen; x++)
					copyMap[yLen - 1 - turn][x] = 0;
				for (int e = nowEnemy.size() - 1; e >= 0; e--) {
					if (nowEnemy.get(e).y == yLen - 1 - turn)
						nowEnemy.remove(e);
				}
			}
			
			if (killCount > totalKilled)
				totalKilled = killCount;
			
			return;
		}
		
		for (int i = num; i < xLen; i++) {
			archer[index] = i;
			Combi(index+1, i+1);
		}
	}
}

class MyPlace {
	public int x, y;
	
	public MyPlace(int y, int x) {
		this.x = x;
		this.y = y;
	}
}