import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ4358_Ecology {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> treeCnt = new HashMap<>();
        PriorityQueue<String> treeName = new PriorityQueue<>();
        int total = 0;

        while (in.hasNextLine()) {
            String tree = in.nextLine();
            if ("".equals(tree)) {
                break;
            }

            total++;
            if (treeCnt.containsKey(tree)) {
                treeCnt.put(tree, treeCnt.get(tree) + 1);
            }
            else {
                treeName.offer(tree);
                treeCnt.put(tree, 1);
            }
        }

        while (!treeName.isEmpty()) {
            String tree = treeName.poll();
            System.out.println(tree + String.format(" %.4f", (treeCnt.get(tree) * 100 / (double)total)));
        }
    }
}