import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main_1947 {
	
	static int n;
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		dp = new long[n + 4];
		dp[2] = 1;
		dp[3] = 2;
		dp[4] = 9;
		
		for (int i = 5; i <= n; i++) {
			dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % 1000000000;
		}
		
		System.out.println(dp[n]);
	}
}