import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2662 {

	static int n;
	static int m;
	static int[][] arr;
	static int[][] dp;
	static int[][] com;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[n + 1][m + 1];
		com = new int[n + 1][m + 1];
		
		for (int i = 1; i <= m; i++) {
			for (int cost = 1; cost <= n; cost++) {
				for (int j = 0; j <= cost; j++) {
					if (dp[cost][i] < dp[j][i - 1] + arr[cost - j][i]) {
						dp[cost][i] = dp[j][i - 1] + arr[cost - j][i];
						com[cost][i] = cost - j;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append(dp[n][m]).append('\n');
		int[] ans = new int[m + 1];
		
		int cost = n;
		for (int i = m; i >= 1; i--) {
			ans[i] = com[cost][i];
			cost -= com[cost][i];
		}
		for (int i = 1; i <= m; i++) sb.append(ans[i]).append(' ');
		
		System.out.println(sb);
	}
}