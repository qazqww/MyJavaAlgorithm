package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1495_Guitarist {
    static int N, S, M, answer;
    static int[] volume;
    static Map<Integer, Set<Integer>> visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = -1;
        volume = new int[N];
        visited = new HashMap<>();
        
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }
        setVolume(0, S);
        System.out.println(answer);
    }

    static void setVolume(int index, int curVolume) {
        if (index == N) {
            answer = Math.max(answer, curVolume);
            return;
        }
        if (curVolume - volume[index] >= 0) {
            if (!visited.containsKey(index)) {
                visited.put(index, new HashSet<>());
            }
            if (!visited.get(index).contains(curVolume - volume[index])) {
                visited.get(index).add(curVolume - volume[index]);
                setVolume(index + 1, curVolume - volume[index]);
            }
        }
        if (curVolume + volume[index] <= M) {
            if (!visited.containsKey(index)) {
                visited.put(index, new HashSet<>());
            }
            if (!visited.get(index).contains(curVolume + volume[index])) {
                visited.get(index).add(curVolume + volume[index]);
                setVolume(index + 1, curVolume + volume[index]);
            }
        }
    }
}