import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19538 {

	static int n;
	static int m;
	static List<Integer>[] graph;
	static int[] visit;
	static int[] rumor;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int k;
			while ((k = Integer.parseInt(st.nextToken())) != 0) {
				graph[i].add(k);
			}
		}
		
		visit = new int[n + 1];
		Arrays.fill(visit, -1);
		rumor = new int[n + 1];
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			
			int k = Integer.parseInt(st.nextToken());
			visit[k] = 0;
			queue.offer(k);
			
			for (int next : graph[k]) rumor[next]++;
		}
		
		while (!queue.isEmpty()) {
			
			int s = queue.size();
			Queue<Integer> q = new ArrayDeque<>();
			
			while (s-- > 0) {
				int cur = queue.poll();
				for (int next : graph[cur]) {
					
					if (visit[next] != -1) continue;
					
					if (graph[next].size() > rumor[next] * 2) continue;
					
					visit[next] = visit[cur] + 1;
					queue.offer(next);
					q.offer(next);
				}
			}
			
			while (!q.isEmpty()) {
				int k = q.poll();
				
				for (int next : graph[k]) {
					rumor[next]++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= n; i++) sb.append(visit[i]).append(' ');
		System.out.println(sb);
	}
}