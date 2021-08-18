// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=99&sfl=wr_hit&stx=1828

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Fridge_1828 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<int[]> substance = new ArrayList<>();		// int[] => { 낮은 온도, 높은 온도 }
		for (int i = 0; i < N; i++) {
			substance.add(new int[] { sc.nextInt(), sc.nextInt() });
		}
		
		// 최고 온도는 낮은 순, 같으면 최저 온도가 낮은 순으로 정렬
		Collections.sort(substance, (int[] s1, int[] s2) -> {
			if (s1[1] == s2[1])
				return s1[0]-s2[0];
			return s1[1]-s2[1];
		});
		
		int fridge = 0;
		int tem = -271;		// 비교 기준이 되는 온도
		for (int[] s : substance) {
			if (s[0] > tem) {		// 기준 온도 (기존 물질의 최고온도)보다 새 물질의 최저온도가 높으면 갱신
				tem = s[1];			// 새 기준 물질의 최고온도를 기준 온도로 설정
				fridge++;			// 새로 기준 온도를 설정할 때마다 냉장고 하나 추가
			}
		}
		
		System.out.println(fridge);
	}
}