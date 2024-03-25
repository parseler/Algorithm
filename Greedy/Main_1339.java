import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1339 {
	
	static int n;
	static int[] alpha;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		alpha = new int[26];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				alpha[str.charAt(str.length() - 1 - j) - 65] += Math.pow(10, j);
			}
		}
		
		Arrays.sort(alpha);
		
		int size = 9;
		int ans = 0;
		for (int i = 25; i >= 0; i--) {
			if (alpha[i] == 0) continue;
			ans += size-- * alpha[i];
		}
		System.out.println(ans);
	}
}
