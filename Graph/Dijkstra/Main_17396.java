import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17396 {
	
	static int n;
	static int m;
	static int[] canGo;
	static List<Edge>[] graph;
	static boolean[] visit;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		canGo = new int[n];
		graph = new List[n];
		visit = new boolean[n];
		arr = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			canGo[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
			arr[i] = Long.MAX_VALUE;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, w));
			graph[b].add(new Edge(a, w));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(0, 0));
		
		arr[0] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
//			if (cur.to != n - 1 && canGo[cur.to] == 1) continue;
			
			if (visit[cur.to]) continue;
			visit[cur.to] = true;
			
			for (Edge next : graph[cur.to]) {
				if (next.to != n - 1 && canGo[next.to] == 1) continue;
				
				if (arr[next.to] > arr[cur.to] + next.w) {
					arr[next.to] = arr[cur.to] + next.w;
					pq.offer(new Edge(next.to, arr[next.to]));
				}
			}
		}
//		System.out.println(Arrays.toString(arr));
		if (arr[n - 1] == Long.MAX_VALUE) System.out.println(-1);
		else System.out.println(arr[n - 1]);
		System.out.println(Long.MAX_VALUE);
	}
	static class Edge implements Comparable<Edge>{
		int to;
		long w;
		
		public Edge(int to, long w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.w, o.w);
		}
	}
}
