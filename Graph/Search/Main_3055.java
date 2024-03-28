import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055 {
	
	static int n;
	static int m;
	static char[][] arr;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static Queue<Point> queue;
	static int[][] visit;
	static Queue<Point> water;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		visit = new int[n][m];
		queue = new ArrayDeque<>();
		water = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'S') {
					queue.offer(new Point(i, j));
					arr[i][j] = '.';
					visit[i][j] = 1;
				}
				else if (arr[i][j] == '*') {
					water.offer(new Point(i, j));
				}
			}
		}
		
		int size;
		int ans = 0;
		
		run:
		while (!queue.isEmpty()) {
			size = water.size();
			for (int i = 0; i < size; i++) {
				Point w = water.poll();
				for (int d = 0; d < 4; d++) {
					int ni = w.x + di[d];
					int nj = w.y + dj[d];
					if (!bound(ni, nj)) continue;
					if (arr[ni][nj] == '.') {
						arr[ni][nj] = '*';
						water.offer(new Point(ni, nj));
					}
				}
			}
			size = queue.size();
			for (int i = 0; i < size; i++) {
				Point s = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = s.x + di[d];
					int nj = s.y + dj[d];
					if (!bound(ni, nj)) continue;
					if (arr[ni][nj] == 'D') {
						ans = visit[s.x][s.y];
						break run;
					}
					
					if (arr[ni][nj] == '.') {
						if (visit[ni][nj] != 0) continue;
						visit[ni][nj] = visit[s.x][s.y] + 1;
						queue.offer(new Point(ni, nj));
					}
				}
			}
		}
		if (ans != 0) System.out.println(ans);
		else System.out.println("KAKTUS");
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
}