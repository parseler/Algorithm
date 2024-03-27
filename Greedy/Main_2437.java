import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2437 {
	
	static int n;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<>();
		while (n-- > 0) pq.offer(Integer.parseInt(st.nextToken()));
		
		int size = 0;
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			if (size + 1 < cur) break;
			
			size += cur;
		}
		System.out.println(size + 1);
	}
}