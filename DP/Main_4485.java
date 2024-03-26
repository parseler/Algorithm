import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485 {
	
	static int n;
	static int[][] arr;
	static int[][] dp;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int idx = 1;
		while ((n = Integer.parseInt(br.readLine())) != 0) {
			sb.append("Problem ").append(idx++).append(": ");
			arr = new int[n][n];
			dp = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = arr[0][0];
			
			pq = new PriorityQueue<>();
			pq.offer(new Node(0, 0, dp[0][0]));
			
			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + di[d];
					int ny = cur.y + dj[d];
					if (!isBound(nx, ny)) continue;
					
					if (dp[nx][ny] <= cur.w + arr[nx][ny]) continue;
					dp[nx][ny] = cur.w + arr[nx][ny];
					pq.offer(new Node(nx, ny, dp[nx][ny]));
				}
			}
			sb.append(dp[n - 1][n - 1]).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int w;
		
		public Node(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	
	static boolean isBound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}