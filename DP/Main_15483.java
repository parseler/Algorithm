import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15483 {
	
	static String str1;
	static String str2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = br.readLine();
		str2 = br.readLine();
		
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];
		
		for (int i = 1; i <= str1.length(); i++) dp[i][0] = i;
		for (int j = 1; j <= str2.length(); j++) dp[0][j] = j;
		
		for (int i = 1; i <= str1.length(); i++) {
			for (int j = 1; j <= str2.length(); j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
			}
		}
		System.out.println(dp[str1.length()][str2.length()]);
	}
}
