import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3584 {
	
	static int n;
	static final int[] arr = new int[10001];
	static int[] parent;
	static int[] level;
	static int root;
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int p;
		int c;
		int a;
		int b;
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			parent = Arrays.copyOf(arr, n + 1);
			level = Arrays.copyOf(arr, n + 1);
			graph = new List[n + 1];
			
			for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
			
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				p = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				parent[c] = p;
				graph[p].add(c);
			}
			
			findRoot();
			makeLevel();
			
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(LCA(a, b)).append('\n');
		}
		System.out.println(sb);
	}
	
	static int LCA(int a, int b) {
		if (level[a] < level[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		while (level[a] != level[b]) {
			a = parent[a];
		}
		
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}
	
	static void makeLevel() {
		level[root] = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(root);
		
		int cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			
			for (int next : graph[cur]) {
				level[next] = level[cur] + 1;
				queue.offer(next);
			}
		}
	}
	
	static void findRoot() {
		for (int i = 1; i <= n; i++) {
			if (parent[i] == 0) {
				root = i;
				break;
			}
		}
	}
}