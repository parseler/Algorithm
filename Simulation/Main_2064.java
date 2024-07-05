import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2064 {

	static int n;
	static int[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		sum = new int[32];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			
			make(str);
		}
		
		int m = 0;
		for (int i = 0; i < 32; i++) {
			if (sum[i] == 0 || sum[i] == n) {
				sum[i] /= n;
				continue;
			}
			
			m = 32 - i;
			break;
		}
		
		for (int i = 31; i > 31 - m; i--) sum[i] = 0;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(makeNet()).append('\n');
		for (int i = 0; i < 32 - m; i++) sum[i] = 1;
		sb.append(makeNet()).append('\n');
		
		System.out.println(sb);
	}
	
	private static String makeNet() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= 4; i++) {
			int k = i * 8 - 1;
			int s = 1;
			int num = 0;
			
			for (int j = 0; j < 8; j++) {
				num += s * sum[k--];
				s *= 2;
			}
			
			sb.append(num).append('.');
		}
		
		return sb.toString().substring(0, sb.length() - 1);
	}

	static void make(String str) {
		
		int k = 1;
		int num = 0;
		int pt = 4;
		for (int i = str.length() - 1; i >= -1; i--) {
			if (i == -1 || str.charAt(i) == '.') {
				
				makeSum(num, pt);
				k = 1;
				num = 0;
				
				pt--;
				continue;
			}
			
			num += k * (str.charAt(i) - '0');
			k *= 10;
		}
	}

	private static void makeSum(int num, int pt) {
		
		int idx = pt * 8 - 1;
		while (num != 0) {
			sum[idx--] += num % 2;
			num /= 2;
		}
	}
}