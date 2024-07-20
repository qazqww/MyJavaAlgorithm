public class Lv1_BandageWrap {
    static class Solution {
        public int solution(int[] bandage, int health, int[][] attacks) {
            int maxHealth = health;
            int atkIdx = 0;
            int healTime = 0;

            for (int i = 1; i <= attacks[attacks.length - 1][0]; i++) {
                if (i == attacks[atkIdx][0]) {
                    health -= attacks[atkIdx][1];
                    if (health <= 0) {
                        return -1;
                    }
                    healTime = 0;
                    atkIdx++;
                }
                else {
                    health += bandage[1];
                    healTime++;
                    if (healTime == bandage[0]) {
                        healTime = 0;
                        health += bandage[2];
                    }
                    if (health > maxHealth) {
                        health = maxHealth;
                    }
                }
            }
            return health;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] { 5, 1, 5 }, 30, new int[][] {
                new int[] { 2, 10 },
                new int[] { 9, 15 },
                new int[] { 10, 5 },
                new int[] { 11, 5 }
        }));
    }
}
