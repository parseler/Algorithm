import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_24538 {
	
	static int n;
	static int k;
	static long[] arr;
	static long[] brr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new long[k + 2];
		brr = new long[k + 2];
		
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			arr[s] += 1;
			arr[e + 1] -= 1;
			brr[e + 1] -= (e - s + 1);
		}
		
		for (int i = 1; i <= k; i++) {
			arr[i] += arr[i - 1];
		}
		for (int i = 1; i <= k; i++) {
			arr[i] += arr[i - 1] + brr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= k; i++) sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
}
