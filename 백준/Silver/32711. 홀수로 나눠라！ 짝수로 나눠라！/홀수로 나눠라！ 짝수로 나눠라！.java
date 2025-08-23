import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int oddCnt = 0;
        int parity = 0;                 // 접두 합의 짝/홀 (0=짝, 1=홀)
        boolean hasEvenPrefixBeforeEnd = false; // i < N 에서 접두 합 짝수 존재?

        for (int i = 1; i <= N; i++) {
            long a = Long.parseLong(st.nextToken());
            if ((a & 1L) == 1L) { // 홀수
                oddCnt++;
                parity ^= 1;
            }
            if (i < N && parity == 0) { // i < N 이면서 접두 합 짝수
                hasEvenPrefixBeforeEnd = true;
            }
        }

        int ans;
        if ((oddCnt & 1) == 1) {               // 조건 1: 홀수 개수가 홀수
            ans = 1;
        } else if (hasEvenPrefixBeforeEnd) {    // 조건 2: 총합 짝수이면서 중간에 짝수 접두사 존재
            ans = 1;
        } else {
            ans = 0;
        }

        System.out.println(ans);
    }
}
