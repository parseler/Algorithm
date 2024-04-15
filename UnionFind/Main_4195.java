import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195 {
	
	static int n;
	static int[] parent;
	static int[] count;
	static Map<String, Integer> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			makeParentAndCount();
			map = new HashMap<>();
			
			while (n-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				int group1 = findGroup(st.nextToken());
				int group2 = findGroup(st.nextToken());
				
				sb.append(union(group1, group2)).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	static int findGroup(String str) {
		if (!map.containsKey(str)) map.put(str, map.size() + 1);	
		return map.get(str);
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static int union(int group1, int group2) {
		int x = find(group1);
		int y = find(group2);
		
		if (x != y) {
			parent[y] = x;
			count[x] += count[y];
		}
		
		return count[x];
	}
	
	static void makeParentAndCount() {
		parent = new int[n * 2 + 1];
		count = new int[n * 2 + 1];
		
		for (int i = 1; i <= n * 2; i++) {
			parent[i] = i;
			count[i] = 1;
		}	
	}
}
