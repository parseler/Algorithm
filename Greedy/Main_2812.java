import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_2812 {
	
	static int n;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		
		int size = n - k;
		Deque<Integer> dq = new ArrayDeque<>();
		
		run:
		for (int i = 0; i < n; i++) {
			int cur = str.charAt(i) - '0';
			
			while (!dq.isEmpty()) {
				
				if (n - i + dq.size() == size) {
					for (int j = i; j < n; j++) dq.add(str.charAt(j) - '0');
					break run;
				}
				
				if (dq.peekLast() < cur) dq.pollLast();
				else break;
				
			}
			if (size != dq.size()) dq.add(cur);
		}
		
		StringBuilder sb = new StringBuilder();
		while (!dq.isEmpty()) sb.append(dq.poll());
		System.out.println(sb);
	}
}
