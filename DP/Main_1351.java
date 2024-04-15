import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1351 {
	
	static long n;
	static int p;
	static int q;
	static Map<Long, Long> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Long.parseLong(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		map = new HashMap<Long, Long>();
		map.put(0L, 1L);
		
		System.out.println(find(n));
		
	}
	
	static long find(long x) {
		if (map.containsKey(x)) return map.get(x);
		
		long a = find(x / p);
		long b = find(x / q);
		
		map.put(x, a + b);
		
		return a + b;
	}
}
