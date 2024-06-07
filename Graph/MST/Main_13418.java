import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13418 {
	
	static int n;
	static int m;
	static List<Edge>[] graphMax;
	static List<Edge>[] graphMin;
	static int max;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graphMax = new List[n + 1];
		graphMin = new List[n + 1];
		for (int i = 0; i <= n; i++) {
			graphMax[i] = new ArrayList<>();
			graphMin[i] = new ArrayList<>();
		}
		
		for (int i = 0; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int up = Integer.parseInt(st.nextToken());
			
			graphMax[u].add(new Edge(v, up));
			graphMax[v].add(new Edge(u, up));
			graphMin[u].add(new Edge(v, 1 - up));
			graphMin[v].add(new Edge(u, 1 - up));
		}
		
		primMax();
		primMin();

		System.out.println(max * max - min * min);
	}
	
	static void primMax() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 1));
		boolean[] visit = new boolean[n + 1];
		
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visit[cur.to]) continue;	
			visit[cur.to] = true;
	
			if (cur.w == 0) {
				max++;
			}

			if (++cnt == n + 1) break;
			
			for (Edge next : graphMax[cur.to]) {
				if (visit[next.to]) continue;
				pq.offer(next);
			}
		}
	}
	
	static void primMin() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		boolean[] visit = new boolean[n + 1];
		
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visit[cur.to]) continue;
			visit[cur.to] = true;
			
			if (cur.w == 1) {
				min++;
			}
			
			if (++cnt == n + 1) break;
			
			for (Edge next : graphMin[cur.to]) {
				if (visit[next.to]) continue;
				pq.offer(next);
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
