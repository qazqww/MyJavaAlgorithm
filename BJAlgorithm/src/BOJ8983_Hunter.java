// https://www.acmicpc.net/problem/8983

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ8983_Hunter {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int pointCnt = Integer.parseInt(st.nextToken());
		int animalCnt = Integer.parseInt(st.nextToken());
		int range = Integer.parseInt(st.nextToken());
		
		int[] points = new int[pointCnt];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < pointCnt; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		
		Map<Integer, ArrayList<Integer>> animalMap = new HashMap<>();
		
		for (int i = 0; i < animalCnt; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if (animalMap.containsKey(x))
				animalMap.get(x).add(y);
			else {
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(y);
				animalMap.put(x, temp);
			}
		}
		
		Arrays.sort(points);
		int answer = 0;
		
		for (int k : animalMap.keySet()) {
			int yRange = -1;
			if (k < points[0]) {
				yRange = range - (points[0] - k);
			}
			else if (k > points[points.length - 1]) {
				yRange = range - (k - points[points.length - 1]);
			}
			else {
				int left = 0;
				int right = points.length - 1;
				while (left < right) {
					if (right == left + 1)
						break;
					
					int mid = (left + right) / 2;
					if (points[mid] < k) {
						left = mid;
					}
					else {
						right = mid;
					}
				}
				
				yRange = range - Math.min(points[right] - k, k - points[left]);
			}
			
			for (int num : animalMap.get(k)) {
				if (num <= yRange) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
}

