// https://www.acmicpc.net/problem/12100

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ12100_2048Easy {
	
	static int N, max;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[r][c]);
			}
		}
		
		permu(0, map);
		System.out.println(max);
	}
	
	// 4방향으로 5자리를 채우는 중복순열
	static void permu(int cnt, int[][] map) {
		
		// 5번 이동했을 시 완료
		if (cnt == 5)
			return;

		int[][] copyMap = new int[N][N];
		int limit = -1; // 이미 합쳐진 수가 더 합쳐지지 않도록 자리수 체크
		
		// 위
		for (int r = 0; r < N; r++) // 맵 복사
			System.arraycopy(map[r], 0, copyMap[r], 0, N);
		
		for (int c = 0; c < N; c++) {
			limit = -1;
			ArrayList<Integer> nums = new ArrayList<>(); // 빈 칸이 아닌 수들을 담아가면서 합칠 수 있는 수는 합침
			for (int r = 0; r < N; r++) { // 한 열씩 체크
				
				if (copyMap[r][c] == 0)	// 빈 칸이라면 패스
					continue;
				
				// nums가 비어있지 않고 (합칠 대상이 있고) && 이미 합쳐진 수가 아니고 && 현재 위치의 수가 마지막으로 넣었던 수와 같다면
				if (!nums.isEmpty() && nums.size() != limit && copyMap[r][c] == nums.get(nums.size()-1)) {
					nums.set(nums.size() - 1, copyMap[r][c] * 2); // 마지막으로 넣은 수에 합쳐 2배로 만듦
					max = Math.max(max, copyMap[r][c] * 2); // 합친 값이 최대값이라면 갱신
					limit = nums.size(); // 마지막으로 넣은 칸이 합쳐지지 않도록 체크
				}
				
				// 그 외의 경우
				else {
					nums.add(copyMap[r][c]); // 그냥 수를 넣는다
				}
				
				// 수를 넣었다면 그 자리는 일단 비워둔다. 
				copyMap[r][c] = 0;
			}
			
			// 위부터 차례로 채워나간다.
			for (int i = 0; i < nums.size(); i++)
				copyMap[i][c] = nums.get(i);
		}
		permu(cnt+1, copyMap); // 다음 단계 진행
		
		// 밑
		for (int r = 0; r < N; r++)
			System.arraycopy(map[r], 0, copyMap[r], 0, N);
		
		for (int c = 0; c < N; c++) {
			limit = -1;
			ArrayList<Integer> nums = new ArrayList<>();
			for (int r = N-1; r >= 0; r--) {
				if (copyMap[r][c] == 0)
					continue;
				
				if (!nums.isEmpty() && nums.size() != limit && copyMap[r][c] == nums.get(nums.size()-1)) {
					nums.set(nums.size() - 1, copyMap[r][c] * 2);
					max = Math.max(max, copyMap[r][c] * 2);
					limit = nums.size();
				}
				else {
					nums.add(copyMap[r][c]);
				}
				copyMap[r][c] = 0;
			}
			
			for (int i = 0; i < nums.size(); i++)
				copyMap[N-1-i][c] = nums.get(i);
		}
		permu(cnt+1, copyMap);
		
		// 왼쪽
		for (int r = 0; r < N; r++)
			System.arraycopy(map[r], 0, copyMap[r], 0, N);
		
		for (int r = 0; r < N; r++) {
			limit = -1;
			ArrayList<Integer> nums = new ArrayList<>();
			for (int c = 0; c < N; c++) {
				if (copyMap[r][c] == 0)
					continue;
				
				if (!nums.isEmpty() && nums.size() != limit && copyMap[r][c] == nums.get(nums.size()-1)) {
					nums.set(nums.size() - 1, copyMap[r][c] * 2);
					max = Math.max(max, copyMap[r][c] * 2);
					limit = nums.size();
				}
				else {
					nums.add(copyMap[r][c]);
				}
				copyMap[r][c] = 0;
			}
			
			for (int i = 0; i < nums.size(); i++)
				copyMap[r][i] = nums.get(i);
		}
		permu(cnt+1, copyMap);
		
		// 오른쪽
		for (int r = 0; r < N; r++)
			System.arraycopy(map[r], 0, copyMap[r], 0, N);
		
		for (int r = 0; r < N; r++) {
			limit = -1;
			ArrayList<Integer> nums = new ArrayList<>();
			for (int c = N-1; c >= 0; c--) {
				if (copyMap[r][c] == 0)
					continue;
				
				if (!nums.isEmpty() && nums.size() != limit && copyMap[r][c] == nums.get(nums.size()-1)) {
					nums.set(nums.size() - 1, copyMap[r][c] * 2);
					max = Math.max(max, copyMap[r][c] * 2);
					limit = nums.size();
				}
				else {
					nums.add(copyMap[r][c]);
				}
				copyMap[r][c] = 0;
			}
			
			for (int i = 0; i < nums.size(); i++)
				copyMap[r][N-1-i] = nums.get(i);
		}
		permu(cnt+1, copyMap);
	}
}