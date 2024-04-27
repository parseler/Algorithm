import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_17299 {
	
	static int n;
	static int[] arr;
	static int[] ans;
	static final int[] count = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			count[arr[i]]++;
		}
		
		ans = new int[n];
		Deque<Integer> queue = new ArrayDeque<>();
		
		for (int i = n - 1; i >= 0; i--) {
			
			while (!queue.isEmpty() && count[queue.peekLast()] <= count[arr[i]]) queue.pollLast();
			
			if (queue.isEmpty()) ans[i] = -1;
			else ans[i] = queue.peekLast();
			queue.add(arr[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) sb.append(ans[i]).append(' ');
		System.out.println(sb);
	}
}