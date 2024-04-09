import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1695 {
	
	static int n;
	static int[] arr;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		dp = new int[n][n];
		for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
		System.out.println(count(0, n - 1));
	}
	
	static int count(int start, int end) {
		if (dp[start][end] != -1) return dp[start][end];
		
		if (start >= end) return dp[start][end] = 0;
		
		if (arr[start] == arr[end]) return dp[start][end] = count(start + 1, end - 1);
		
		return dp[start][end] = Math.min(count(start + 1, end), count(start, end - 1)) + 1;
	}
}