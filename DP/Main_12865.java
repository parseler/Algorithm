import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12865 {
	
	static int n;
	static int k;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[k + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			for (int j = k; j >= w; j--) {
				dp[j] = Math.max(dp[j - w] + v, dp[j]);
			}
		}
		
		System.out.println(dp[k]);
	}
}
