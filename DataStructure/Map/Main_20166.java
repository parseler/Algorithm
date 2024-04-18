import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20166 {
	
	static int n;
	static int m;
	static int k;
	static char[][] arr;
	static Map<String, Integer> map;
	static int[] di = {1, -1, 0, 0, 1, -1, 1, -1};
	static int[] dj = {0, 0, 1, -1, 1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		
		String str;
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		makeMap();

		while (k-- > 0) {
			str = br.readLine();
			if (map.containsKey(str)) sb.append(map.get(str));
			else sb.append(0);
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void makeMap() {
		map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				StringBuilder sb = new StringBuilder();
				sb.append(arr[i][j]);
				findStr(i, j, sb);			
			}
		}
	}
	
	static void findStr(int x, int y, StringBuilder sb) {
		if (sb.length() == 6) {
			return;
		}

		if (!map.containsKey(sb.toString())) {
			map.put(sb.toString(), 1);
		}
		else map.put(sb.toString(), map.get(sb.toString()) + 1);
		
		for (int d = 0; d < 8; d++) {
			int nx = (x + di[d]) % n < 0 ? (x + di[d]) % n + n : (x + di[d]) % n;
			int ny = (y + dj[d]) % m < 0 ? (y + dj[d]) % m + m : (y + dj[d]) % m;
			sb.append(arr[nx][ny]);
			findStr(nx, ny, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
	
	
}
