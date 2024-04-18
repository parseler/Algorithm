import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5567 {
	
	static int n;
	static int m;
	static List<Edge>[] graph;
	static int cnt;
	static int[] dp;
	static boolean[] visit;
	static final int INF = 1000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		int u, v;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, 1));
			graph[v].add(new Edge(u, 1));
		}
		
		dp = new int[n + 1];
		for (int i = 1; i <= n; i++) dp[i] = INF;
		dp[1] = 0;
		
		visit = new boolean[n + 1];
		dijkstra();
		
		for (int i = 2; i <= n; i++) {
			if (dp[i] <= 2) cnt++;
		}
		
		System.out.println(cnt);
		
	}
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visit[cur.to]) continue;
			visit[cur.to] = true;
			
			for (Edge next : graph[cur.to]) {
				if (dp[next.to] > dp[cur.to] + next.weight) {
					dp[next.to] = dp[cur.to] + next.weight;
					pq.offer(new Edge(next.to, dp[next.to]));
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
}
