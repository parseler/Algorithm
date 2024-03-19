import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666 {

	static int n;
	static int[][][] dp;
	static int T;
	static int[] seq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		T = Integer.parseInt(br.readLine());
		dp = new int[T + 1][n + 1][n + 1];
		
		seq = new int[T + 1];
		for (int t = 1; t <= T; t++) seq[t] = Integer.parseInt(br.readLine());

		System.out.println(find(0, x, y));

	}
	static int find(int idx, int x, int y) {
		
		if (dp[idx][x][y] != 0) return dp[idx][x][y];
		
		if (idx == T - 1) {
			return dp[idx][x][y] = Math.min(Math.abs(seq[idx + 1] - y), Math.abs(seq[idx + 1] - x));
		}
		
		return dp[idx][x][y] = Math.min(find(idx + 1, x, seq[idx + 1]) + Math.abs(y - seq[idx + 1]),
				find(idx + 1, seq[idx + 1], y) + Math.abs(x - seq[idx + 1]));
	}
}
