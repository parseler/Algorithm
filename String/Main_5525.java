import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_5525 {

	static int n;
	static int m;
	static int pl; // pattern length
	static String str;
	static int[] piTable;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		pl = 2 * n + 1;
		piTable = new int[pl];
//		makePiTable("IOIOIOIOI");
		
		for (int i = 2; i < pl; i++) piTable[i] = i - 1;
		
		KMP();
		System.out.println(cnt);
	}

	static void makePiTable(String pattern) {
		int j = 0;
		
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) j = piTable[j - 1];
			
			if (pattern.charAt(i) == pattern.charAt(j)) piTable[i] = ++j;
		}
	}
	
	static void KMP() {
		int j = 0;
		
		for (int i = 0; i < str.length(); i++) {
			
			char p = 'O';
			if (j % 2 == 0) p = 'I';
			
			while (j > 0 && str.charAt(i) != p) {
				j = piTable[j - 1];
				if (j % 2 == 0) p = 'I';
				else p = 'O';
			}
			
			if (str.charAt(i) == p) {
				if (j == pl - 1) {
					cnt++;
					j = piTable[j];
				}
				else j++;
			}
		}
	}
}