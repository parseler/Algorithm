import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623 {
	
	static int n;
	static int m;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] ans;
	static Queue<Integer> queue;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		degree = new int[n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken()) - 1;
			
			int cur = Integer.parseInt(st.nextToken());
			
			while (k-- > 0) {
				int next = Integer.parseInt(st.nextToken());
				graph[next].add(cur);
				degree[cur]++;
				cur = next;
			}
		}
		
		ans = new int[n + 1];
		visit = new boolean[n + 1];
		int idx = n;
		queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			if (degree[i] == 0) {
				queue.offer(i);
				ans[idx--] = i;
				visit[i] = true;
			}
		}

		if (queue.size() == 0) {
			System.out.println(0);
			return;
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : graph[cur]) {
				degree[next]--;
				if (degree[next] == 0) {
					if (visit[next]) {
						System.out.println(0);
						return;
					}
					ans[idx--] = next;
					queue.offer(next);
					visit[next] = true;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) sb.append(ans[i]).append('\n');
		System.out.println(sb);
	}
}
