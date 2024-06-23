import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500 {

	static int n;
	static int m;
	static int[][] arr;
	static int[][] hix;
	static int[][] vix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 3][m + 3];
		hix = new int[n + 3][m + 3];
		vix = new int[n + 3][m + 3];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				hix[i][j] = hix[i][j - 1] + arr[i][j];
				vix[i][j] = vix[i - 1][j] + arr[i][j];
			}
		}
		
		int ans = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				
				if (j >= 3) {
					int res = hix[i][j] - hix[i][j - 3];
					
					int one = Math.max(arr[i - 1][j], Math.max(arr[i - 1][j - 1], arr[i - 1][j - 2]));
					int two = Math.max(arr[i + 1][j], Math.max(arr[i + 1][j - 1], arr[i + 1][j - 2]));
					
					res += Math.max(arr[i][j + 1], Math.max(one, two));
					ans = Math.max(ans, res);
				}
				
				if (i >= 3) {
					int res = vix[i][j] - vix[i - 3][j];
					
					int one = Math.max(arr[i][j - 1], Math.max(arr[i - 1][j - 1], arr[i - 2][j - 1]));
					int two = Math.max(arr[i][j + 1], Math.max(arr[i - 1][j + 1], arr[i - 2][j + 1]));
					
					res += Math.max(arr[i + 1][j], Math.max(one, two));
					ans = Math.max(ans, res);
				}
				
				
				int a = arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
				int b = arr[i + 2][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
				int c = arr[i][j + 2] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
				int d = arr[i][j] + arr[i + 2][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1];
				int e = arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 2] + arr[i + 1][j + 1];
				
				ans = Math.max(ans, Math.max(a, Math.max(b, Math.max(c, Math.max(d, e)))));				
			}
		}
		
		System.out.println(ans);
		
	}
}