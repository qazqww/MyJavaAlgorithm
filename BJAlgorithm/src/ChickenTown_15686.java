//https://www.acmicpc.net/problem/15686

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenTown_15686 {

	static int chikNum, houseNum;				// 치킨집과 가정집 개수
	static int totalChik;						// 남겨질 치킨집 개수
	static int finalDist = Integer.MAX_VALUE;	// 가장 작은 도시의 치킨 거리 (answer)
	static int[] chikCombi;						// 남겨질 치킨집 조합
	static List<Place> chikPlace;				// 치킨집의 위치 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = in.readLine().split(" ");
		int size = Integer.parseInt(temp[0]);			// 맵의 크기
		totalChik = Integer.parseInt(temp[1]);
		
		chikCombi = new int[totalChik];
		chikPlace = new ArrayList<>();
		List<Place> housePlace = new ArrayList<>();		// 가정집 위치 리스트
		int[][] map = new int[size][size];
		
		// 맵 입력
		for (int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int x = 0; x < size; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				if (map[y][x] == 1) {
					housePlace.add(new Place(y, x));
					houseNum++;
				}
				else if (map[y][x] == 2) {
					chikPlace.add(new Place(y, x));
					chikNum++;
				}
			}
		}
		
		// 치킨집은 dists에 각 가정집과의 거리를 담는다. (dists[i] : i번째 집과의 거리)
		for (int i = 0; i < chikPlace.size(); i++) {
			chikPlace.get(i).dists = new int[houseNum];
		}
		
		// 치킨집 별로 각 가정집과의 거리를 구한다.
		for (int ck = 0; ck < chikNum; ck++) {
			Place ckTemp = chikPlace.get(ck);
			for (int h = 0; h < houseNum; h++) {
				Place hTemp = housePlace.get(h);
				int dist = Math.abs(ckTemp.y - hTemp.y) + Math.abs(ckTemp.x - hTemp.x);
				chikPlace.get(ck).dists[h] = dist;
			}
		}
		
		Combi(0, 0);
		System.out.println(finalDist);
	}
	
	// 총 치킨집 개수 (chikNum)에서 남겨질 치킨집 개수 (totalChik)를 뽑아내는 조합
	static void Combi(int index, int num) {
		if (index == totalChik) {
			
			// 뽑아낸 조합의 도시 치킨 거리 구하기
			int chikDist = 0;
			for (int h = 0; h < houseNum; h++) {
				int min = Integer.MAX_VALUE;
				for (int ck : chikCombi) {
					if (chikPlace.get(ck).dists[h] < min)	// 각 집 별로 가장 가까운 치킨집과의 거리를 구한다
						min = chikPlace.get(ck).dists[h];
				}
				chikDist += min;
			}
			
			if (chikDist < finalDist)
				finalDist = chikDist;
			
			return;
		}
		
		for (int i = num; i < chikNum; i++) {
			chikCombi[index] = i;
			Combi(index+1, i+1);
		}
	}
}

class Place {
	public int x, y;
	public int[] dists;
	
	public Place(int y, int x) {
		this.x = x;
		this.y = y;
	}
}