// https://www.acmicpc.net/problem/10815

/* HashSet, contains()로는 시간 초과가 발생해서
 * 이분탐색으로 풀이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumCard_10815 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> myCard = new ArrayList<>();
		
		String[] temp = br.readLine().split(" ");
		for (String s : temp) {
			myCard.add(Integer.parseInt(s));
		}
		
		Collections.sort(myCard);
		
		int M = Integer.parseInt(br.readLine());
		temp = br.readLine().split(" ");
		for (String s : temp) {
			int target = Integer.parseInt(s);

			int start = 0;
			int mid = myCard.size() / 2;
			int end = myCard.size() - 1;
			
			while (true) {
				if (target == myCard.get(mid)) {
					System.out.print("1 ");
					break;
				}
				
				if (start == mid) {
					System.out.print("0 ");
					break;
				}
				
				if (target > myCard.get(mid)) {
					start = mid;
					mid = (start + end + 1) / 2;
				}
				else if (target < myCard.get(mid)) {
					end = mid;
					mid = (start + end) / 2;
				}

			}
		}
	}
}
