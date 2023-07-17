import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1360_Undo {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "");
        ArrayList<Integer> timestamp = new ArrayList<>();
        timestamp.add(0);
        StringBuilder now = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            String command = st.nextToken();
            String content = st.nextToken();
            int time = Integer.parseInt(st.nextToken());

            switch (command) {
                case "type":
                    now.append(content);
                    break;
                case "undo":
                    int search = time - Integer.parseInt(content) - 1;
                    if (search < 0) {
                        search = 0;
                    }
                    if (!map.containsKey(search)) {
                        for (int j = timestamp.size() - 1; j >= 0; j--) {
                            if (timestamp.get(j) < search) {
                                search = timestamp.get(j);
                                break;
                            }
                        }
                    }
                    now = new StringBuilder(map.get(search));
                    break;
            }
            map.put(time, now.toString());
            timestamp.add(time);
        }
        System.out.println(map.get(timestamp.get(timestamp.size() - 1)));
    }
}