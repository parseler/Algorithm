import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458 {

	static int n;
	static long[] dist;
	static boolean[] arrive;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		r:
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(' ');
			n = Integer.parseInt(br.readLine());

			long max = 0;
			dist = new long[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				x = x >= 0 ? x : -x;
				y = y >= 0 ? y : -y;
				dist[i] = x + y;
				if (max < dist[i])
					max = dist[i];

			}

			if (max == 0) {
				sb.append(0).append('\n');
				continue;
			}
			
			boolean even = false;
			if (dist[0] % 2 == 0)
				even = true;

			for (int i = 1; i < n; i++) {
				if ((dist[i] % 2 == 0) != even) {
					sb.append(-1).append('\n');
					continue r;
				}
			}
			
			arrive = new boolean[n];

			int idx = 1;

			while (true) {
				int m = 0;
				int cnt = 0;
				for (int i = 0; i < n; i++) {
					dist[i] -= idx;
					if (dist[i] <= 0) {
						m++;
						if (-dist[i] % 2 == 0) {
							cnt++;
							arrive[i] = true;
						} else
							arrive[i] = false;
					}
				}
				
				if (cnt == n) {
					sb.append(idx).append('\n');
					break;
				}
				
				if (m == n) {
					
				}
				idx++;
			}
		}
		System.out.println(sb);
	}
}