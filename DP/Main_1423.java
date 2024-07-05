import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1423 {

	static int n;
	static int[] cnt;
	static long[] power;
	static int d;
	static Level[][] levels;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		cnt = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) cnt[i] = Integer.parseInt(st.nextToken());
		
		power = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) power[i] = Integer.parseInt(st.nextToken());

		d = Integer.parseInt(br.readLine());
		
		levels = new Level[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				levels[i][j] = new Level(0, -2_000_000);
			}
		}
		
		for (int i = 1; i < n; i++) {
			for (int k = 1; k < n; k++) {
				if (i + k > n) continue;
				
				levels[k][i].to = i;
				levels[k][i].up = power[i + k] - power[i];
			}
		}
		
		for (int k = 1; k < n; k++) Arrays.sort(levels[k]);
		
		
		long[][] dp = new long[d + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			dp[0][i] = cnt[i];
			dp[0][0] += cnt[i] * power[i];
		}
		
		long max;
		
		for (int i = 1; i <= d; i++) {
			
			max = 0;
			
			for (int j = 0; j < i; j++) {
				int up = i - j;
				
				if (up >= n) continue;
				
				for (int k = 0; k < levels[up].length; k++) {
					Level cur = levels[up][k];
					
					if (cur.up == -2_000_000) break;
					if (dp[j][cur.to] == 0) continue;
					
					if (max < dp[j][0] + power[cur.to + up] - power[cur.to]) {
						max = dp[j][0] + power[cur.to + up] - power[cur.to];

						dp[i] = Arrays.copyOf(dp[j], n + 1);
						dp[i][0] = max;
						dp[i][cur.to + up]++;
						dp[i][cur.to]--;
					}
					
					break;
				}
			}
			
			if (dp[i][0] == 0) break;
		}
		
//		for (int i = 0; i <= d; i++) System.out.println(Arrays.toString(dp[i]));
		
		long ans = 0;
		for (int i = 0; i <= d; i++) ans = Math.max(ans, dp[i][0]);
		System.out.println(ans);
	}
	static class Level implements Comparable<Level> {
		int to;
		long up;
		
		public Level(int to, int up) {
			this.to = to;
			this.up = up;
		}

		@Override
		public int compareTo(Level o) {
			return Long.compare(o.up, this.up);
		}
	}
}