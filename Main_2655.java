import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2655 {

	static int n;
	static Block[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new Block[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			arr[i] = new Block(i + 1, w, h, s);
		}
		
		Arrays.sort(arr);
		
		int[] dp = new int[10001];
		// 수정
		int[] save = new int[100];
		
		for (int i = 0; i < n; i++) {
			Block cur = arr[i];
			for (int j = cur.w; j <= 10000; j++) {
				if (dp[cur.w] < dp[j] + cur.h) {
					dp[cur.w] = dp[j] + cur.h;
					// 수정
					save[cur.w] = cur.idx;
				}
			}
		}
		
		System.out.println(Arrays.toString(dp));
	}
	
	static class Block implements Comparable<Block> {
		int idx;
		int w;
		int h;
		int s;
		
		public Block(int idx, int w, int h, int s) {
			this.idx = idx;
			this.w = w;
			this.h = h;
			this.s = s;
		}

		@Override
		public int compareTo(Block o) {
			if (this.s == o.s) return Integer.compare(o.w, this.w); 
			return Integer.compare(o.s, this.s);
		}
		
		
	}
}
