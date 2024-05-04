import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12014 {
	
	static int n;
	static int k;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	sb.append("Case #").append(t).append('\n');
        	
        	st = new StringTokenizer(br.readLine());
        	
        	n = Integer.parseInt(st.nextToken());
        	k = Integer.parseInt(st.nextToken());
        	int[] lis = new int[n + 1];
        	int idx = 0;
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 1; i <= n; i++) {
        		int cur = Integer.parseInt(st.nextToken());
        		if (cur > lis[idx]) {
        			lis[++idx] = cur;
        			continue;
        		}
        		
        		int pt = Arrays.binarySearch(lis, 0, idx, cur);
        		if (pt < 0) lis[-(pt + 1)] = cur;
        	}
        	if (idx >= k) sb.append(1).append('\n');
        	else sb.append(0).append('\n');
        }
        System.out.println(sb);
    }
}