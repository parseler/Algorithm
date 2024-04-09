import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2550 {
	
	static int n;
	static int[] seq;
	static int[] lis;
	static int[] light;
	static int[] arr;
	static int[] index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		
		seq = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) seq[Integer.parseInt(st.nextToken())] = i;
		
		st = new StringTokenizer(br.readLine());
		
		light = new int[n + 1];
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			light[i] = Integer.parseInt(st.nextToken());
			arr[i] = seq[light[i]];
		}

		lis = new int[n + 1];
		index = new int[n + 1];
		
		int idx = 0;
		for (int i = 1; i <= n; i++) {
			if (lis[idx] <= arr[i]) {
				index[i] = ++idx;
				lis[idx] = arr[i];
				continue;
			}
			
			int start = 0;
			int end = idx;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (lis[mid] > arr[i]) end = mid - 1;
				else start = mid + 1;
			}
			lis[end + 1] = arr[i];
			index[i] = end + 1;
		}

		sb.append(idx).append('\n');
		
		int[] ans = new int[idx];
		for (int i = n; i >= 1; i--) {
			if (index[i] == idx) {
				ans[--idx] = light[i];
			}
		}
		Arrays.sort(ans);
		for (int i = 0; i < ans.length; i++) sb.append(ans[i]).append(' ');
		System.out.println(sb);
		
	}
}