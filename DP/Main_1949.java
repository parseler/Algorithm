import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1949 {

	static int n;
	static int[] cost;
	static List<Integer>[] graph;
	static int[] dp;
	static boolean[] dpUse;
	static int[] dist;
	static final int ROOT = 1;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		cost = new int[n + 1];
		graph = new List[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		dist = new int[n + 1];
		visit = new boolean[n + 1];
		visit[ROOT] = true;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(ROOT);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			visit[cur] = true;
			for (int next : graph[cur]) {
				if (visit[next]) continue;
				visit[next] = true;
				dist[next] = dist[cur] + 1;
				queue.offer(next);
			}
		}
		
		dp = new int[n + 1];
		dpUse = new boolean[n + 1];

		System.out.println(findMaxFromRoot(ROOT));
	}
	static int findMaxFromRoot(int a) {
		if (dp[a] != 0) return dp[a];
		
		
		int res = 0;
		boolean canUse = true;

		
		for (int next : graph[a]) {
			if (dist[next] < dist[a]) continue;
			
			int k = findMaxFromRoot(next);
			if (dpUse[next] && canUse) canUse = false;
			
			res += k;
		}
		
		if (canUse) {
			dpUse[a] = true;
			return dp[a] = res + cost[a];
		}
		else {
			int nextRes = 0;
			for (int next : graph[a]) {
				if (dist[next] < dist[a]) continue;
				for (int nn : graph[next]) {
					if (dist[nn] < dist[next]) continue;
					nextRes += findMaxFromRoot(nn);
				}
			}
			
			if (nextRes + cost[a] > res) {
				dp[a] = nextRes + cost[a];
				dpUse[a] = true;
			} else dp[a] = res;
			
			return dp[a];
		}
	}
}
