import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16398 {
	
	static int n;
	static int[] parent;
	static long ans = 0L;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for (int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 1; j <= n; j++) {
        		int w = Integer.parseInt(st.nextToken());
        		if (i >= j) continue;
        		pq.offer(new Edge(i, j, w));
        	}
        }
        
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
        
        int cnt = 0;
        while (!pq.isEmpty()) {
        	Edge cur = pq.poll();
        	
        	if (union(cur.u, cur.v)) {
        		ans += cur.w;
        		if (++cnt == n - 1) break;
        	}
        }
        
        System.out.println(ans);
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
    
    static class Edge implements Comparable<Edge> {
    	int u;
    	int v;
    	int w;
    	
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
    }
}