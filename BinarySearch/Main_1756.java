import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1756 {

	static int d;
	static int n;
	static int[] oven;
	static int pizza;
	static int start;
	static int end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		d = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		if (d < n) {
			System.out.println(0);
			return;
		}
		
		oven = new int[d + 1];
		oven[0] = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= d; i++) {
			int o = Integer.parseInt(st.nextToken());
			if (o > oven[i - 1]) oven[i] = oven[i - 1];
			else oven[i] = o;	
		}

		st = new StringTokenizer(br.readLine());
		end = d;
		for (int i = 0; i < n; i++) {
			pizza = Integer.parseInt(st.nextToken());
			start = 1;
			if ((end = binarySearch() - 1) == -1) break;
		}
		
		System.out.println(end + 1);
		
	}
	static int binarySearch() {
		int mid;
		int ans = 0;
		
		while (start <= end) {
			mid = (start + end) / 2;
			if (oven[mid] >= pizza) {
				ans = mid;
				start = mid + 1;
			}
			else end = mid - 1;
		}
		
		return ans;
	}
}
