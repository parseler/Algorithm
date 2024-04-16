import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1525 {
	
	static long arr;
	static Map<Long, Integer> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = 10000000000L;
		int idx = 10;
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				long k = Long.parseLong(st.nextToken());
				arr += k * idx;
				idx *= 10;
				
				if (k == 0L) arr += i * 3 + j + 1;
			}
		}
		
		makeMap();
		if (map.containsKey(arr)) System.out.println(map.get(arr));
		else System.out.println(-1);
	}
	
	static void makeMap() {
		map = new HashMap<>();
		map.put(10876543219L, 0);
		
		Queue<Long> queue = new ArrayDeque<>();
		queue.offer(10876543219L);
		
		int nextIdx;
		long next;
		while (!queue.isEmpty()) {
			long cur = queue.poll();
			
			int count = map.get(cur) + 1;
			
			int zero = (int) (cur % 10);
			
			if (zero + 1 <= 9 && zero % 3 != 0) {
				nextIdx = zero + 1;
				long k = (cur / multiply(nextIdx)) % 10;
				
				next = cur - k * (multiply(nextIdx)) + k * (multiply(zero));
				next = next - zero + nextIdx;
				
				if (!map.containsKey(next)) {
					map.put(next, count);
					queue.offer(next);
				}
			}
			
			if (zero - 1 >= 1 && zero % 3 != 1) {
				nextIdx = zero - 1;
				long k = (cur / multiply(nextIdx)) % 10;
				
				next = cur - k * (multiply(nextIdx)) + k * (multiply(zero));
				next = next - zero + nextIdx;
				
				if (!map.containsKey(next)) {
					map.put(next, count);
					queue.offer(next);
				}
			}
			
			if (zero + 3 <= 9) {
				nextIdx = zero + 3;
				long k = (cur / multiply(nextIdx)) % 10;
	
				next = cur - k * (multiply(nextIdx)) + k * (multiply(zero));
				next = next - zero + nextIdx;
				
				if (!map.containsKey(next)) {
					map.put(next, count);
					queue.offer(next);
				}
			}
			
			if (zero - 3 >= 1) {
				nextIdx = zero - 3;
				long k = (cur / multiply(nextIdx)) % 10;

				next = cur - k * (multiply(nextIdx)) + k * (multiply(zero));
				next = next - zero + nextIdx;
				
				if (!map.containsKey(next)) {
					map.put(next, count);
					queue.offer(next);
				}
			}
		}
	}
	
	static long multiply(int idx) {
		return (long) Math.pow(10, idx);
	}
}