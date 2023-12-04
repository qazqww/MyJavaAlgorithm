import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005_ACMCraft {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            // 자료 입력
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            BuildInfo[] buildInfos = new BuildInfo[N];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                buildInfos[i] = new BuildInfo(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(in.readLine());
                int now = Integer.parseInt(st.nextToken()) - 1;
                int other = Integer.parseInt(st.nextToken()) - 1;

                buildInfos[now].nextTech.add(other);
                buildInfos[other].precedeCnt++;
            }

            int target = Integer.parseInt(in.readLine()) - 1;

            // 건설 가능한 건물 탐색
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                if (buildInfos[i].precedeCnt == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int now = queue.poll();

                if (now == target) {
                    System.out.println(buildInfos[now].precedeTime + buildInfos[now].buildTime);
                    break;
                }

                for (int next : buildInfos[now].nextTech) {
                    buildInfos[next].precedeCnt--;
                    buildInfos[next].precedeTime = Math.max(buildInfos[next].precedeTime, buildInfos[now].precedeTime + buildInfos[now].buildTime);
                    if (buildInfos[next].precedeCnt == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
    }

    static class BuildInfo {
        int precedeCnt;
        int precedeTime;
        int buildTime;
        ArrayList<Integer> nextTech;

        public BuildInfo(int buildTime) {
            this.precedeCnt = 0;
            this.precedeTime = 0;
            this.buildTime = buildTime;
            this.nextTech = new ArrayList<>();
        }
    }
}