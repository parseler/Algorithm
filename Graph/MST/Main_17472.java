import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472 {

	static int n;
	static int m;
	static int[][] arr;
	static boolean[][] visit;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int[] parent;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int k = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					bfs(i, j, k++);
				}
			}
		}

		pq = new PriorityQueue<>();
		int[][] distance = new int[k + 1][k + 1];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) continue;
				
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (!isBound(ni, nj)) continue;
					if (arr[ni][nj] == arr[i][j]) continue;
					
					int dist = 0;
					while (true) {
						dist++;
						ni += di[d];
						nj += dj[d];
						if (!isBound(ni, nj)) break;
						if (arr[ni][nj] != 0) {
							if (dist == 1) break;
							if (distance[arr[i][j]][arr[ni][nj]] == 0) distance[arr[i][j]][arr[ni][nj]] = dist;
							else distance[arr[i][j]][arr[ni][nj]] = Math.min(dist, distance[arr[i][j]][arr[ni][nj]]);
							break;
						}
					}
				}
			}
		}
		
		for (int i = 2; i < k ; i++) {
			for (int j = i + 1; j <= k; j++) {
				if (distance[i][j] == 0) continue;
				pq.offer(new Edge(i, j, distance[i][j]));
			}
		}
		
		parent = new int[k + 1];
		for (int i = 2; i <= k; i++) parent[i] = i;
		
		int ans = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (!union(e.x, e.y)) continue;
			
			ans += e.w;
			if (++cnt == k - 2) break;
		}
		
		if (cnt == k - 2) System.out.println(ans);
		else System.out.println(-1);
		
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
	
	static void bfs(int i, int j, int k) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(i, j));
		arr[i][j] = k + 1;
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = p.x + di[d];
				int nj = p.y + dj[d];
				if (!isBound(ni, nj)) continue;
				
				if (arr[ni][nj] == 1) {
					arr[ni][nj] += k;
					queue.offer(new Point(ni, nj));
				}
			}
		}
	}
	
	static boolean isBound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		int w;
		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		@Override
		public String toString() {
			return "Edge [x=" + x + ", y=" + y + ", w=" + w + "]";
		}
		
	}
}