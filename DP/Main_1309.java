import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_1309 {
	
	static int n;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][3];
		
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for (int i = 2; i <= n; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
			dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
			dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
			
			for (int j = 0; j < 3; j++) dp[i][j] %= 9901;
		}
		
		System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);
		
	}
}