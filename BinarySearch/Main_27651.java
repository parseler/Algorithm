import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_27651 {

	static int n;
	static long[] sum;
	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		sum = new long[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
		
		cnt = 0;
		
		
		for (int i = n - 1; i > 1; i--) {
			int start = 1;
			int end = i - 1;
			long ans = 0;
			
			while (start <= end) {
				int mid = (start + end) / 2;
				if (prob(mid, i)) {
					ans = mid;
					start = mid + 1;
				}
				else end = mid - 1;
			}
			cnt += ans;
		}

		System.out.println(cnt);
		
	}
	static boolean prob(int start, int end) {
		return sum[n] - sum[end] < sum[end] - sum[start] && sum[n] - sum[end] > sum[start];
	}
}
