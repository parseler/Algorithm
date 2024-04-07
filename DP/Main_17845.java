import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17845 {

	static int n;
	static int k;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n + 1];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			for (int j = n; j >= 0; j--) {
				if (j - t < 0) break;
				dp[j] = Math.max(dp[j], dp[j - t] + w);
			}
		}
		
		System.out.println(dp[n]);		
		
	}
}
