import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136 {
	
	static int[][] arr = new int[10][10];
	static int[] page = {0, 5, 5, 5, 5, 5};
	static int ans = Integer.MAX_VALUE;
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) total++;
			}
		}
		
		back(0, 0, 0, 0);
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void back(int i, int j, int cnt, int res) {
		
		if (res >= ans) return;
		
		if (j == 10) {
			i++;
			j = 0;
		}
		
		if (i == 10) {
			if (ans > res) ans = res;
			return;
		}
		
		if (cnt == total) {
			if (ans > res) ans = res;
			return;
		}
		
		if (arr[i][j] != 1) {
			back(i, j + 1, cnt, res);
		} else {
			for (int s = 5; s >= 1; s--) {
				if (isPage(i, j, s)) {
					attach(i, j, s, 2);
					page[s]--;
					back(i, j + 1, cnt + s * s, res + 1);
					attach(i, j, s, 1);
					page[s]++;
				}
			}
		}
	}

	static boolean isPage(int i, int j, int size) {
		if (page[size] == 0) return false;
		
		for (int x = i; x < i + size; x++) {
			for (int y = j; y < j + size; y++) {
				if (check(x, y) && arr[x][y] == 1) continue;
				return false;
			}
		}
		return true;
	}
	
	static void attach(int i, int j, int size, int k) {
		for (int x = i; x < i + size; x++) {
			for (int y = j; y < j + size; y++) {
				arr[x][y] = k;
			}
		}
	}

	
	static boolean check(int i, int j) {
		return i >= 0 && i < 10 && j >= 0 && j < 10;
	}

}