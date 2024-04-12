import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1744 {
	
	static int n;
	static int[] arr;
	static int[] dp;
	static int ans = 0;
	static int zero;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] == 0) zero++;
		}
		
		if (n == 1) {
			System.out.println(arr[0]);
			return;
		}
		
		Arrays.sort(arr);
		
		int idx = n - 1;
		while (idx > 0 && arr[idx] > 1 && arr[idx - 1] > 1) {
			ans += arr[idx] * arr[idx - 1];
			idx -= 2;
		}
		
		while (idx >= 0 && arr[idx] > 0) ans += arr[idx--];
		
		idx = 0;
		while (idx < n - 1 && arr[idx] <= 0 && arr[idx + 1] <= 0) {
			ans += arr[idx] * arr[idx + 1];
			idx += 2;
		}
		
		while (idx < n && arr[idx] < 0) ans += arr[idx++];
		
		System.out.println(ans);
	}
}
