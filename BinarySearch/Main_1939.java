import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1939 {

	static int n;
	static int m;
	static List<Edge>[] graph;
	static int start;
	static int end;
	static int ans;
	static int[] visit;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[x].add(new Edge(y, w));
			graph[y].add(new Edge(x, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int left = 1;
		int right = 1_000_000_000;
		ans = 0;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (prob(mid)) {
				ans = mid;
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		System.out.println(ans);
	}
	
	static boolean prob(int weight) {
		visit = new int[n + 1];
		visit[start] = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == end) break;
			
			for (Edge next : graph[cur]) {
				if (next.w < weight) continue;
				if (visit[next.to] != 0) continue;
				
				visit[next.to] = visit[cur] + 1;
				queue.offer(next.to);
			}
		}
		if (visit[end] == 0) return false;
		else return true;
	}

	static class Edge {
		int to;
		int w;
		
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

}
