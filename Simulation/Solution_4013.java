import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013 {
	
	static int k;
	static int[][] magnet;
	static int ans;
	static int[] ro;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			sb.append('#').append(t).append(' ');
			
			k = Integer.parseInt(br.readLine());
			
			magnet = new int[4][9];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				ro = new int[4];
				
				boolean[] visit = new boolean[4];
				visit[idx] = true;
				find(idx, dir, visit);
				for (int i = 0; i < 4; i++) {
					if (ro[i] == 0) continue;
					rotate(i, ro[i]);
				}
			}
			if (magnet[0][0] == 1) ans += 1;
			if (magnet[1][0] == 1) ans += 2;
			if (magnet[2][0] == 1) ans += 4;
			if (magnet[3][0] == 1) ans += 8;
			sb.append(ans).append('\n');
		}
		System.out.println(sb);

	}
	static void rotate(int idx, int dir) {
		if (dir == 1) {
			for (int i = 7; i >= 0; i--) magnet[idx][i + 1] = magnet[idx][i];
			magnet[idx][0] = magnet[idx][8];
		} else {
			magnet[idx][8] = magnet[idx][0];
			for (int i = 0; i <= 7; i++) magnet[idx][i] = magnet[idx][i + 1];
		}
	}
	
	static void find(int idx, int dir, boolean[] visit) {
		ro[idx] = dir;
		if (idx > 0 && magnet[idx][6] != magnet[idx - 1][2] && !visit[idx - 1]) {
			visit[idx - 1] = true;
			find(idx - 1, -dir, visit);
		}
		if (idx < 3 && magnet[idx + 1][6] != magnet[idx][2] && !visit[idx + 1]) {
			visit[idx + 1] = true;
			find(idx + 1, -dir, visit);
		}
	}
}