import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194 {
	
	static int n;
	static int m;
	static char[][] arr;
	static int[][][] visit;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		visit = new int[64][n][m];
		
		Queue<Sik> queue = new ArrayDeque<>();
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == '0') {
					queue.offer(new Sik(0, i, j));
					visit[0][i][j] = 1;
				}
			}
		}
		
		int ans = -1;
		
		escape:
		while (!queue.isEmpty()) {
			Sik cur = queue.poll();
//			System.out.println(cur);
			
			if (arr[cur.x][cur.y] == 1) {
				ans = visit[cur.key][cur.x][cur.y];
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.x + di[d];
				int nj = cur.y + dj[d];
				if (!bound(ni, nj)) continue;
				
				if (arr[ni][nj] == '1') {
					ans = visit[cur.key][cur.x][cur.y];
					break escape;
				}
				
				if (arr[ni][nj] == '#') continue;
				
				// key
				if (arr[ni][nj] >= 97 && arr[ni][nj] <= 102) {
					if (visit[cur.key | 1<<(arr[ni][nj] - 'a')][ni][nj] != 0) continue;
					
					visit[cur.key | 1<<(arr[ni][nj] - 'a')][ni][nj] = visit[cur.key][cur.x][cur.y] + 1;
					queue.offer(new Sik(cur.key | 1<<(arr[ni][nj] - 'a'), ni, nj));
					continue;
				}
				
				// door
				if (arr[ni][nj] >= 65 && arr[ni][nj] <= 70) {
					if ((cur.key & 1<<(arr[ni][nj] - 'A')) == 0) continue;
					if (visit[cur.key | 1<<(arr[ni][nj] - 'A')][ni][nj] != 0) continue;
					
					visit[cur.key | 1<<(arr[ni][nj] - 'A')][ni][nj] = visit[cur.key][cur.x][cur.y] + 1;
					queue.offer(new Sik(cur.key, ni, nj));
					continue;
				}
				
				if (visit[cur.key][ni][nj] != 0) continue;
				visit[cur.key][ni][nj] = visit[cur.key][cur.x][cur.y] + 1;
				queue.offer(new Sik(cur.key, ni, nj));
			}
		}
		
		System.out.println(ans);
		
	}
	
	static class Sik {
		int key;
		int x;
		int y;
		
		
		public Sik(int key, int x, int y) {
			this.key = key;
			this.x = x;
			this.y = y;			
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Sik [key=");
			builder.append(key);
			builder.append(", x=");
			builder.append(x);
			builder.append(", y=");
			builder.append(y);
			builder.append("]");
			return builder.toString();
		}
	}
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}