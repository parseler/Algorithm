import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499 {

	static int n;
	static int m;
	static int k;
	static int[][] arr;
	static int[] dice = new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while (k-- > 0) {
			int cmd = Integer.parseInt(st.nextToken());
			
			switch (cmd) {
			case 1:
				if (bound(x, y + 1)) {
					east();
					change(x, ++y);
					sb.append(dice[2]).append('\n');
				}
				break;
			case 2:
				if (bound(x, y - 1)) {
					west();
					change(x, --y);
					sb.append(dice[2]).append('\n');
				}
				break;
			case 3:
				if (bound(x - 1, y)) {
					north();
					change(--x, y);
					sb.append(dice[2]).append('\n');
				}
				break;
			case 4:
				if (bound(x + 1, y)) {
					south();
					change(++x, y);
					sb.append(dice[2]).append('\n');
				}
				break;
			default:
				break;
			}
		}
		System.out.println(sb);
	}
	
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	static void east() {
		int k = dice[0];
		dice[0] = dice[5];
		dice[5] = dice[2];
		dice[2] = dice[4];
		dice[4] = k;
	}
	static void west() {
		int k = dice[0];
		dice[0] = dice[4];
		dice[4] = dice[2];
		dice[2] = dice[5];
		dice[5] = k;
	}
	static void north() {
		int k = dice[0];
		dice[0] = dice[3];
		dice[3] = dice[2];
		dice[2] = dice[1];
		dice[1] = k;
	}
	static void south() {
		int k = dice[0];
		dice[0] = dice[1];
		dice[1] = dice[2];
		dice[2] = dice[3];
		dice[3] = k;
	}
	static void change(int x, int y) {
		if (arr[x][y] == 0) {
			arr[x][y] = dice[0];
		} else {
			dice[0] = arr[x][y];
			arr[x][y] = 0;
		}
	}
}