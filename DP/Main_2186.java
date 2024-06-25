import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2186 {

	static int n;
	static int m;
	static int k;
	static char[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static String str;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		str = br.readLine();
		
		dp = new int[n][m][str.length()];
		for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) Arrays.fill(dp[i][j], -1);
		
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != str.charAt(0)) continue;
				
				ans += dfs(i, j, 0);
			}
		}
		
		System.out.println(ans);
	}
	
	static int dfs(int x, int y, int depth) {
		
		if (dp[x][y][depth] != -1) return dp[x][y][depth];
		if (depth == str.length() - 1) return dp[x][y][depth] = 1;
		
		int res = 0;
		
		for (int d = 0; d < 4; d++) {
			for (int s = 1; s <= k; s++) {
				int ni = x + di[d] * s;
				int nj = y + dj[d] * s;
				
				if (!bound(ni, nj)) continue;
				if (arr[ni][nj] != str.charAt(depth + 1)) continue;
				
				res += dfs(ni, nj, depth + 1);
			}
		}
		
		return dp[x][y][depth] = res;
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}