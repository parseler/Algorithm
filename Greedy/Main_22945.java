import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22945 {
	
	static int n;
	static int[] arr;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = n - 1;
		
		while (start < end) {
			int k = (end - start - 1) * Math.min(arr[start], arr[end]);
			
			if (res < k) res = k;
			
			if (arr[start] > arr[end]) end--;
			else start++;
		}
		
		System.out.println(res);
		
	}
}
