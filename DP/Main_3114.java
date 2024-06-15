import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3114 {

	static int n;
	static int m;
	static int[][] arr;
	static int[][] brr;
	static int[][] dp;
	static int[][] prefixArrH;
	static int[][] prefixBrrV;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1][m + 1];
		brr = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= m; j++) {
				String str = st.nextToken();
				if (str.charAt(0) == 'A') {
					arr[i][j] = str.charAt(1) - '0';
					if (str.length() == 3) {
						arr[i][j] *= 10;
						arr[i][j] += str.charAt(2) - '0';
					}
				} else {
					brr[i][j] = str.charAt(1) - '0';
					if (str.length() == 3) {
						brr[i][j] *= 10;
						brr[i][j] += str.charAt(2) - '0';
					}
				}
			}
		}
		
		prefixArrH = new int[n + 1][m + 1];
		prefixBrrV = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				prefixArrH[i][j] = prefixArrH[i][j - 1] + arr[i][j];
				prefixBrrV[i][j] = prefixBrrV[i - 1][j] + brr[i][j];
			}
		}
		
		dp = new int[n][m];
		for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
		
		System.out.println(calculate(n - 1, m - 1));
	}
	
	static int calculate(int i, int j) {
		if (!bound(i, j)) return 0;
		if (dp[i][j] != -1) return dp[i][j];
		if (i == 0 && j == 0) return dp[i][j] = 0;
		
		int fromUp = calculate(i - 1, j);
		int fromLeft = calculate(i, j - 1);
		int fromUpLeft = calculate(i - 1, j - 1);
		
		if (bound(i - 1, j)) {
			fromUp += prefixArrH[i + 1][j];
		}
		if (bound(i, j - 1)) {
			fromLeft += prefixBrrV[i][j + 1];
		}
		
		if (bound(i - 1, j - 1)) {
			fromUpLeft += prefixArrH[i + 1][j] + prefixBrrV[i][j + 1];
		}
		
		return dp[i][j] = Math.max(fromLeft, Math.max(fromUp, fromUpLeft));
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}
