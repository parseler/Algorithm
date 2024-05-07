import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368 {
	
	static int n;
	static int[] parent;
	static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        
        parent = new int[n + 2];
        pq = new PriorityQueue<>();
        
        for (int i = 1; i <= n; i++) {
        	parent[i] = i;
        	pq.offer(new Edge(n + 1, i, Integer.parseInt(br.readLine())));
        }
        parent[n + 1] = n + 1;
  
        for (int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 1; j <= n; j++) {
        		int w = Integer.parseInt(st.nextToken());
        		if (i >= j) continue;
        		
        		pq.offer(new Edge(i, j, w));
        	}
        }
        
        int cnt = 0;
        int ans = 0;
        
        while (!pq.isEmpty()) {
        	Edge cur = pq.poll();
        	
        	if (!union(cur.u, cur.v)) continue;
        	ans += cur.w;
        	
        	if (++cnt == n) break;
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
			return Integer.compare(this.w, o.w);
		}
    }
}