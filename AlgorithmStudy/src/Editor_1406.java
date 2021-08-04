// https://www.acmicpc.net/problem/1406

/* [issue] 시간 초과
 * LinkedList를 사용하면 삽입, 삭제가 용이할 줄 알았으나
 * 삽입/삭제 지점까지 가는데에 O(n)이 걸리므로 생각보다 시간이 걸린다.
 * 이를 ListIterator를 추가함으로써 해결했다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Editor_1406 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		List<Character> editor = new LinkedList<>();
		ListIterator<Character> iter;

		for (char ch : in.readLine().toCharArray()) {
			editor.add(ch);
		}

		iter = editor.listIterator();
		while (iter.hasNext()) {
			iter.next();
		}

		int cmdCount = Integer.parseInt(in.readLine());
		int cursor = editor.size();
		for (int i = 0; i < cmdCount; i++) {
			String[] cmd = in.readLine().split(" ");
			switch (cmd[0]) {
			case "L":
				if (iter.hasPrevious()) {
					iter.previous();
				}
				break;
			case "D":
				if (iter.hasNext()) {
					iter.next();
				}
				break;
			case "B":
				if (iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
				break;
			case "P":
				iter.add(cmd[1].charAt(0));
				break;
			}
		}

		for (char ch : editor) {
			sb.append(ch);
		}
		out.write(sb.toString());
		out.flush();
	}
}
		
//		Iterator 미사용 코드 (시간 초과)
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringBuilder sb = new StringBuilder();
//		List<Character> editor = new LinkedList<>();
//
//		for (char ch : in.readLine().toCharArray()) {
//			editor.add(ch);
//		}
//
//		int cmdCount = Integer.parseInt(in.readLine());
//		int cursor = editor.size();
//		for (int i = 0; i < cmdCount; i++) {
//			String[] cmd = in.readLine().split(" ");
//			switch (cmd[0]) {
//			case "L":
//				if (cursor > 0) {
//					cursor--;
//				}
//				break;
//			case "D":
//				if (cursor < editor.size()) {
//					cursor++;
//				}
//				break;
//			case "B":
//				if (cursor - 1 < 0) {
//					break;
//				}
//				editor.remove(--cursor);
//				break;
//			case "P":
//				editor.add(cursor++, cmd[1].charAt(0));
//				break;
//			}
//		}
//
//		for (char ch : editor) {
//			sb.append(ch);
//		}
//		out.write(sb.toString());
//		out.flush();

