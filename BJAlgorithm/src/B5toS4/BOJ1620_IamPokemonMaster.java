package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1620_IamPokemonMaster {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Map<Integer, String> idxMap = new HashMap<>();
        Map<String, Integer> nameMap = new HashMap<>();
        int idx = 1;

        for (int i = 0; i < N; i++) {
            String name = in.readLine();
            idxMap.put(idx, name);
            nameMap.put(name, idx);
            idx++;
        }

        for (int i = 0; i < M; i++) {
            String question = in.readLine();
            try {
                int num = Integer.parseInt(question);
                System.out.println(idxMap.get(num));
            }
            catch (NumberFormatException e) {
                System.out.println(nameMap.get(question));
            }
        }
    }
}
