import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_28427 {
	
	static int q;
	static boolean[] isNotPrime;
	static int[] prefix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		makePrime();
		
		prefix = new int[500000];
		
		for (int i = 2; i <= 499999; i++) {
			prefix[i] = prefix[i - 1] + (isNotPrime[i * 2 + 1] ? 0 : 1);
		}
		
		q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(prefix[end - 1] - prefix[stt - 1]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void makePrime() {
		isNotPrime = new boolean[1000000];
		isNotPrime[2] = false;
		
		for (int i = 2; i <= 999999; i++) {
			if (!isNotPrime[i]) {
				for (int j = i * 2; j <= 999999; j += i) {
					isNotPrime[j] = true;
				}
			}
		}
	}
}
