import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main_5639 {

	static List<Integer> list;
	static int[] bigger;
	static List<Integer> ans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        list = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null && !str.equals("")) list.add(Integer.parseInt(str));
        
        Deque<Point> dq = new ArrayDeque<>();
        int n = list.size();
        bigger = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
        	Point cur = new Point(list.get(i), i);
        	
        	while (!dq.isEmpty() && dq.peekLast().x < cur.x) {
        		dq.pollLast();
        	}
        	
        	if (dq.isEmpty()) bigger[i] = Integer.MAX_VALUE;
        	else bigger[i] = dq.peekLast().y;
        	dq.add(cur);
        }
        
        ans = new ArrayList<>();
        postOrder(0, n - 1);

        for (int i : ans) sb.append(i).append('\n');
        System.out.println(sb);
    }
    
    static void postOrder(int start, int end) {
    	if (start > end) return;

    	if (start == end) {
    		ans.add(list.get(start));
    		return;
    	}
 
    	if (bigger[start] == Integer.MAX_VALUE || bigger[start] > end) {
    		postOrder(start + 1, end);
    	}
    	else {
    		postOrder(start + 1, bigger[start] - 1);
    		postOrder(bigger[start], end);
    	}
    	postOrder(start, start);
    }
}