import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2143 {

	static int T;
	static int n;
	static int m;
	static int[] arr;
	static int[] aum;
	static int[] brr;
	static int[] bum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
		
		aum = new int[(n + 1) * n / 2];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				aum[idx++] = arr[j] - arr[i];
			}
		}

		m = Integer.parseInt(br.readLine());
		brr = new int[m + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= m; i++) brr[i] = Integer.parseInt(st.nextToken()) + brr[i - 1];
		
		bum = new int[(m + 1) * m / 2];
		idx = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j <= m; j++) {
				bum[idx++] = brr[j] - brr[i];
			}
		}
		
		Arrays.sort(aum);
		Arrays.sort(bum);
		
		long cnt = 0;
		int adx = 0;
		int bdx = bum.length - 1;
		
		while (adx < aum.length && bdx >= 0) {
			int num = aum[adx] + bum[bdx];
			
			if (num == T) {
				
				long a = 1;
				while (adx < aum.length - 1 && aum[adx + 1] == aum[adx]) {
					adx++;
					a++;
				}
				int b = 1;
				while (bdx > 0 && bum[bdx - 1] == bum[bdx]) {
					bdx--;
					b++;
				}
				cnt += a * b;
				adx++;
				bdx--;
			}
			else if (num > T) bdx--;
			else adx++;
		}
		
		
		System.out.println(cnt);
	}
}
