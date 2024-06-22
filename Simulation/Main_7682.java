import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_7682 {

	static Map<Integer, String> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new HashMap<>();
		comb(0, new int[9]);

		StringBuilder sb = new StringBuilder();
		String str = null;
		while (!(str = br.readLine()).equals("end")) {
			sb.append(map.get(trit(str))).append('\n');
		}

		System.out.println(sb);
	}

	private static void comb(int depth, int[] arr) {

		if (depth == 9) {
			if (check(arr)) {
				map.put(trit(arr), "valid");
			} else {
				map.put(trit(arr), "invalid");
			}
			return;
		}

		for (int i = 0; i <= 2; i++) {
			arr[depth] = i;
			comb(depth + 1, arr);
		}
	}

	private static boolean check(int[] arr) {
		int cntO = 0;
		int cntX = 0;

		for (int i = 0; i < 9; i++) {
			if (arr[i] == 1)
				cntX++;
			else if (arr[i] == 2)
				cntO++;
		}

		if (cntX < cntO)
			return false;
		if (cntX > cntO + 1)
			return false;

		int[] count = new int[9];
		int[] bingo = new int[2];

		for (int i = 0; i <= 6; i += 3) {
			if (arr[i] == arr[i + 1] && arr[i] == arr[i + 2] && arr[i] != 0) {
				count[i]++;
				count[i + 1]++;
				count[i + 2]++;
				bingo[arr[i] - 1]++;
			}
		}

		for (int i = 0; i <= 2; i++) {
			if (arr[i] == arr[i + 3] && arr[i] == arr[i + 6] && arr[i] != 0) {
				count[i]++;
				count[i + 3]++;
				count[i + 6]++;
				bingo[arr[i] - 1]++;
			}
		}

		if (arr[0] == arr[4] && arr[0] == arr[8] && arr[0] != 0) {
			count[0]++;
			count[4]++;
			count[8]++;
			bingo[arr[0] - 1]++;
		}

		if (arr[2] == arr[4] && arr[2] == arr[6] && arr[2] != 0) {
			count[2]++;
			count[4]++;
			count[6]++;
			bingo[arr[2] - 1]++;
		}
		
		if (bingo[0] == 0 && bingo[1] == 0) {
			if (cntX + cntO == 9) return true;
			else return false;
		}

		if (bingo[0] == 1 && bingo[1] == 1) return false;
		
		if (bingo[0] == 1) {
			if (cntX == cntO) return false;
			return true;
		}
		
		if (bingo[1] == 1) {
			if (cntX == cntO) return true;
			return false;
		}

		for (int i = 0; i < 9; i++) {
			if (count[i] == 2) return true;
		}

		return false;
	}

	private static int trit(String str) {

		int res = 0;
		int rank = 1;
		int num;

		for (int i = 0; i < 9; i++) {
			if (str.charAt(i) == 'X')
				num = 1;
			else if (str.charAt(i) == 'O')
				num = 2;
			else
				num = 0;

			res += rank * num;
			rank *= 3;
		}

		return res;
	}

	private static int trit(int[] arr) {

		int res = 0;
		int rank = 1;

		for (int i = 0; i < 9; i++) {
			res += rank * arr[i];
			rank *= 3;
		}

		return res;
	}
}