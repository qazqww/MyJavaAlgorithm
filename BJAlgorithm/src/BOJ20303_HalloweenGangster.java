import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20303_HalloweenGangster {
    static Info[] infos;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        infos = new Info[N];
        for (int i = 0; i < N; i++) {
            infos[i] = new Info(i);
        }

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            infos[i].candyCnt = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(infos[a], infos[b]);
        }

        ArrayList<Info> roots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (infos[i].parent == infos[i].no) {
                roots.add(infos[i]);
            }
        }

        int[][] candyCnt = new int[2][K];
        for (int i = 0; i < roots.size(); i++) {
            Info info = roots.get(i);
            for (int j = 0; j < K; j++) {
                if (j < info.headCnt) {
                    candyCnt[1][j] = candyCnt[0][j];
                }
                else {
                    candyCnt[1][j] = Math.max(candyCnt[0][j], candyCnt[0][j - info.headCnt] + info.candyCnt);
                }
            }
            System.arraycopy(candyCnt[1], 0, candyCnt[0], 0, K);
            candyCnt[1] = new int[K];
        }

        System.out.println(candyCnt[0][K - 1]);
    }

    static void union(Info a, Info b) {
        Info aRoot = findRoot(a);
        Info bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return;
        }

        aRoot.candyCnt += bRoot.candyCnt;
        aRoot.headCnt += bRoot.headCnt;
        bRoot.parent = aRoot.no;
    }

    static Info findRoot(Info num) {
        if (num.parent == num.no) {
            return num;
        }
        Info root = findRoot(infos[num.parent]);
        num.parent = root.no;
        return root;
    }

    static class Info {
        int no;
        int parent;
        int candyCnt;
        int headCnt;

        public Info(int no) {
            this.no = no;
            parent = no;
            candyCnt = 0;
            headCnt = 1;
        }
    }
}