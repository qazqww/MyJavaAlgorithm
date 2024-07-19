import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Lv3_DoublePriorityQueue {
    static class Solution {
        public int[] solution(String[] operations) {
            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
            PriorityQueue<Integer> minPQ = new PriorityQueue<>();

            for (String operation : operations) {
                String[] temp = operation.split(" ");
                char cmd = temp[0].charAt(0);
                int num = Integer.parseInt(temp[1]);
                switch (cmd) {
                    case 'I':
                        maxPQ.offer(num);
                        minPQ.offer(num);
                        if (map.containsKey(num)) {
                            map.put(num, map.get(num) + 1);
                        }
                        else {
                            map.put(num, 1);
                        }
                        break;
                    case 'D':
                        if (num == 1) {
                            checkPQ(maxPQ, map, false);
                        }
                        else {
                            checkPQ(minPQ, map, false);
                        }
                        break;
                }
            }

            int[] answer = { checkPQ(maxPQ, map, true), checkPQ(minPQ, map, true)};
            return answer;
        }

        int checkPQ(PriorityQueue<Integer> pq, Map<Integer, Integer> map, boolean searchMode) {
            while (!pq.isEmpty() && !map.containsKey(pq.peek())) {
                pq.poll();
            }

            if (pq.isEmpty()) {
                return 0;
            }

            if (!searchMode) {
                int next = pq.poll();
                if (map.get(next) == 1) {
                    map.remove(next);
                } else {
                    map.put(next, map.get(next) - 1);
                }
                return next;
            }
            else {
                return pq.peek();
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"}));
        System.out.println();
        System.out.println(s.solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}));
    }
}
/*
PQ max
PQ min
Map<int, int> : 숫자, 개수
 */