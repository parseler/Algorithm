import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1256 {
	
	static int n;
	static int m;
	static int k;
	static final int INF = 1_000_000_001;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new long[n + m + 1][(n + m + 1) / 2 + 1];
		makeDp();
				
		int[] res = new int[n + m];
		int ans = 0;
		
		for (int i = 0; i < res.length; i++) {
			
			if (n == 0 || m == 0) {
				if (k != 1) ans = -1;
				else {
					int left = n != 0 ? 1 : 0;
					for (int j = i; j < res.length; j++) res[j] = left;
				}
				break;
			}
			
			long cur = dp[n + m - 1][m > n - 1 ? n - 1 : m];
			
			if (cur < k) {
				res[i] = 0;
				m--;
				k -= cur;
			} else {
				res[i] = 1;
				n--;
			}
		}
		
		if (ans == -1) System.out.println(-1);
		else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < res.length; i++) {
				sb.append(res[i] == 0 ? 'z' : 'a');
			}
			System.out.println(sb);
		}
	}
	
	static void makeDp() {
		for (int i = 0; i <= n + m; i++) {
			dp[i][0] = 1;
			
			for (int j = 1; j <= i / 2 + 1; j++) {
				dp[i][j] = dp[i][j - 1] * (i - j + 1) / j;
				
				if (dp[i][j] > k) {
					for (int t = j; t <= i / 2 + 1; t++) dp[i][t] = INF;
					break;
				}
			}
		}
	}
}
