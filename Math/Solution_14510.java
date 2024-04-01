import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510 {

	static int n;
	static int[] arr;
	static int max;
	static int one;
	static int two;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		r: for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(' ');
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[n];
			max = 0;
			int one = 0;
			int two = 0;

			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (max < arr[i]) max = arr[i];
			}
			for (int i = 0; i < n; i++) {
				arr[i] = max - arr[i];
				two += arr[i] / 2;
				one += arr[i] % 2;
			}

			if (one > two) {
				sb.append(one * 2 - 1).append('\n');
				continue;
			}
			
			while (one < two) {
				one += 2;
				two--;
			}
			
			if (one - two == 2) {
				one -= 2;
				two++;
				sb.append(one * 2 + 2);
			}
			else sb.append(one + two);
			sb.append('\n');
			
		}
		System.out.println(sb);
	}
}