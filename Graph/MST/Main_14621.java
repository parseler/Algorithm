import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14621 {
	
	static int n;
	static int m;
	static char[] arr;
	static int[] parent;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = st.nextToken().charAt(0);
		
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if (arr[u] == arr[v]) continue;
			
			pq.offer(new Edge(u, v, w));
		}
		
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) parent[i] = i;
		
		int cnt = 0;
		int ans = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (union(cur.u, cur.v)) {
				ans += cur.w;
				if (++cnt == n - 1) break;
			}
		}
		
		System.out.println(cnt == n - 1 ? ans : -1);
	}
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);
		
		if (x == y) return false;
		
		parent[x] = y;
		return true;
	}
	
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;
		
		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}