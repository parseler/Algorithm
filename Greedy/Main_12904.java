import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12904 {
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String S = br.readLine();
		String T = br.readLine();
		
		int start = 0;
		int end = T.length() - 1;
		boolean reverseMode = false;
		
		
		for (int i = 0; i < T.length() - S.length(); i++) {
			if (T.charAt(end) == 'A') {
				if (reverseMode) end++;
				else end--;
			} else {
				if (reverseMode) end++;
				else end--;
				
				int k = start;
				start = end;
				end = k;
				reverseMode = !reverseMode;
			}
		}
		
		if (reverseMode) {
			for (int i = 0; i < S.length(); i++) {
				if (S.charAt(i) != T.charAt(start - i)) {
					System.out.println(0);
					return;
				}
			}
		}
		else {
			for (int i = 0; i < S.length(); i++) {
				if (S.charAt(i) != T.charAt(start + i)) {
					System.out.println(0);
					return;
				}
			}
		}
		
		System.out.println(1);
	}
}