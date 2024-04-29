import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2263 {
	
	static int n;
	static int[] inOrderIdx;
	static int[] inOrder;
	static int[] postOrder;
	static StringBuilder sb;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        inOrderIdx = new int[n + 1];
        inOrder = new int[n + 1];
        postOrder = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
        	inOrderIdx[inOrder[i] = Integer.parseInt(st.nextToken())] = i;
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) postOrder[i] = Integer.parseInt(st.nextToken());
        
        preOrder(1, n);
        System.out.println(sb);
    }
    static void preOrder(int start, int end) {
    	
    	if (start > end) return;
    	
    	sb.append(postOrder[end]).append(' ');
    	if (start == end) return;
    	
    	int idx = inOrderIdx[postOrder[end]];
    	
    	int s = start;
    	int e = end - 1;
    	int mid;
    	int leftTreeIdx = -1;
    	
    	while (s <= e) {
    		mid = (s + e) / 2;
    		
    		if (inOrderIdx[postOrder[mid]] < idx) {
    			s = mid + 1;
    			leftTreeIdx = mid;
    		} else {
    			e = mid - 1;	
    		}
    	}
    	
    	if (leftTreeIdx == -1) {
    		preOrder(start, end - 1);
    		return;
    	}
    	
    	preOrder(start, leftTreeIdx);
    	preOrder(leftTreeIdx + 1, end - 1);
    }
}