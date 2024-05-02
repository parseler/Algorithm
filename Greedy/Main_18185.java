import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18185 {
	
	static int n;
	static int[] weight;
	static int[] cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        cnt = new int[n];
        
        weight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) weight[i] = Integer.parseInt(st.nextToken());
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
        	ans += Math.min(weight[i], cnt[i]) * 2;
        	
        	if (cnt[i] >= weight[i]) continue;
        	
        	ans += (weight[i] - cnt[i]) * 3;
        	
        	if (i + 1 < n) cnt[i + 1] += weight[i] - cnt[i];
        	if (i + 2 < n) cnt[i + 2] += Math.min(weight[i + 1], weight[i] - cnt[i]);
        }
        System.out.println(ans);
    }
}