import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1208 {
	
	static int n;
	static int s;
	static int[] arr;
	static int[] brr;
	static int[] asum;
	static int[] bsum;
	static boolean[] visit;
	static int idx;
	static int a;
	static int b;
	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		a = (n + 1) / 2;
		b = n / 2;
		
		arr = new int[a];
		brr = new int[b];
		
		for (int i = 0; i < a; i++) arr[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < b; i++) brr[i] = Integer.parseInt(st.nextToken());
		
		visit = new boolean[a];
		asum = new int[(int) Math.pow(2, a) - 1];
		idx = 0;
		powerA(0);
		
		visit = new boolean[b];
		bsum = new int[(int) Math.pow(2, b) - 1];
		idx = 0;
		powerB(0);
		
		Arrays.sort(asum);
		Arrays.sort(bsum);
		cnt = 0l;
		
		int al = asum.length;
		int bl = bsum.length;
		
		int start = 0;
		int end = bl - 1;
		
		while (start <= al - 1 && end >= 0) {

			if (asum[start] + bsum[end] == s) {

				int k = 0;
				while (start + k <= al - 1 && asum[start + k] + bsum[end] == s) {
					k++;
				}
				
				int l = 0;
				while (end - l >= 0 && bsum[end - l] + asum[start] == s) {
					l++;
				}
				
				cnt += (long) k * l;
				
				start += k;
				end -= l;
			}
			else if (asum[start] + bsum[end] > s) end--;
			else start++;
		}
		
		for (int i = 0; i < al; i++) {
			if (asum[i] == s) cnt++;
		}
		for (int i = 0; i < bl; i++) {
			if (bsum[i] == s) cnt++;
		}
		
		System.out.println(cnt);
	}
	static void powerA(int depth) {
		
		if (depth == a) {
			int sum = 0;
			
			for (int t = 0; t < a; t++) {
				if (visit[t]) sum += arr[t];
			}
			
			if (sum == 0) return;
			asum[idx++] = sum;
			return;
		}
		
		visit[depth] = true;
		powerA(depth + 1);
		visit[depth] = false;
		powerA(depth + 1);
	}
	
	static void powerB(int depth) {
		
		if (depth == b) {
			int sum = 0;
			
			for (int t = 0; t < b; t++) {
				if (visit[t]) sum += brr[t];
			}
			
			if (sum == 0) return;
			bsum[idx++] = sum;
			return;
		}
		
		visit[depth] = true;
		powerB(depth + 1);
		visit[depth] = false;
		powerB(depth + 1);
	}
}
