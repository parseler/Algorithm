import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2655 {

	static int n;
	static Block[] arr;
	static Block[] brr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new Block[n];
		brr = new Block[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			arr[i] = new Block(i, w, h, s);
			brr[i] = new Block(i, w, h, s);
		}
		
		Arrays.sort(arr);
		
		int[] dp = new int[10001];
		int[] saveIdx = new int[10001];
		int[] currentIdx = new int[10001];
		
		int weight = 0;
		int height = 0;
		
		for (int i = 0; i < n; i++) {
			Block cur = arr[i];
			
			for (int j = cur.w; j <= 10000; j++) {
				if (dp[cur.w] < dp[j] + cur.h) {
					dp[cur.w] = dp[j] + cur.h;
					
					if (dp[cur.w] > height) {
						height = dp[cur.w];
						weight = cur.w;
					}
					
					currentIdx[cur.w] = cur.idx;
					saveIdx[cur.w] = currentIdx[j]; 
				}
			}
		}
		
		List<Integer> list = new ArrayList<>();
		
		while (height != 0) {
			list.add(currentIdx[weight]);
			
			height -= brr[currentIdx[weight]].h;
			weight = brr[saveIdx[weight]].w;
		}
		
		sb.append(list.size()).append('\n');
		for (int i : list) {
			sb.append(i + 1).append('\n');
		}
		System.out.println(sb);
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
