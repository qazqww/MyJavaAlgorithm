package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10775_Airport {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(in.readLine());
        int P = Integer.parseInt(in.readLine());
        parent = new int[G + 1];
        for (int i = 0; i < G + 1; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int no = Integer.parseInt(in.readLine());
            int root = findRoot(no);
            if (root == 0) {
                break;
            }

            answer++;
            union(root - 1, root);
        }
        System.out.println(answer);
    }

    static void union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot < bRoot) {
            parent[bRoot] = aRoot;
        }
        else {
            parent[aRoot] = bRoot;
        }
    }

    static int findRoot(int idx) {
        return parent[idx] = parent[idx] == idx ? idx : findRoot(parent[idx]);
    }
}