import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1493 {

	static int n;
	static long[] need = new long[20];
	static long[] have = new long[20];
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			have[Integer.parseInt(st.nextToken())] = Long.parseLong(st.nextToken());
		}

		check(w, h, l);
		
		System.out.println(ans);
	}
	
	static void check(int w, int h, int l) {
		
		if (w == 0 || h == 0 || l == 0) return;
		
		if (ans == -1) return;
		
		int use = -1;
		int k;
		
		for (int i = 19; i >= 0; i--) {
			if (have[i] == 0) continue;
			
			k = 1 << i;
			
			if (w < k || h < k || l < k) continue;
			
			have[i]--;
			use = k;
			break;
		}
		
		if (use != -1) {
			ans++;
			check(w, h, l - use);
			check(w - use, h, use);
			check(use, h - use, use);
		} else {
			ans = -1;
			return;
		}
		
	}
}
