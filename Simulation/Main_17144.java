import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17144 {
	
	static int n;
	static int m;
	static int T;
	static int[][] arr;
	static Point[] condi = new Point[2];
	static Queue<Point> queue;
	static int sum;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		queue = new ArrayDeque<>();
		sum = 0;
		
		int idx = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					condi[idx++] = new Point(i, j);
					continue;
				}
				
				if (arr[i][j] != 0) {
					queue.offer(new Point(i, j));
				}
			}
		}
		
		while (T-- > 0) {
			diffuse();
			conditioner();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == -1) continue;
				sum += arr[i][j];
			}
		}

		System.out.println(sum);
	}
	
	static void conditioner() {
		int[][] map = new int[n][m];
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (upScope(cur.x, cur.y)) {
				if (cur.y != m - 1 && cur.x == condi[0].x) {
					map[cur.x][cur.y + 1] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.y == m - 1 && cur.x != 0) {
					map[cur.x - 1][cur.y] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.x == 0 && cur.y != 0) {
					map[cur.x][cur.y - 1] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.y == 0 && cur.x != condi[0].x) {
					map[cur.x + 1][cur.y] = arr[cur.x][cur.y]; 
				}
			}
			else if (downScope(cur.x, cur.y)) {
				if (cur.y != m - 1 && cur.x == condi[1].x) {
					map[cur.x][cur.y + 1] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.y == 0 && cur.x != condi[1].x) {
					map[cur.x - 1][cur.y] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.x == n - 1 && cur.y != 0) {
					map[cur.x][cur.y - 1] = arr[cur.x][cur.y];
					continue;
				}
				
				if (cur.y == m - 1 && cur.x != n - 1) {
					map[cur.x + 1][cur.y] = arr[cur.x][cur.y]; 
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == -1) continue;
				if (!upScope(i, j) && !(downScope(i, j))) {
					if (arr[i][j] != 0) queue.offer(new Point(i, j));
					continue;
				}
				
				arr[i][j] = map[i][j];
				if (arr[i][j] != 0) queue.offer(new Point(i, j));
			}
		}
		
		
	}
	static boolean upScope(int x, int y) {
		return x == 0 || x == condi[0].x || (x <= condi[0].x && (y == 0 || y == m - 1));
	}
	
	static boolean downScope(int x, int y) {
		return x == condi[1].x || x == n - 1 || (x >= condi[1].x && (y == 0 || y == m - 1));
	}
	
	static void diffuse() {
		int[][] map = new int[n][m];
			
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if (arr[cur.x][cur.y] < 5) continue;
			int k = arr[cur.x][cur.y] / 5;
			
			int cnt = 0;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				if (!bound(nx, ny)) continue;
				if (arr[nx][ny] == -1) continue;
				cnt++;
				map[nx][ny] += k;
			}
			arr[cur.x][cur.y] -= cnt * k;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == -1) continue;
				
				arr[i][j] += map[i][j];
				if (arr[i][j] != 0) queue.offer(new Point(i, j));
			}
		}
		
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}
