import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17619 {
    
    static int n;
    static int q;
    static int[] parent;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());

         n = Integer.parseInt(st.nextToken());
         q = Integer.parseInt(st.nextToken());
         
         arr = new int[n][3];
         
         for (int i = 1; i <= n; i++) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int x1 = Integer.parseInt(st.nextToken());
        	 int x2 = Integer.parseInt(st.nextToken());
        	 st.nextToken();
        	 
        	 arr[i - 1][0] = x1;
        	 arr[i - 1][1] = x2;
        	 arr[i - 1][2] = i;
         }
         
         Arrays.sort(arr, (o1, o2) -> {
        	 return Integer.compare(o1[0], o2[0]);
         });
         
         parent = new int[n + 1];
         for (int i = 1; i <= n; i++) parent[i] = i;
         
         int end = arr[0][1];
         int p = arr[0][2];
         
         for (int i = 1; i < n; i++) {
        	 if (end < arr[i][0]) {
        		 end = arr[i][1];
        		 p = arr[i][2];
        		 continue;
        	 }
        	 
        	 union(p, arr[i][2]);
        	 end = Math.max(end, arr[i][1]);
         }
         
         StringBuilder sb = new StringBuilder();
         while (q-- > 0) {
        	 st = new StringTokenizer(br.readLine());
        	 
        	 int a = Integer.parseInt(st.nextToken());
        	 int b = Integer.parseInt(st.nextToken());
        	 sb.append(find(a) == find(b) ? 1 : 0).append('\n');
         }
         System.out.println(sb);
    }
    
    static int find(int a) {
    	if (parent[a] == a) return a;
    	return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b) {
    	int x = find(a);
    	int y = find(b);
    	if (x != y) parent[x] = y;
    }
}