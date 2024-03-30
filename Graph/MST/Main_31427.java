import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_31427 {

	static int n;
	static int m;
	static int[] parent;
	static int[] arr = new int[5];
	static List<Edge>[] list;
	static boolean[] visit;
	static int[] seq;
	static Map<Integer, int[]> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		list = new List[5];
		for (int i = 0; i < 5; i++) list[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int school = st.nextToken().charAt(0) - 'A';
			list[school].add(new Edge(x, y));
		}

		visit = new boolean[5];
		map = new HashMap<Integer, int[]>();
		seq = new int[5];
		perm(0);
		
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			Point[] pts = new Point[5];
			int[] arr = new int[5];
			for (int i = 0; i < 5; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				pts[i] = new Point(arr[i], i);
			}
			Arrays.sort(pts, (o1, o2) -> {
				return o1.x - o2.x;
			});
			int hash = pts[0].y * 10000 + pts[1].y * 1000 + pts[2].y * 100 + pts[3].y * 10 + pts[4].y;

			int[] cur = map.get(hash);
			
			long ans = 0;
			for (int i = 0; i < 5; i++) {
				ans += (long) cur[i] * arr[i];
			}
			sb.append(ans).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void perm(int depth) {
		
		if (depth == 5) {
			kruskal();
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if (visit[i]) continue;
			seq[depth] = i;
			visit[i] = true;
			perm(depth + 1);
			visit[i] = false;
		}
	}
	
	static void kruskal() {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) parent[i] = i;
		int[] count = new int[5];
		int cnt = 0;
		
		run:
		for (int i = 0; i < 5; i++) {
			int cur = seq[i];
			
			for (Edge e : list[cur]) {
				if (!union(e.x, e.y)) continue;
				count[cur]++;
				if (++cnt == n - 1) break run;
			}
		}
		
		int hash = seq[0] * 10000 + seq[1] * 1000 + seq[2] * 100 + seq[3] * 10 + seq[4];
		map.put(hash, count);
	}

	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);
		if (x == y) return false;
		
		parent[x] = y;
		return true;
	}
	
	static class Edge {
		int x;
		int y;
		int weight;
		
		public Edge(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
