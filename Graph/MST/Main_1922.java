import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1922 {
	
	static int n;
	static int m;
	static Map<Integer, List<Edge>> graph;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new HashMap<>();
		set = new HashSet<>();
		int start = 0;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if (graph.containsKey(a)) graph.get(a).add(new Edge(b, w));
			else {
				List<Edge> list = new ArrayList<>();
				list.add(new Edge(b, w));
				graph.put(a, list);
				set.add(a);
			}
			
			if (graph.containsKey(b)) graph.get(b).add(new Edge(a, w));
			else {
				List<Edge> list = new ArrayList<>();
				list.add(new Edge(a, w));
				graph.put(b, list);
				set.add(b);
			}
			if (i == m - 1) start = a;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int ans = 0;
		int cnt = 0;
		
		set.remove(start);
		pq.addAll(graph.get(start));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (set.contains(cur.to)) {
				set.remove(cur.to);
				ans += cur.weight;
				if (++cnt == n - 1) break;
				
				
				if (graph.containsKey(cur.to)) {
					pq.addAll(graph.get(cur.to));
				}
			}
		}
		
		System.out.println(ans);
		
	}
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
}