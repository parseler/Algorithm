import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_9370 {
	
	static int n;
	static int m;
	static int t;
	static int s;
	static int g;
	static int h;
	static List<Edge>[] graph;
	static int[] dp;
	static int[] route;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			graph = new List[n + 1];
			for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken()) * 2;
				
				if (u == g && v == h) w--;
				if (u == h && v == g) w--;
				
				graph[u].add(new Edge(v, w));
				graph[v].add(new Edge(u, w));
			}
			
			dijkstra();
			
			PriorityQueue<Integer> res = new PriorityQueue<>();
			while (t-- > 0) {
				int dest = Integer.parseInt(br.readLine());
				int r = dest;
				
				while (dest != 0) {
					if ((dest == g && route[dest] == h) || (dest == h && route[dest] == g)) {
						res.offer(r);
						break;
					}
					
					dest = route[dest];
				}
			}
			
			while (!res.isEmpty()) {
				sb.append(res.poll()).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		dp = new int[n + 1];
		Arrays.fill(dp, INF);
		dp[s] = 0;
		
		boolean[] visit = new boolean[n + 1];
		route = new int[n + 1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(s, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (dp[cur.to] < cur.w) continue;		
			if (!visit[cur.to]) visit[cur.to] = true;
			
			for (Edge next : graph[cur.to]) {
				if (visit[next.to]) continue;
				if (dp[next.to] > cur.w + next.w) {
					dp[next.to] = cur.w + next.w;
					pq.offer(new Edge(next.to, dp[next.to]));
					route[next.to] = cur.to;
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
