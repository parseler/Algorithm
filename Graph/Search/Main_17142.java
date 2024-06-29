import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142 {
	
	static int n;
	static int m;
	static int[][] arr;
	static List<Point> list;
	static int ans = Integer.MAX_VALUE;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) list.add(new Point(i, j));
			}
		}
		int[] v = new int[m];
		perm(0, v, 0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void bfs(int[] v) {
		Queue<Point> queue = new ArrayDeque<>();
		int[][] visit = new int[n][n];
		for (int i : v) {
			queue.offer(list.get(i));
			visit[list.get(i).x][list.get(i).y] = 1;
		}
		
		int res = 1;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				if (!bound(ni, nj)) continue;
				if (visit[ni][nj] != 0) continue;
				if (arr[ni][nj] == 1) continue;

				queue.offer(new Point(ni, nj));
				visit[ni][nj] = visit[cur.x][cur.y] + 1;
				if (arr[ni][nj] == 2) continue;
				res = Math.max(res, visit[ni][nj]);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 0 && visit[i][j] == 0) return;
			}
		}

		ans = Math.min(res - 1, ans);
	}
	
	static void perm(int depth, int[] v, int s) {
		if (depth == m) {
			bfs(v);
			return;
		}
		
		for (int i = s; i < list.size(); i++) {
			v[depth] = i;
			perm(depth + 1, v, i + 1);
			v[depth] = 0;
		}
	}
	
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}