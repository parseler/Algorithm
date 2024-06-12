import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2138 {
	
	static int n;
	static int[] arr;
	static int[] basic;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		for (int i = 0; i < n; i++) {
			arr[i] = str1.charAt(i) == str2.charAt(i) ? 0 : 1;
		}
		
		basic = new int[n];
		
		int cnt1 = 0;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] == basic[i]) continue;
			
			cnt1++;
			basic[i] = 1 - basic[i];
			basic[i + 1] = 1 - basic[i + 1];
			if (i + 2 < n) basic[i + 2] = 1 - basic[i + 2];
		}
		
		if (arr[n - 1] != basic[n - 1]) cnt1 = Integer.MAX_VALUE;
		
		basic = new int[n];
		arr[0] = 1 - arr[0];
		arr[1] = 1 - arr[1];
		
		int cnt2 = 1;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] == basic[i]) continue;
			
			cnt2++;
			basic[i] = 1 - basic[i];
			basic[i + 1] = 1 - basic[i + 1];
			if (i + 2 < n) basic[i + 2] = 1 - basic[i + 2];
		}
		
		if (arr[n - 1] != basic[n - 1]) cnt2 = Integer.MAX_VALUE;
		
		System.out.println(Math.min(cnt1, cnt2) == Integer.MAX_VALUE ? -1 : Math.min(cnt1, cnt2));
	}
}
