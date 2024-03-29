import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656 {
	
	static int c;
	static int n;
	static int m;
	static int[][] arr;
	static int[] seq;
	static int cnt;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int ans;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(' ');
			
			st = new StringTokenizer(br.readLine());
			
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			cnt = 0;
			
			arr = new int[n][m];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] != 0) cnt++;
				}
			}
			
			seq = new int[c];
			ans = cnt;
			perm(0);
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static void play() {
		int res = cnt;
		Queue<Point> queue = new ArrayDeque<>();
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) copy[i][j] = arr[i][j];
		}
		
		for (int i = 0; i < c; i++) {
			int pt = seq[i];
			int j = 0;
			while (j < n && copy[j][pt] == 0) j++;
			
			if (j == n) continue;
			queue.offer(new Point(j, pt));
			
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				int k = copy[cur.x][cur.y];
				
				if (k == 0) continue;
				
				res--;
				copy[cur.x][cur.y] = 0; 
				if (k == 1) continue;
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.x;
					int nj = cur.y;
					
					for (int p = 1; p < k; p++) {
						ni += di[d];
						nj += dj[d];
						if (!bound(ni, nj)) break;
						if (copy[ni][nj] == 0) continue;
						queue.offer(new Point(ni, nj));
					}
				}
			}
			gravity(copy);
		}
		if (res < ans) {
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(copy[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			ans = res;
		}
	}
	
	static void gravity(int[][] board) {
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                // 내려야 할만한거
                if (board[i][j] != 0) {
                    int val = board[i][j];
                    int x = i;
                    int y = j;
  
                    while (true) {
                        int nx = x + 1;
                        if (bound(nx, y) && board[nx][y] == 0) {
                            board[nx][y] = val;
                            board[x][y] = 0;
                            x = nx;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
	
	static void perm(int depth) {
		
		if (depth == c) {
			play();
			return;
		}
		
		for (int i = 0; i < m; i++) {
			seq[depth] = i;
			perm(depth + 1);
		}
	}
	
	static boolean bound(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	
}