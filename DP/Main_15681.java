import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15681 {

	static int n;
	static int root;
	static int Q;
	static List<Integer>[] graph;
	static boolean[] visit;
	static int[] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
     
        n = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(v);
        	graph[v].add(u);
        }
        
        visit = new boolean[n + 1];
        dp = new int[n + 1];
        dfs(root);
        
        while (Q-- > 0) sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        
        System.out.println(sb);
    }
    
    static int dfs(int cur) {
    	if (dp[cur] != 0) return dp[cur];
    	visit[cur] = true;
    	
    	int cnt = 1;
    	for (int next : graph[cur]) {
    		if (visit[next]) continue;
    		cnt += dfs(next);
    	}
    	
    	return dp[cur] = cnt;
    }
}