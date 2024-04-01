import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {

	static int n;
	static int[] ans;
	static int[] arr;
	static int start;
	static int end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		ans = new int[3];
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		long res = 3_000_000_001L;
		
		run:
		for (int i = 0; i < n - 2; i++) {
			start = i + 1;
			end = n - 1;
			int find = arr[i];
			
			while (start < end) {
				long t = 0L + arr[start] + arr[end] + find;

				if (t < 0) t = -t;
				
				if (res > t) {
					res = t;
					ans[0] = find;
					ans[1] = arr[start];
					ans[2] = arr[end];
					if (res == 0) break run;
				}

				if (0L + arr[start] + arr[end] + find > 0) end--;
				else start++;
			}
		}
		System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
	}
}
