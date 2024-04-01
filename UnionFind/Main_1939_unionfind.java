import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1939_unionfind {

	static int n;
	static int m;
	static int start;
	static int end;
	static int ans;
	static PriorityQueue<Edge> pq;
	static int[] parent;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		makeParent();	
		makePQ();

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		while (find(start) != find(end)) {
			Edge cur = pq.poll();
			
			union(cur.u, cur.v);
			ans = cur.w;
		}
		
		System.out.println(ans);
	}
	
	static void union(int a, int b) {
		int x = find(a);
		int y = find(b);
		
		if (x == y) return;
		
		parent[x] = y;
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static void makePQ() throws IOException {
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(x, y, w));
		}
	}
	
	static void makeParent() {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) parent[i] = i;
	}

	static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.w, this.w);
		}
	}
}
