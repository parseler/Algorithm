import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146 {
	
	static int n;
	static int[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Point> q = new ArrayDeque<>();
		Queue<Node> queue = new ArrayDeque<>();
		
		int idx = 1;
		boolean[][] visit = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if (arr[i][j] != 0 && !visit[i][j]) {
					arr[i][j] = ++idx;
					q.offer(new Point(i, j));
					visit[i][j] = true;
					
					while (!q.isEmpty()) {
						Point cur = q.poll();
						for (int d = 0; d < 4; d++) {
							int ni = cur.x + di[d];
							int nj = cur.y + dj[d];
							
							if (!bound(ni, nj)) continue;
							if (visit[ni][nj]) continue;
							if (arr[ni][nj] == 0) continue;
							
							arr[ni][nj] = idx;
							visit[ni][nj] = true;
							q.offer(new Point(ni, nj));
						}
					}
				}
				
				
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (!bound(ni, nj)) continue;
					
					if (arr[ni][nj] == 0 && arr[i][j] != 0) {
						queue.offer(new Node(i, j, arr[i][j]));
						break;
					}
				}
			}
		}
		
		int[][][] visited = new int[idx + 1][n][n];
		int res = 0;
		
		run:
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				
				if (!bound(ni, nj)) continue;
				if (arr[ni][nj] == cur.idx) continue;
				if (visited[cur.idx][ni][nj] != 0) continue;
				if (arr[ni][nj] == 0) {
					visited[cur.idx][ni][nj] = visited[cur.idx][cur.x][cur.y] + 1;
					queue.offer(new Node(ni, nj, cur.idx));
				} else {
					res = visited[cur.idx][cur.x][cur.y];
					break run;
				}
			}
		}
		
		System.out.println(res);
		
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < n;
	}
	
	static class Node {
		int x;
		int y;
		int idx;
		
		public Node(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
}
