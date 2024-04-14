import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2632 {
	
	static int order;
	static int n;
	static int m;
	static int[] arr;
	static int[] brr;
	static int[] aum;
	static int[] bum;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		order = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n * 2 + 1];
		brr = new int[m * 2 + 1];
		
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i + n] = Integer.parseInt(br.readLine());
		}
		for (int i = 1; i <= m; i++) {
			brr[i] = brr[i + m] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= 2 * n; i++) arr[i] = arr[i - 1] + arr[i];
		for (int i = 1; i <= 2 * m; i++) brr[i] = brr[i - 1] + brr[i];
		
		aum = new int[arr[n] + 1];
		bum = new int[brr[m] + 1];
		
		for (int i = 0; i <= n; i++) {
			for (int j = i + 1;j <= 2 * n; j++) {
				int k = arr[j] - arr[i];
				if (k > arr[n]) break;
				
				if (j > n && (j - i == n || i == n)) continue;

				aum[k]++;
			}
		}
		
		for (int i = 0; i <= m; i++) {
			for (int j = i + 1; j <= 2 * m; j++) {
				int k = brr[j] - brr[i];
				if (k > brr[m]) break;
				if (j > m && (j - i == m || i == m)) continue;

				bum[k]++;
			}
		}

		aum[0] = bum[0] = 1;
		
		for (int i = 0; i < aum.length; i++) {
			
			if (i > order || order - i >= bum.length) continue;
			
			ans += (long) aum[i] * bum[order - i];
		}
		
		System.out.println(ans);
	}
}
