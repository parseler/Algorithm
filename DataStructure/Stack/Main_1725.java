import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1725 {

	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		stack.add(arr[0]);
		
		int ans = 0;
		
		for (int i = 1; i < n; i++) {
			
			int cnt = 0;
			
			while (!stack.isEmpty() && stack.peek() > arr[i]) {
				cnt++;
				ans = Math.max(ans, stack.pop() * cnt);
			}
			
			for (int j = 0; j <= cnt; j++) stack.add(arr[i]);
		}
		
		int len = 1;
		while (!stack.isEmpty()) {
			int k = stack.pop();
			ans = Math.max(ans, k * len);
			len++;
		}
		
		System.out.println(ans);
	}
}
