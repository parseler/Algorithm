import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1135 {
	
	static int n;
	static List<Integer>[] graph;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());   
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
        	graph[Integer.parseInt(st.nextToken())].add(i);
        }
        
        System.out.println(dp(0) - 1);
        
    }
    static int dp(int cur) {
    	int len = graph[cur].size();
    	
    	if (len == 0) return 1;
    	int[] ans = new int[len];
    	int idx = 0;
    	
    	for (int next : graph[cur]) {
    		ans[idx++] = dp(next);
    	}
    	
    	Arrays.sort(ans);
    	for (int i = 0; i < len; i++) ans[i] += len - i - 1;
    	Arrays.sort(ans);
    	return ans[len - 1] + 1;
    }
}