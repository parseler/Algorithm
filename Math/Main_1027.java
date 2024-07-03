import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1027 {
	
	static int n;
	static int[] arr;
	static int[] res;
	static final int INF = -1_000_000_001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		res = new int[n];
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			
			long height = INF;
			long dist = 0;
			
			for (int j = i - 1; j >= 0; j--) {
				long h = arr[j] - arr[i];
				long d = i - j;
				
				if (height * d < h * dist) {
					res[i]++;
					height = h;
					dist = d;
				}
			}

			height = INF;
			dist = 0;
			
			for (int j = i + 1; j < n; j++) {
				int h = arr[j] - arr[i];
				int d = j - i;
				
				if (height * d < h * dist) {
					res[i]++;
					height = h;
					dist = d;
				}
			}
			
			ans = Math.max(ans, res[i]);
		}
		
		System.out.println(ans);
	}
}
