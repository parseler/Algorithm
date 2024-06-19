import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14698 {

	static int n;
	static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		long ans = m;
		PriorityQueue<Position> pq = new PriorityQueue<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if (u <= v) continue;
			
			pq.offer(new Position(v, u));
		}
		
		long sum = 0L;
		int end = 0;
		
		while (!pq.isEmpty()) {
			Position cur = pq.poll();
			
			if (cur.s >= end) {
				end = cur.e;
				sum += cur.e - cur.s;
				continue;
			}
			
			if (end >= cur.e) continue;
			
			sum += cur.e - end;
			end = cur.e;
		}
		
		System.out.println(ans + sum * 2);
	}
	
	static class Position implements Comparable<Position> {
		int s;
		int e;
		
		public Position(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.s, o.s);
		}
	}
}