import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        long[] v = new long[n];
        // 입력이 한 줄이든 여러 줄이든 모두 토큰으로 채집
        StringTokenizer st = new StringTokenizer("");
        int idx = 0;
        while (idx < n) {
            if (!st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
                continue;
            }
            v[idx++] = Long.parseLong(st.nextToken());
        }

        long s = v[n - 1];                   // 마지막 구간부터 역산
        for (int i = n - 2; i >= 0; i--) {
            long need = v[i];
            long q = s / need;
            if (s % need != 0) q++;          // 올림(ceil)
            s = q * need;                    // need의 정수배로 맞춤
        }

        System.out.println(s);
    }
}
