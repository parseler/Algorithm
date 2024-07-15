import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1863 {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		int ans = 0;
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			st.nextToken();
			int h = Integer.parseInt(st.nextToken());
			
			while (!dq.isEmpty() && dq.peekLast() >= h) {
				if (dq.pollLast() != h) ans++;
			}
			if (h != 0) dq.offer(h);
		}

		System.out.println(ans + dq.size());
	}
}