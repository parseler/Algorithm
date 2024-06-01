import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2056 {
	
	static int n;
	static Work[] works;
 	static int[] degree;
 	static int[] takenTime;
 	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		works = new Work[n + 1];
		degree = new int[n + 1];
		takenTime = new int[n + 1];
		graph = new List[n + 1];
		for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		PriorityQueue<Work> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());			
			int cnt = Integer.parseInt(st.nextToken());
			degree[i] = cnt;
			takenTime[i] = t;
			
			if (cnt == 0) {
				pq.offer(new Work(i, t, 0));
			}
			
			while (cnt-- > 0) {
				int work = Integer.parseInt(st.nextToken());
				graph[work].add(i);
			}
		}
		
		int day = 0;
		
		while (!pq.isEmpty()) {
			Work cur = pq.poll();
			day = cur.start + cur.time;
			for (int next : graph[cur.num]) {
				if (--degree[next] == 0) {
					pq.offer(new Work(next, takenTime[next], day));
				}
			}
		}
		System.out.println(day);
	}
	
	static class Work implements Comparable<Work> {
		int num;
		int time;
		int start;
		
		public Work(int num, int time, int start) {
			this.num = num;
			this.time = time;
			this.start = start;
		}

		@Override
		public int compareTo(Work o) {
			return Integer.compare(this.time + this.start, o.time + o.start);
		}
	}
}
