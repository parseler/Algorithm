import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11000 {
	
	static int n;
	static PriorityQueue<Point> pq;
	static PriorityQueue<Integer> end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((o1, o2) ->  {
			if (o1.x == o2.x) return o1.y - o2.y;
			return o1.x - o2.x;
			});
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			pq.add(new Point(s, t));
		}
		
		end = new PriorityQueue<>();
		end.offer(0);
		
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			if (end.peek() <= cur.x) {
				end.poll();
				end.offer(cur.y);
			} else {
				end.offer(cur.y);
			}
		}
		
		System.out.println(end.size());
		
	}
}
