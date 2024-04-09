import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17609 {
	
	static int n;
	static String str;
	static int start;
	static int end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		while (n-- > 0) {
			str = br.readLine();
			start = 0;
			end = str.length() - 1;
			
			if (pelindrom()) {
				sb.append(0).append('\n');
				continue;
			}
			
			if (simpel(start + 1, end) || simpel(start, end - 1)) {
				sb.append(1).append('\n');
			}
			else sb.append(2).append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static boolean simpel(int start, int end) {
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	static boolean pelindrom() {
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
	
	
}
