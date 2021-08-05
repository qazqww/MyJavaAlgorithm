// https://www.acmicpc.net/problem/2493

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

// 타워의 위치와 높이를 담을 클래스
class Pair {
	int place;
	int height;
	
	public int getPlace() {
		return place;
	}
	public int getHeight() {
		return height;
	}
	
	public Pair(int place, int height) {
		this.place = place;
		this.height = height;
	}
}

public class Tower_2493 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Pair> stack = new Stack<>();			// 도착하지 않은 레이저의 타워를 임시적으로 담는 스택
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		int[] tower = new int[N];			// 높이 입력값을 받음
		int[] target = new int[N];			// 레이저가 도착한 위치의 값을 담음
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N-1; i >= 0; i--) {
			Pair now = new Pair(i, tower[i]);
			while (!stack.isEmpty() && now.getHeight() >= stack.peek().getHeight()) {	// 스택의 타워가 자신에게 닿는 경우
				Pair prev = stack.pop();		// 자신보다 높은 타워가 나올 때까지 pop
				target[prev.place] = i+1;		// 자신보다 낮은 타워의 결과값으로 자신의 위치를 입력
			}
			stack.push(now);					// 그 이후 스택에 자신을 추가
		}
		
		// 도착하지 못한 레이저 탑들은 결과값을 0으로
		while (!stack.isEmpty()) {
			Pair temp = stack.pop();
			target[temp.place] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(target[i] + " ");
		}
		out.write(sb.toString());
		out.flush();
	}
}
