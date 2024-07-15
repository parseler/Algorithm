import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11057 {
	
	static int n;
	static int[][] dp;
	static final int MOD = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n + 1][10];
		
		int ans = 0;
		
		for (int i = 0; i <= 9; i++) {
			ans += makeDp(n, i);
			ans %= MOD;
		}
		System.out.println(ans);
	}
	
	static int makeDp(int cur, int num) {
		if (dp[cur][num] != 0) return dp[cur][num];
		if (cur == 1) return dp[cur][num] = 1;
		
		int res = 0;
		for (int i = 0; i <= num; i++) {
			res += makeDp(cur - 1, i);
			res %= MOD;
		}
		
		return dp[cur][num] = res;
	}
}