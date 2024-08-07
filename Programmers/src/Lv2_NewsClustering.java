import java.util.HashMap;
import java.util.Map;

public class Lv2_NewsClustering {
    static class Solution {
        public int solution(String str1, String str2) {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
            str1 = str1.replaceAll("[^a-z]", "!");
            str2 = str2.replaceAll("[^a-z]", "!");

            Map<String, int[]> stringCnt = new HashMap<>();
            for (int i = 1; i < str1.length(); i++) {
                if (str1.charAt(i - 1) == '!' || str1.charAt(i) == '!') {
                    continue;
                }
                String subStr = str1.substring(i-1, i+1);
                if (!stringCnt.containsKey(subStr)) {
                    stringCnt.put(subStr, new int[2]);
                }
                stringCnt.get(subStr)[0]++;
            }
            for (int i = 1; i < str2.length(); i++) {
                if (str2.charAt(i - 1) == '!' || str2.charAt(i) == '!') {
                    continue;
                }
                String subStr = str2.substring(i-1, i+1);
                if (!stringCnt.containsKey(subStr)) {
                    stringCnt.put(subStr, new int[2]);
                }
                stringCnt.get(subStr)[1]++;
            }

            int union = 0;
            int inter = 0;
            for (String key : stringCnt.keySet()) {
                int[] arr = stringCnt.get(key);
                union += Math.max(arr[0], arr[1]);
                inter += Math.min(arr[0], arr[1]);
            }
            int answer = union == 0 ? 65536 : (int) ((inter / (double)union) * 65536);
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("FRANCE", "french"));
        System.out.println(s.solution("handshake", "shake hands"));
        System.out.println(s.solution("aa1+aa2", "AAAA12"));
        System.out.println(s.solution("E=M*C^2", "e=m*c^2"));
    }
}