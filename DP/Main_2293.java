import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2293 {
	
	static int n;
	static int k;
	static int[] val;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		val = new int[n];
		for (int i = 0; i < n; i++) {
			val[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[k + 1];
		dp[0] = 1;
		
		for (int i = 0; i < n; i++) {
			for (int cost = 1; cost <= k; cost++) {
				if (cost < val[i]) continue;
				dp[cost] += dp[cost - val[i]];
			}
		}
		
		System.out.println(dp[k]);
	}
}
