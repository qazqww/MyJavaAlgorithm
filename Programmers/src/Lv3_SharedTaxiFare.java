import java.util.Arrays;

public class Lv3_SharedTaxiFare {
    static class Solution {
        final static int INF = 100_000_000;
        public int solution(int n, int s, int a, int b, int[][] fares) {
                int[][] map = new int[n + 1][n + 1];
                for (int i = 0; i <= n; i++) {
                    Arrays.fill(map[i], INF);
                    map[i][i] = 0;
                }

                for (int[] fare : fares) {
                    map[fare[0]][fare[1]] = fare[2];
                    map[fare[1]][fare[0]] = fare[2];
                }

                for (int k = 1; k <= n; k++) {
                    for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= n; j++) {
                            map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                        }
                    }
                }

                int answer = Integer.MAX_VALUE;
                for (int k = 1; k <= n; k++) {
                    answer = Math.min(answer, map[s][k] + map[k][a] + map[k][b]);
                }
                return answer;
        }
    }
    /*
    각 점에 대해
    S -> K * 2 랑 K -> A + K -> B
     */

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, 4, 6, 2, new int[][] {
                new int[] { 4, 1, 10 },
                new int[] { 3, 5, 24 },
                new int[] { 5, 6, 2 },
                new int[] { 3, 1, 41 },
                new int[] { 5, 1, 24 },
                new int[] { 4, 6, 50 },
                new int[] { 2, 4, 66 },
                new int[] { 2, 3, 22 },
                new int[] { 1, 6, 25 }
        }));
    }
}