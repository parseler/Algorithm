import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1261 {
	
	static int n;
	static int m;
	static int[][] arr;
	static int[][] dp;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static final int INF = 11111;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		dp = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			Arrays.fill(dp[i], INF);
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		dp[0][0] = 0;
		dijkstra();
		
		System.out.println(dp[n - 1][m - 1]);
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				
				if (!bound(ni, nj)) continue;
				
				if (dp[ni][nj] > dp[cur.x][cur.y] + arr[ni][nj]) {
					dp[ni][nj] = dp[cur.x][cur.y] + arr[ni][nj];
					pq.offer(new Edge(ni, nj, dp[ni][nj]));
				}
			}
		}
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
	}
	
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
