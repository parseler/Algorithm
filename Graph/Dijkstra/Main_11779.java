import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11779 {

	static int n;
	static int m;
	static List<Edge>[] graph;
	static int[] dp;
	static int[] save;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
			
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dp = new int[n + 1];
		Arrays.fill(dp, INF);
		dp[start] = 0;
		
		save = new int[n + 1];
		
		dijkstra(start, end);
		System.out.println(dp[end]);
		
		int cnt = 1;
		int cur = end;
		
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		list.add(end);
		
		while (cur != start) {
			cnt++;
			cur = save[cur];
			list.add(cur);
		}
		System.out.println(cnt);

		for (int i = list.size() - 1; i >= 0; i--) sb.append(list.get(i)).append(' ');
		System.out.println(sb);
	}
	
	static void dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (cur.to == end) return;
			
			for (Edge next : graph[cur.to]) {
				if (dp[next.to] > dp[cur.to] + next.w) {
					dp[next.to] = dp[cur.to] + next.w;
					
					save[next.to] = cur.to;
					
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
