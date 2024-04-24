import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5214 {
	
	static int n;
	static int k;
	static int m;
	static int[][] graph;
	static int[] visit;
	static List<Integer>[] tubes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[m + 1][k];
		tubes = new List[n + 1];
		for (int i = 1; i <= n; i++) tubes[i] = new ArrayList<Integer>();
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < k; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());		
				tubes[graph[i][j]].add(i);
			}
			Arrays.sort(graph[i]);
		}

		visit = new int[n + 1];
		visit[1] = 1;
		
		System.out.println(bfs() == 0 ? -1 : visit[n]);
	}
	
	static int bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(1);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == n) break;
			
			for (int tube : tubes[cur]) {
				for (int next : graph[tube]) {
					if (visit[next] != 0) continue;
					visit[next] = visit[cur] + 1;
					queue.offer(next);
				}
			}
		}
		
		return visit[n];
	}
}