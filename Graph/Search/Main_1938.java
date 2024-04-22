import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_1938 {
	
	static int n;
	static char[][] arr;
	static int[][][] visit;
	static int[] di = {1, -1, 0, 0};
	static int[] dj = {0, 0, 1, -1};
	static int[][] start;
	static int[][] end;
	static Queue<Train> queue;
	static Train endPoint;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		
		visit = new int[2][n][n];
		
		start = new int[3][2];
		end = new int[3][2];
		
		int idx1 = 0;
		int idx2 = 0;
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j);
				
				if (arr[i][j] == 'B') {
					start[idx1][0] = i;
					start[idx1++][1] = j;
				}			
				if (arr[i][j] == 'E') {
					end[idx2][0] = i;
					end[idx2++][1] = j;
				}
			}
		}
		
		queue = new ArrayDeque<>();
		
		if (start[0][0] == start[1][0]) {
			queue.offer(new Train(start[1][0], start[1][1], 0));
			visit[0][start[1][0]][start[1][1]] = 1;
		}
		else {
			queue.offer(new Train(start[1][0], start[1][1], 1));
			visit[1][start[1][0]][start[1][1]] = 1;
		}
		
		if (end[0][0] == end[1][0]) {
			endPoint = new Train(end[1][0], end[1][1], 0);
		}
		else {
			endPoint = new Train(end[1][0], end[1][1], 1);
		}
		
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		
		while (!queue.isEmpty()) {
			Train cur = queue.poll();
			if (cur.x == endPoint.x && cur.y == endPoint.y && cur.dir == endPoint.dir) {
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				
				if (!check(nx, ny, cur.dir)) continue;	
				if (visit[cur.dir][nx][ny] != 0) continue;
				
				visit[cur.dir][nx][ny] = visit[cur.dir][cur.x][cur.y] + 1;
				queue.offer(new Train(nx, ny, cur.dir));
			}
			
			if (!check(cur.x, cur.y, 1 - cur.dir)) continue;
			if (!bound(cur.x - 1, cur.y - 1)) continue;
			if (!bound(cur.x + 1, cur.y - 1)) continue;
			if (!bound(cur.x - 1, cur.y + 1)) continue;
			if (!bound(cur.x + 1, cur.y + 1)) continue;
			if (arr[cur.x - 1][cur.y - 1] == '1') continue;
			if (arr[cur.x + 1][cur.y - 1] == '1') continue;
			if (arr[cur.x - 1][cur.y + 1] == '1') continue;
			if (arr[cur.x + 1][cur.y + 1] == '1') continue;		
			
			if (visit[1 - cur.dir][cur.x][cur.y] != 0) continue;
			
			visit[1 - cur.dir][cur.x][cur.y] = visit[cur.dir][cur.x][cur.y] + 1;
			queue.offer(new Train(cur.x, cur.y, 1 - cur.dir));
		}
		
		return visit[endPoint.dir][endPoint.x][endPoint.y] == 0 ? 0 : visit[endPoint.dir][endPoint.x][endPoint.y] - 1;
	}
	
	static boolean check(int i, int j, int dir) {
		
		if (!bound(i, j)) return false;
		if (arr[i][j] == '1') return false;
		
		if (dir == 1) {
			if (!bound(i - 1, j)) return false;		
			if (arr[i - 1][j] == '1') return false;
			if (!bound(i + 1, j)) return false;
			if (arr[i + 1][j] == '1') return false;
		}	
		else if (dir == 0) {
			if (!bound(i, j - 1)) return false;
			if (arr[i][j - 1] == '1') return false;
			if (!bound(i, j + 1)) return false;
			if (arr[i][j + 1] == '1') return false;
		}
		
		return true;
		
	}
	static boolean bound(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < n;
	}
	
	static class Train {
		int x;
		int y;
		int dir;
		
		public Train(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Train [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}
	}
}
