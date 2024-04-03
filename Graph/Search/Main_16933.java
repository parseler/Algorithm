import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16933 {
	
	static int n;
	static int m;
	static int k;
	static int[][] arr;
	static int[][] visit;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) arr[i][j] = str.charAt(j) - '0';
		}
		
		visit = new int[n][m];
		for (int i = 0; i < n; i++) Arrays.fill(visit[i], -1);
		
		visit[0][0] = k;
		
		Queue<Human> queue = new ArrayDeque<>();
		
		queue.offer(new Human(0, 0, k, 1));
		int res = -1;
		
		while (!queue.isEmpty()) {
			Human cur = queue.poll();
			if (cur.x == n - 1 && cur.y == m - 1) {
				res = cur.time;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				if (!bound(ni, nj)) continue;
				if (visit[ni][nj] >= cur.h) continue;
				
				if (arr[ni][nj] == 0) {
					queue.offer(new Human(ni, nj, cur.h, cur.time + 1));
					visit[ni][nj] = cur.h;
				}
				else {
					if (cur.h == 0) continue;
					if (visit[ni][nj] >= cur.h - 1) continue;
					
					if (cur.time % 2 == 0) {
						
						queue.offer(new Human(cur.x, cur.y, cur.h, cur.time + 1));
					}
					else {
						queue.offer(new Human(ni, nj, cur.h - 1, cur.time + 1));
						visit[ni][nj] = cur.h - 1;
					}
				}
			}
		}
		System.out.println(res);
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	static class Human {
		int x;
		int y;
		int h;
		int time;
		
		public Human(int x, int y, int h, int time) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.time = time;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Human [x=");
			builder.append(x);
			builder.append(", y=");
			builder.append(y);
			builder.append(", h=");
			builder.append(h);
			builder.append(", time=");
			builder.append(time);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
}
