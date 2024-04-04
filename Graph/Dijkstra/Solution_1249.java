import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_1249 {

    static int n;
    static int[][] arr;
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static PriorityQueue<Edge> pq;
    static boolean[][] visit;
    static int[][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');
            
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = str.charAt(j) - '0';
                }
            }
            
            pq = new PriorityQueue<>();
            
            pq.offer(new Edge(0, 0, 0));
            
            visit = new boolean[n][n];
            dp = new int[n][n];
            dp[0][0] = 0;
            for (int i = 0; i < n; i++) {
            	Arrays.fill(dp[i], INF);
            }
            
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                
                if (visit[cur.toX][cur.toY]) continue;
                visit[cur.toX][cur.toY] = true;
                
                for (int d = 0; d < 4; d++) {
                    int nx = cur.toX + di[d];
                    int ny = cur.toY + dj[d];
                    
                    if (!bound(nx, ny)) continue;
                    
                    dp[nx][ny] = Math.min(dp[nx][ny], cur.w + arr[nx][ny]);
                    pq.offer(new Edge(nx, ny, dp[nx][ny]));
                }
            }
            sb.append(dp[n - 1][n - 1]).append('\n');
        }
        
        System.out.println(sb);
    }
    
    static boolean bound(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    
    static class Edge implements Comparable<Edge> {
        int toX;
        int toY;
        int w;
        
        public Edge(int toX, int toY, int w) {
            this.toX = toX;
            this.toY = toY;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}