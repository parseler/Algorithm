import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9934 {
	
	static int n;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[1 << n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= (1 << n) - 1; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {1, (1 << n) - 1, 1});
		
		int lev = 1;
		int mid;
		int[] cur;
		StringBuilder sb = new StringBuilder();
		
		while (!queue.isEmpty()) {
			cur = queue.poll();
			
			if (cur[1] < cur[0]) continue;
			
			mid = (cur[0] + cur[1]) / 2;
			
			if (cur[2] > lev) {
				sb.append('\n');
				lev++;
			}
			
			sb.append(arr[mid]).append(' ');
			
			queue.offer(new int[] {cur[0], mid - 1, cur[2] + 1});
			queue.offer(new int[] {mid + 1, cur[1], cur[2] + 1});
		}
		
		System.out.println(sb);
		
	}
}