import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10711 {

	static int n;
	static int m;
	static char[][] arr;
	static int[][] count;
	static int[] di = {0, 1, 0, -1, -1, -1, 1, 1};
	static int[] dj = {1, 0, -1, 0, 1, -1, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) arr[i][j] = str.charAt(j);
		}
		
		count = new int[n][m];
		
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				
				if (arr[i][j] == '.') continue;
				
				for (int d = 0; d < 8; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if (arr[ni][nj] == '.') count[i][j]++;
				}
			}
		}
		
		int cnt = 0;
		Queue<Point> queue = new ArrayDeque<>();

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (arr[i][j] == '.') continue;
				
				if (arr[i][j] - '0' <= count[i][j]) {
					arr[i][j] = '.';
					queue.offer(new Point(i, j));
				}
			}
		}
		
		while (true) {
			int k = queue.size();

			if (k == 0) break;
			cnt++;
			
			while (k-- > 0) {
				Point cur = queue.poll();
				
				for (int d = 0; d < 8; d++) {
					int ni = cur.x + di[d];
					int nj = cur.y + dj[d];
					
					if (arr[ni][nj] == '.') continue;
					
					count[ni][nj]++;
					
					if (count[ni][nj] >= arr[ni][nj] - '0') {
						arr[ni][nj] = '.';
						queue.offer(new Point(ni, nj));
					}
				}
			}
			
//			for (int i = 0; i < n; i++) System.out.println(Arrays.toString(arr[i]));
//			for (int i = 0; i < n; i++) System.out.println(Arrays.toString(count[i]));
//			System.out.println();
		}
		
		System.out.println(cnt);
	}
}