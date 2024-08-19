import java.util.ArrayList;
import java.util.Arrays;

public class Lv3_TableMerge {
    static class Solution {
        final int TABLE_LEN = 2500;
        int[] parents = new int[TABLE_LEN];
        String[] values = new String[TABLE_LEN];
        ArrayList<String> list = new ArrayList<>();

        public String[] solution(String[] commands) {
            setup();
            for (String str : commands) {
                String[] cmd = str.split(" ");
                switch (cmd[0]) {
                    case "UPDATE":
                        if (cmd.length == 4) { // 해당 셀 값 변경
                            int root = findRoot(getIdxNum(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])));
                            values[root] = cmd[3];
                        }
                        else { // 해당 값 모두 변경
                            for (int i = 0; i < TABLE_LEN; i++) {
                                if (values[i].equals(cmd[1])) {
                                    values[i] = cmd[2];
                                }
                            }
                        }
                        break;
                    case "MERGE":
                        union(getIdxNum(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])),
                            getIdxNum(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])));
                        break;
                    case "UNMERGE":
                        int targetIdx = getIdxNum(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                        int root = findRoot(targetIdx);
                        String value = values[root];
                        ArrayList<Integer> initializeList = new ArrayList<>();
                        for (int i = 0; i < TABLE_LEN; i++) {
                            if (findRoot(i) == root) {
                                initializeList.add(i);
                            }
                        }
                        for (int num : initializeList) {
                            parents[num] = num;
                            values[num] = "";
                        }
                        values[targetIdx] = value;
                        break;
                    case "PRINT":
                        int root2 = findRoot(getIdxNum(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2])));
                        if (values[root2].isBlank()) {
                            list.add("EMPTY");
                        }
                        else {
                            list.add(values[root2]);
                        }
                        break;
                }
//                for (int i = 1; i <= 4; i++) {
//                    for (int j = 1; j <= 4; j++) {
//                        System.out.print(values[getIdxNum(i, j)] + "\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
            String[] answer = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }

        void setup() {
            for (int i = 0; i < 2500; i++) {
                parents[i] = i;
                values[i] = "";
            }
        }

        int getIdxNum(int r, int c) {
            return (r - 1) * 50 + (c - 1);
        }

        int findRoot(int num) {
            if (parents[num] == num) {
                return num;
            }
            return parents[num] = findRoot(parents[num]);
        }

        void union(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);
            if (aRoot != bRoot) {
                parents[bRoot] = aRoot;
            }
            if (values[aRoot].isBlank() && !values[bRoot].isBlank()) {
                values[aRoot] = values[bRoot];
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{
                "UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"
        })));
        System.out.println(Arrays.toString(s.solution(new String[]{
                "UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"
        })));
    }
}
