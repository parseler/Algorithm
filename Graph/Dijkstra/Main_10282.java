import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10282 {
	
	static int n;
	static int d;
	static int c;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			List<Edge>[] graph = new List[n + 1];
			for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				graph[v].add(new Edge(u, w));
			}
			
			int[] dp = new int[n + 1];
			dijkstra(dp, graph);
			
			int cnt = 0;
			int time = 0;
			
			for (int i = 1; i <= n; i++) {
				if (dp[i] == INF) continue;
				cnt++;
				if (time < dp[i]) time = dp[i];
			}
			
			sb.append(cnt).append(' ').append(time).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra(int[] dp, List<Edge>[] graph) {
		Arrays.fill(dp, INF);
		dp[c] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(c, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for (Edge next : graph[cur.to]) {
				if (dp[next.to] > dp[cur.to] + next.w) {
					dp[next.to] = dp[cur.to] + next.w;
					pq.offer(new Edge(next.to, dp[next.to]));
				}
			}
		}
	}


	static class Edge implements Comparable<Edge> {
		int to;
		int w;
		
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
