// https://www.acmicpc.net/problem/10158

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ant_10158 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split(" ");
		int lenX = Integer.parseInt(str[0]);
		int lenY = Integer.parseInt(str[1]);
		str = in.readLine().split(" ");
		int x = Integer.parseInt(str[0]);
		int y = Integer.parseInt(str[1]);
		int time = Integer.parseInt(in.readLine());
		
		// 남은 이동량 : len * 2 시간 후면 제자리에 오므로, len * 2로 나눈 나머지만 계산
		int moveX = time % (lenX * 2);
		int moveY = time % (lenY * 2);

		// 현재 위치와 벽 사이의 거리를 구하여 3가지 경우의 수를 찾아냄
		int gap = lenX - x;
		if (moveX <= gap) {	// 걸음 수가 모자라 반사되지 않는 경우
			x += moveX;
		}
		else if (moveX > gap + lenX) {	// 2번 반사된 경우
			x = moveX - (lenX + gap);
		}
		else {	// 1번 반사되어 역으로 걸어가다 멈춘 경우
			x = lenX + gap - moveX;
		}
		
		gap = lenY - y;
		if (moveY <= gap) {
			y += moveY;
		}
		else if (moveY > gap + lenY) {
			y = moveY - (lenY + gap);
		}
		else {
			y = lenY + gap - moveY;
		}
		
		System.out.println(x + " " + y);
	}
}

/*
while (true) {
	
	if (dx > 0 && dy > 0) {	// 우상 방향 이동
		move = Math.min(lenY - y, lenX - x);
		x += move;
		y += move;
	}
	else if (dx > 0 && dy < 0) {	// 우하 방향 이동
		move = Math.min(y, lenX - x);
		x += move;
		y -= move;
	}
	else if (dx < 0 && dy < 0) {	// 좌하 방향 이동
		move = Math.min(x, y);
		x -= move;
		y -= move;
	}
	else if (dx < 0 && dy > 0) {	// 좌상 방향 이동
		move = Math.min(lenY - y, x);
		x -= move;
		y += move;
	}

	count += move;
	if (count > time) {
		int gap = count - time;
		x -= dx * gap;
		y -= dy * gap;
		break;
	}
	
	if (x == 0 && y == 0 || x == lenX && y == 0 || x == 0 && y == lenY || x == lenX && y == lenY) {
		dx *= -1;
		dy *= -1;
	}
	else if (x == 0 || x == lenX)
		dx *= -1;
	else if (y == 0 || y == lenY)
		dy *= -1;
}
*/