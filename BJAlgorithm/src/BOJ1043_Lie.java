// https://www.acmicpc.net/problem/1043

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1043_Lie {
	
	static int[] parent;
	static int root = -1; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// 사람 수, 파티 수 입력받기
		int personNum = Integer.parseInt(st.nextToken());
		int partyNum = Integer.parseInt(st.nextToken());
		parent = new int[personNum+1]; // Union-Find를 위해 자신의 부모를 담는 배열 생성
		for (int i = 1; i < personNum+1; i++)
			parent[i] = i; // 자기 자신으로 부모를 설정
		
		// 진실을 아는 사람 정보 입력받기
		st = new StringTokenizer(in.readLine());
		int truthNum = Integer.parseInt(st.nextToken());
		if (truthNum > 0)
			root = Integer.parseInt(st.nextToken()); // 기준으로 삼을, 진실을 아는 사람 (기준 사람)
		for (int i = 2; i < truthNum+1; i++) {
			int temp = Integer.parseInt(st.nextToken());
			parent[temp] = root; // 진실을 아는 사람들의 부모를 기준 사람으로 설정
		}
		
		// 파티 정보 입력받기
		ArrayList<Integer>[] partyList = new ArrayList[partyNum]; // 각 파티의 참여자를 담음
		for (int i = 0; i < partyNum; i++) {
			partyList[i] = new ArrayList<>();
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()); // 파티 참여자 수
			int first = Integer.parseInt(st.nextToken()); // union의 비교 기준이 될 사람 (편의를 위해 설정)
			partyList[i].add(first);
			
			for (int j = 2; j < num+1; j++) {
				int temp = Integer.parseInt(st.nextToken());
				partyList[i].add(temp);
				union(first, temp);
			}
		}
		
		// 파티별로 진실을 아는 사람과 이어진 사람이 있는지 체크
		int answer = 0;
		loop: for (int i = 0; i < partyNum; i++) {
			for (int j = 0; j < partyList[i].size(); j++) {
				if (getParent(partyList[i].get(j)) == root) // 진실을 아는 사람과 부모가 같다면
					continue loop; // 해당 파티는 패스
			}
			answer++; // 그렇지 않으면 거짓말을 할 수 있는 파티에 추가
		}
		
		System.out.println(answer);
		
	}
	
	static int getParent(int num) {
		if (num == parent[num])
			return num;
		return parent[num] = getParent(parent[num]);
	}
	
	static void union(int a, int b) {
		int aRoot = getParent(a);
		int bRoot = getParent(b);
		if (aRoot == bRoot)
			return;
		if (aRoot == root) // root를 보존하기 위해 따로 설정
			parent[bRoot] = root;
		else
			parent[aRoot] = parent[bRoot];
	}
}