import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1026 {
	
	static int n;
	static int[] a;
	static int[] b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		b = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) b[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(a);
		Arrays.sort(b);
		
		int s = 0;
		
		for (int i = 0; i < n; i++) {
			s += a[i] * b[n - 1 - i];
		}
		System.out.println(s);
	}
}
