import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12919 {

	static String S;
	static String T;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();
		T = br.readLine();

		dp = new int[50][50];
		
		System.out.println(dp(0, T.length() - 1) - 1);
	}

	static int dp(int start, int end) {

		if (dp[start][end] != 0)
			return dp[start][end];

		if (end - start + 1 == S.length() || start - end + 1 == S.length()) {
			
			if (check(start, end)) return dp[start][end] = 2;
			
			return dp[start][end] = 1;
		}

		if (T.charAt(end) == 'A') {
			int case1 = dp(start, start <= end ? end - 1 : end + 1);
			if (case1 == 2) return dp[start][end] = 2;
			
			if (T.charAt(start) == 'B') return dp[start][end] = dp(end, start <= end ? start + 1 : start - 1);
			
			return dp[start][end] = 1;
			
		} else {
			if (T.charAt(start) == 'B') return dp[start][end] = dp(end, start <= end ? start + 1 : start - 1);
			else return dp[start][end] = 1;
		}
	}

	static boolean check(int start, int end) {
		if (start <= end) {
			for (int i = 0; i < S.length(); i++) {
				if (S.charAt(i) != T.charAt(i + start))
					return false;
			}
		} else {
			for (int i = 0; i < S.length(); i++) {
				if (S.charAt(i) != T.charAt(start - i))
					return false;
			}
		}
		return true;
	}
}
