import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9466_TermProject {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(in.readLine());
            int[] target = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                target[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            int[] isTeam = new int[n];
            Set<Integer> set;
            Stack<Integer> stack;

            for (int i = 0; i < n; i++) { // 모든 노드 하나씩 탐색
                if (isTeam[i] != 0) { // 팀 구성 여부가 이미 판단됐을 경우
                    continue;
                }

                set = new HashSet<>();
                stack = new Stack<>();

                int next = i;
                boolean pause = false;
                while (!set.contains(next)) { // 순환이 생성될 경우까지 탐색
                    if (isTeam[next] != 0) { // 탐색 도중 이미 결과를 판단할 수 있는 경우
                        pause = true;
                        break;
                    }
                    set.add(next);
                    stack.push(next);
                    next = target[next];
                }

                if (!pause) {
                    while (stack.peek() != next) { // 순환이 완성된 노드까지만 pop하여 성공 표시
                        isTeam[stack.pop()] = 1;
                    }
                    isTeam[stack.pop()] = 1;
                }

                while (!stack.isEmpty()) { // 그 이후의 노드들은 실패 표시
                    isTeam[stack.pop()] = -1;
                }
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (isTeam[i] < 0) {
                    cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}