import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1103 {

	static int n;
	static int m;
	static int[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int[][] visit;
	static int[][] dp;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) == 'H' ? 0 : str.charAt(j) - '0';
			}
		}
		
		visit = new int[n][m];
		visit[0][0] = 1;
		dp = new int[n][m];
		
		dfs(0, 0);
		
		System.out.println(flag ? -1 : dp[0][0]);
	}
	
	static int dfs(int x, int y) {
		
		if (flag) return -1;
		if (dp[x][y] != 0) return dp[x][y];
		
		int res = 1;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d] * arr[x][y];
			int ny = y + dj[d] * arr[x][y];
			
			if (!bound(nx, ny)) continue;
			if (arr[nx][ny] == 0) continue;
			
			if (visit[nx][ny] == 1) {
				flag = true;
			}
			
			visit[nx][ny] = 1;
			res = Math.max(res, 1 + dfs(nx, ny));
			visit[nx][ny] = 0;
		}
		
		return dp[x][y] = res;
	}
	
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
