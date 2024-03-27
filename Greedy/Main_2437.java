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
		for (int i = 0; i < n; i++) pq.offer(Integer.parseInt(st.nextToken()));

		int size = 1;

		run:
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			int currentSize = size - 1;
			for (int i = 0; i <= currentSize; i++) {
				if (cur + i > size) {
					break run;
				} else if (cur + i == size) size++;
			}
		}
		System.out.println(size);
	}
}