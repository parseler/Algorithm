import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263 {
	
	static int n;
	static int[][] arr;
	static final int MAX = 10000;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j= 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 0) arr[i][j] = MAX;
				}
			}
			
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (i == j) continue;
						if (i == k || j == k) continue;
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
			
			int cc = MAX;
			
			for (int i = 0; i < n; i++) {
				int c = 0;
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					c += arr[i][j];
				}
				if (cc > c) cc = c;
			}
			sb.append(cc).append('\n');
		}
		System.out.println(sb);
	}
}