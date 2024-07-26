    import java.util.Arrays;

public class Lv2_Minecraft {
    static class Solution {
        int totalPickax;
        String[] minerals;
        int[] remainPickax = new int[3];
        int[] order;
        int answer = Integer.MAX_VALUE;

        public int solution(int[] picks, String[] minerals) {
            totalPickax = Arrays.stream(picks).sum();
            this.minerals = minerals;
            remainPickax = picks;
            order = new int[totalPickax];
            permu(0);
            return answer;
        }

        void process() {
            int fatigue = 0;
            int mineralIdx = 0;
            loop: for (int pickax : order) {
                int mineCnt = 0;
                while (mineCnt < 5) {
                    fatigue += getFatigue(pickax, minerals[mineralIdx++]);
                    mineCnt++;
                    if (mineralIdx >= minerals.length) {
                        break loop;
                    }
                }
            }
            answer = Math.min(answer, fatigue);
        }

        int getFatigue(int pickax, String mineral) {
            if (pickax == 0) {
                return 1;
            }
            else if (pickax == 1) {
                if (mineral.equals("diamond")) {
                    return 5;
                }
                return 1;
            }
            else {
                if (mineral.equals("diamond")) {
                    return 25;
                }
                else if (mineral.equals("iron")) {
                    return 5;
                }
                return 1;
            }
        }

        void permu(int idx) {
            if (idx >= totalPickax) {
                process();
                return;
            }
            for (int i = 0; i < 3; i++) {
                if (remainPickax[i] > 0) {
                    order[idx] = i;
                    remainPickax[i]--;
                    permu(idx + 1);
                    remainPickax[i]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s1 = new Solution();
        System.out.println(s1.solution(new int[] { 1, 3, 2 }, new String[] {
                "diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"
        }));
        Solution s2 = new Solution();
        System.out.println(s2.solution(new int[] { 0, 1, 1 }, new String[] {
                "diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"
        }));
    }
}
