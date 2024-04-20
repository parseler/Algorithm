import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2629 {
	
	static int n;
	static int m;
	static boolean[] weight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		n = Integer.parseInt(br.readLine());
		
		weight = new boolean[15001];
		weight[0] = true;
		
		st = new StringTokenizer(br.readLine());	
		for (int i = 0; i < n; i++) {
			int w = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j <= 15000; j++) {
				if (!weight[j]) continue;
				
				if (j + w <= 15000 && !weight[j + w]) queue.offer(j + w);
				if (!weight[Math.abs(j - w)]) queue.offer(Math.abs(j - w));
			}
			
			while (!queue.isEmpty()) weight[queue.poll()] = true;	
		}
		
		m = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int w = Integer.parseInt(st.nextToken());
			
			if (w <= 15000 && weight[w]) sb.append('Y').append(' ');
			else sb.append('N').append(' ');
		}
		System.out.println(sb);
	}
}