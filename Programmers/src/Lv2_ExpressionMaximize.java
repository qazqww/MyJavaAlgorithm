public class Lv2_ExpressionMaximize {
    static class Solution {
        final char[][] priorities = new char[][] {
                new char[] { '+', '_', '*' }, new char[] { '+', '*', '_' },
                new char[] { '*', '+', '_' }, new char[] { '*', '_', '+' },
                new char[] { '_', '+', '*' }, new char[] { '_', '*', '+' },
        };
        
        public long solution(String expression) {
            long answer = 0;
            expression = expression.replace('-', '_');

            for (char[] priority : priorities) {
                String newExp = expression;
                for (char op : priority) {
                    newExp = splitString(newExp, "\\" + op);
                }
                answer = Math.max(answer, Math.abs(Long.parseLong(newExp)));
            }

            return answer;
        }
    }

    static String splitString(String expression, String op) {
        String[] temp = expression.split(op);
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < temp.length - 1; i++) {
            long front = 0;
            long rear = 0;

            // 앞 식의 끝번째 수 빼내기
            for (int j = temp[i].length() - 1; j >= -1; j--) {
                if (j == -1 || (temp[i].charAt(j) != '-' && (temp[i].charAt(j) < '0' || temp[i].charAt(j) > '9'))) {
                    front = Long.parseLong(temp[i].substring(j + 1));
                    temp[i] = temp[i].substring(0, j + 1);
                    break;
                }
            }
            // 뒷 식의 첫번째 수 빼내기
            for (int j = 0; j <= temp[i + 1].length(); j++) {
                if (j >= temp[i + 1].length() || (temp[i + 1].charAt(j) != '-' && (temp[i + 1].charAt(j) < '0' || temp[i + 1].charAt(j) > '9'))) {
                    rear = Long.parseLong(temp[i + 1].substring(0, j));
                    temp[i + 1] = temp[i + 1].substring(j);
                    break;
                }
            }

            long num = 0;
            switch (op) {
                case "\\+":
                    num = front + rear;
                    break;
                case "\\_":
                    num = front - rear;
                    break;
                case "\\*":
                    num = front * rear;
                    break;
            }
            result.append(temp[i]);
            temp[i + 1] = num + temp[i + 1];
        }
        result.append(temp[temp.length - 1]);
        return result.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("999*999*999*999*999"));
        System.out.println(solution.solution("100+100-100+100-100"));
//        System.out.println(solution.solution("100-200*300-500+20"));
//        System.out.println(solution.solution("50*6-3*2"));
    }
}
/*
100 200 300 500 20
- * - +

    
for (char[] priority : priorities) {
                Map<Character, Integer> map = new HashMap<>();
                for (int i = 0; i < priority.length; i++) {
                    map.put(priority[i], i);
                }
                Stack<Integer> prevNum = new Stack<>();
                Stack<Character> prevOp = new Stack<>();

                int numStart = 0;
                for (int i = 0; i < expression.length(); i++) {
                    if (expression.charAt(i) < '0' || expression.charAt(i) > '9') {
                        prevNum.push(Integer.parseInt(expression.substring(numStart, i)));
                        
                        if (prevOp.isEmpty()) {
                            prevOp.push(expression.charAt(i));
                        }
                        else {
                            if (map.get(prevOp.peek()) > map.get(expression.charAt(i))) {
                                
                            }
                        }
                        
                        numStart = i + 1;
                    }
                }
            }
 */