import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774 {
	
	static int n;
	static int m;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	static int[][] pts;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        pts = new int[n + 1][2];
        pq = new PriorityQueue<>();
        
        for (int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
        	pts[i][0] = Integer.parseInt(st.nextToken());
        	pts[i][1] = Integer.parseInt(st.nextToken());
        	
        	for (int j = i - 1; j >= 1; j--) {
        		double w = cal(pts[i][0], pts[i][1], pts[j][0], pts[j][1]);
        		pq.offer(new Edge(i, j, w));
        	}
        }
        
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
        
        int cnt = n - 1;
        
        while (m-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	if (union(u, v)) cnt--;
        }

        double ans = 0;
        while (!pq.isEmpty()) {
        	Edge cur = pq.poll();
        	
        	if (!union(cur.u, cur.v)) continue;
        	ans += cur.w;
        	
        	if (--cnt == 0) break;
        }
        
        System.out.printf("%.2f", ans);
    }    
    static double cal(int x1, int y1, int x2, int y2) {
    	
    	double x = (double) x1 - x2;
    	double y = (double) y1 - y2;
    	
    	return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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
    	double w;

		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
    }
}