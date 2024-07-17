import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2212 {
    
    static int n;
    static int k;
    static PriorityQueue<Integer> pq;
    static int[] arr;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
         
         n = Integer.parseInt(br.readLine());
         k = Integer.parseInt(br.readLine()) - 1;
         
         if (k >= n) {
        	 System.out.println(0);
        	 return;
         }
         
         arr = new int[n];
         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
         Arrays.sort(arr);
         
         pq = new PriorityQueue<>((o1, o2) -> {
        	 return Integer.compare(o2, o1);
         });
         
         for (int i = 1; i < n; i++) pq.offer(arr[i] - arr[i - 1]);
         
         int res = arr[n - 1] - arr[0];
         
         while (k-- > 0) res -= pq.poll();
         
         System.out.println(res);
    }
}