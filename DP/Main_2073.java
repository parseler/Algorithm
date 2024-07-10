import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2073 {
    
    static int d;
    static int p;
    static int[] dp;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());

         d = Integer.parseInt(st.nextToken());
         p = Integer.parseInt(st.nextToken());
         
         dp = new int[d + 1];
         dp[0] = Integer.MAX_VALUE;
        
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine ());
            int l = Integer.parseInt (st.nextToken ());
            int c = Integer.parseInt (st.nextToken ());
            
           for (int i = d; i >= l; i--) {
        	   if (dp[i - l] == 0) continue;
        	   
               dp[i] = Math.max(dp[i], Math.min(dp[i - l], c));
           }
        }
        
        System.out.println(dp[d]);
    }
}