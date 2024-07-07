import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6087 {

	static int n;
	static int m;
	static char[][] arr;
	static int[][][] visit;
	static int ans;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		visit = new int[4][n][m];
		
		int k = 0;
		Queue<Point> queue = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				
				if (arr[i][j] == 'C' && k++ == 0) {
					for (int d = 0; d < 4; d++) {
						queue.offer(new Point(i, j, d));
						visit[d][i][j] = 1;
					}
					arr[i][j] = '.';
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (visit[cur.dir][cur.x][cur.y] >= ans) continue;
			
			if (arr[cur.x][cur.y] == 'C') {
				if (visit[cur.dir][cur.x][cur.y] < ans) {
					ans = visit[cur.dir][cur.x][cur.y];
				}
				continue;
			}
			
			for (int d = 0; d < 4; d++) {
				
				if (d + cur.dir == 1) continue;
				if (d + cur.dir == 5) continue;
				
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				
				if (!bound(ni, nj)) continue;
				if (arr[ni][nj] == '*') continue;
				
				if (cur.dir == d) {
					if (visit[d][ni][nj] == 0 || visit[d][ni][nj] > visit[cur.dir][cur.x][cur.y]) {
						visit[d][ni][nj] = visit[cur.dir][cur.x][cur.y];
						queue.offer(new Point(ni, nj, d));
					}
				} else {
					if (visit[d][ni][nj] == 0 || visit[d][ni][nj] > visit[cur.dir][cur.x][cur.y] + 1) {
						visit[d][ni][nj] = visit[cur.dir][cur.x][cur.y] + 1;
						queue.offer(new Point(ni, nj, d));
					}
				}
			}
		}
		System.out.println(ans - 1);
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	static class Point {
		int x;
		int y;
		int dir;
		
		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}