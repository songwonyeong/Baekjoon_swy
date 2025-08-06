import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String S = br.readLine();
            List<Integer> vowelIdx = new ArrayList<>();
            for (int i = 0; i < S.length(); i++) {
                if (isVowel(S.charAt(i))) vowelIdx.add(i);
            }
            int k = vowelIdx.size();
            if (k == 0) {
                sb.append(-1).append('\n');
                continue;
            }
            long ans = 1;
            for (int i = 1; i < k; i++) {
                // 인접한 모음 사이 구간 길이 (양쪽 모음 포함)
                int gap = vowelIdx.get(i) - vowelIdx.get(i - 1);
                ans = (ans * gap) % MOD;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
