import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904_AandB {
	
	static String start, target;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		start = in.readLine();
		target = in.readLine();
			
		if (find(start))
			System.out.println(1);
		else
			System.out.println(0);
	}
	
	static boolean find(String str) {
		String typeA = str + "A";
		String typeB = "B" + str;
		
		System.out.println(typeA + " " + reverse(typeB));
		
		if (str.length() == target.length()) {
			if (typeA.equals(target) || reverse(typeB).equals(target))
				return true;
			else 
				return false;
		}
		
		if (find(typeA))
			return true;
		
		if (find(typeB))
			return true;
		
		return false;
	}
	
	static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
}
