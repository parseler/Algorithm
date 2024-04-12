import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1461 {
	
	static int n;
	static int m;
	static PriorityQueue<Integer> plus;
	static PriorityQueue<Integer> minus;
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		plus = new PriorityQueue<>(Collections.reverseOrder());
		minus = new PriorityQueue<>(Collections.reverseOrder());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			if (k > 0) plus.add(k);
			else minus.add(-k);
		}
		
		int a = plus.isEmpty() ? 0 :plus.peek();
		int b = minus.isEmpty() ? 0 : minus.peek();
		
		int cnt = 0;
		while (!plus.isEmpty()) {
			int k = plus.poll();
			if (cnt++ % m == 0) {
				ans += k * 2;
			}
		}

		cnt = 0;
		while (!minus.isEmpty()) {
			int k = minus.poll();
			if (cnt++ % m == 0) {
				ans += k * 2;
			}
		}
		
		ans -= Math.max(a, b);
	
		System.out.println(ans);
	}
}
