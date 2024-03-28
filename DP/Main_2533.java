import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2533 {
	
	static int n;
	static List<Integer>[] graph;
	static int[][] dp;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		graph = new List[n + 1];
		
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		dp = new int[n + 1][2];
		for (int i = 0; i < n + 1; i++) Arrays.fill(dp[i], -1);
		
		int ans = Math.min(root(1, 0, 0), root(1, 1, 0));
		
		System.out.println(ans);
			
	}
	static int root(int cur, int idx, int parent) {
		if (dp[cur][idx] != -1) return dp[cur][idx];
		
		dp[cur][idx] = 0;

		for (int next : graph[cur]) {
			if (next == parent) continue;
			if (idx == 0) dp[cur][idx] += root(next, 1, cur);
			else dp[cur][idx] += Math.min(root(next, 0, cur), root(next, 1, cur));
		}
		
		if (idx == 1) return dp[cur][idx] = dp[cur][idx] + 1;
		return dp[cur][idx];
	}
}