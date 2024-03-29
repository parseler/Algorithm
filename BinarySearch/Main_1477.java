import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1477 {

	static int n;
	static int m;
	static int l;
	static PriorityQueue<Integer> rest;
	static int[] space;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		int min = l / (n + m);
		
		if (n != 0) st = new StringTokenizer(br.readLine());
		
		rest = new PriorityQueue<>();
		rest.offer(l);
		for (int i = 0; i < n; i++) rest.offer(Integer.parseInt(st.nextToken()));
		
		int cur = 0;
		int next;

		int idx = 0;
		space = new int[n + 1];
		while (!rest.isEmpty()) {
			next = rest.poll();
			space[idx++] = next - cur;
			cur = next;
		}
		Arrays.sort(space);
		
		int start = 1;
		int end = l;
		int mid = 0;
		int ans = 0;
		
		while (end >= start) {
			mid = (start + end) / 2;
			if (prob(mid)) {
				end = mid - 1;
				ans = mid;
			}
			else {
				start = mid + 1;
			}
		}
		System.out.println(ans);
	}
	static boolean prob(int x) {
		int cnt = 0;
		for (int i = n; i >= 0; i--) {
			if (space[i] <= x) break;
			
			int k = space[i] / x;
			if (space[i] % x == 0) cnt += k - 1;
			else cnt += k;
			
			if (cnt > m) return false;
		}
		
		return true;
	}
}
