import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11780 {

	static int n;
	static int m;
	static int[][] cost;
	static int[][] arr;
	static final int MAX = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		cost = new int[n + 1][n + 1];
		arr = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++)
			Arrays.fill(cost[i], MAX);

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			cost[from][to] = Math.min(cost[from][to], w);
			arr[from][to] = to;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j || i == k || j == k)
						continue;

					if (cost[i][j] > cost[i][k] + cost[k][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
						arr[i][j] = arr[i][k];
					}
				}
			}
		}
		
		StringBuilder costBuilder = new StringBuilder();
		StringBuilder roadBuilder = new StringBuilder();

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				costBuilder.append(cost[i][j] == MAX ? 0 : cost[i][j]).append(' ');

				if (cost[i][j] == MAX) {
					roadBuilder.append(0).append('\n');
					continue;
				}

				int cnt = 1;
				int cur = i;

				while (cur != j) {
					cur = arr[cur][j];
					cnt++;
				}
				roadBuilder.append(cnt).append(' ').append(i).append(' ');

				cur = i;

				while (cur != j) {
					cur = arr[cur][j];
					roadBuilder.append(cur).append(' ');
				}

				roadBuilder.append('\n');
			}
			if (i != n) costBuilder.append('\n');
		}

		System.out.println(costBuilder);
		System.out.println(roadBuilder);

	}
}
