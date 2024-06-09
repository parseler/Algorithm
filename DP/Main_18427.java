import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18427 {
	
	static int n;
	static int m;
	static int h;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1][m];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int j = 0;
			while (st.hasMoreTokens()) {
				arr[i][j++] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[n + 1][h + 1];
		
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(block(n, h));
		
	}
	static int block(int pts, int height) {
		if (dp[pts][height] != -1) return dp[pts][height];
		if (pts == 0) {
			if (height == 0) return 1;
			else return 0;
		}
		
		int res = 0;
		
		for (int i = 0; i < m; i++) {
			if (arr[pts][i] == 0) break;
			if (arr[pts][i] > height) continue;
			
			res += block(pts - 1, height - arr[pts][i]) % 10007;
		}
		
		res += block(pts - 1, height) % 10007;
		
		return dp[pts][height] = res % 10007;
	}
}
