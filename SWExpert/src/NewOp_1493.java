// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b-QGqADMBBASw

import java.util.ArrayList;
import java.util.Scanner;

public class NewOp_1493 {

	// 좌표는 (y, x) 로 표시하고 있습니다
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		int num = 0;
		int index = 1;
		ArrayList<Integer> xList = new ArrayList<>();
		xList.add(0);	// index 1부터 활용
		
		// (1, x) 의 값 구하기
		// (1, x) 의 값은 1부터 x까지 수의 합이다.
		// (1, 141) 의 값은 10011
		// p, q가 10000까지 주어지므로 가능한 최대 x좌표 : 대략 140즘
		// p좌표 + q좌표 => 결과값의 x좌표가 280대까지 가능
		while (xList.size() < 284) {
			num += index;
			xList.add(num);		// xList.get(i) = 1 ~ i의 합
			index++;
		}
		
		for (int t = 0; t < T; t++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			/* & 연산 : 수에 해당하는 좌표 구하기
			 * 어떤 대각선 라인에 해당하는지 y = 1일때 x값을 구하고
			 * 대각선으로 나아가면서 좌표를 구함
			 */
			int xIndex1 = 1;	// 어떤 x좌표에서 시작하면 되는지
			for (; xIndex1 < xList.size(); xIndex1++) {
				if (num1 > xList.get(xIndex1 - 1) && num1 <= xList.get(xIndex1)) {
					break;
				}
			}			
			int temp = xList.get(xIndex1);
			int n1 = 0;			// 얼마나 올라가면 되는지
			while (temp != num1) {
				temp--;
				n1++;
			}
			
			int xIndex2 = 1;
			for (; xIndex2 < xList.size(); xIndex2++) {
				if (num2 > xList.get(xIndex2 - 1) && num2 <= xList.get(xIndex2)) {
					break;
				}
			}			
			temp = xList.get(xIndex2);
			int n2 = 0;
			while (temp != num2) {
				temp--;
				n2++;
			}
			
			// num의 좌표는 x : xIndex - n, y : 1 + n
			// 구할 숫자의 좌표 (y, x)는 (n1 + 1 + n2 + 1, xIndex1 - n1 + xIndex2 - n2)
			
			/* # 연산 : 좌표에 할당된 수 구하기
			 * (1, x) => 1 ~ x 의 합
			 * (y, x) => (y - n, x + n) - n
			 * n이 y-1 일때, (1, x + y - 1) - (y - 1)
			 * x 인덱스가 x좌표값 + y좌표값 - 1 인 곳의 숫자에서 y좌표값-1 만큼 빼면 숫자를 구할 수 있다
			 */

			int answer = xList.get(xIndex1 + xIndex2 + 1) - (n1 + n2 + 1);
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}