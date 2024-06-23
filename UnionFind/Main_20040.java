import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20040 {

	static int n;
	static int m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for (int i = 0; i < n; i++) parent[i] = i;
		
		int ans = 0;
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if (!union(u, v)) {
				ans = i;
				break;
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
	
}
