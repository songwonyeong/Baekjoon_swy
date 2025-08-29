import java.io.*;
import java.util.*;

/**
 * BOJ 2236 칩 만들기
 * Interval DP + Reconstruction
 */
public class Main {
    static final long NEG = Long.MIN_VALUE / 4;

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        long nextLong() throws IOException {
            int c; do { c = read(); } while (c <= ' ');
            int sign = 1; if (c == '-') { sign = -1; c = read(); }
            long v = 0;
            while (c > ' ') { v = v * 10 + (c - '0'); c = read(); }
            return v * sign;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }

    static int N, K;
    static long[] a; // 1-indexed
    static long[][][] dp; // [1..N][1..N][0..K]
    static int[][][] choiceType; // 0: skip r, 1: single r, 2: pair(m,t)
    static int[][][] choiceM;     // when type=2
    static int[][][] choiceT;     // when type=2
    static int[] partner;         // 1..N (0: unused, i: alone, j: pair)
    static List<Integer> starts;  // left endpoints (or the only endpoint for alone)

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        N = fs.nextInt();
        K = fs.nextInt();
        a = new long[N + 1];
        for (int i = 1; i <= N; i++) a[i] = fs.nextLong();

        dp = new long[N + 2][N + 2][K + 1];
        choiceType = new int[N + 2][N + 2][K + 1];
        choiceM = new int[N + 2][N + 2][K + 1];
        choiceT = new int[N + 2][N + 2][K + 1];

        // initialize
        for (int l = 1; l <= N; l++)
            for (int r = 1; r <= N; r++)
                Arrays.fill(dp[l][r], NEG);

        for (int i = 1; i <= N; i++) {
            dp[i][i - 1] = new long[K + 1];   // empty interval base: 0
        }

        // fill by increasing length
        for (int len = 1; len <= N; len++) {
            for (int l = 1; l + len - 1 <= N; l++) {
                int r = l + len - 1;
                for (int k = 0; k <= K; k++) {
                    long best = dp[l][r][k];

                    // 1) skip r
                    if (dp[l][r - 1][k] > best) {
                        best = dp[l][r - 1][k];
                        dp[l][r][k] = best;
                        choiceType[l][r][k] = 0;
                    }

                    // 2) use r alone (needs one wire)
                    if (k >= 1 && dp[l][r - 1][k - 1] != NEG) {
                        long cand = dp[l][r - 1][k - 1] + a[r] * a[r];
                        if (cand > best) {
                            best = cand;
                            dp[l][r][k] = best;
                            choiceType[l][r][k] = 1;
                        }
                    }

                    // 3) pair m with r
                    if (k >= 1) {
                        for (int m = l; m <= r - 1; m++) {
                            for (int t = 0; t <= k - 1; t++) {
                                long left = (m - 1 >= l) ? dp[l][m - 1][k - 1 - t] : 0;
                                long inside = (m + 1 <= r - 1) ? dp[m + 1][r - 1][t] : 0;
                                if ((m - 1 < l && k - 1 - t != 0) || (m + 1 > r - 1 && t != 0))
                                    continue; // impossible counts
                                if (left == NEG || inside == NEG) continue;
                                long cand = left + inside + a[m] * a[r];
                                if (cand > best) {
                                    best = cand;
                                    dp[l][r][k] = best;
                                    choiceType[l][r][k] = 2;
                                    choiceM[l][r][k] = m;
                                    choiceT[l][r][k] = t;
                                }
                            }
                        }
                    }
                }
            }
        }

        // 역추적(최대로 쓰인 전선 수 kUsed <= K)
        int kUsed = 0;
        long bestTot = NEG;
        for (int k = 0; k <= K; k++) {
            if (dp[1][N][k] > bestTot) { bestTot = dp[1][N][k]; kUsed = k; }
        }

        partner = new int[N + 1];
        starts = new ArrayList<>();
        reconstruct(1, N, kUsed);

        // 첫 K줄: 사용한 전선의 시작 지점(왼쪽 끝; 혼자 쓰는 경우엔 그 부품),
        // 인덱스 오름차순으로 출력, 남으면 0으로 채움.
        Collections.sort(starts);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < K; i++) {
            if (i < starts.size()) out.append(starts.get(i)).append('\n');
            else out.append('0').append('\n');
        }

        // 다음 N줄: partner[i] (자기 자신 / 짝 / 0)
        for (int i = 1; i <= N; i++) out.append(partner[i]).append('\n');

        System.out.print(out);
    }

    // dp[l][r][k]의 선택을 따라 partner[]와 starts를 채운다.
    static void reconstruct(int l, int r, int k) {
        if (l > r || k == 0) return;
        int typ = choiceType[l][r][k];
        if (typ == 0) { // skip r
            reconstruct(l, r - 1, k);
        } else if (typ == 1) { // r alone
            partner[r] = r;
            starts.add(r);
            reconstruct(l, r - 1, k - 1);
        } else { // pair (m, r)
            int m = choiceM[l][r][k];
            int t = choiceT[l][r][k];
            partner[m] = r;
            partner[r] = m;
            starts.add(m); // 왼쪽 끝을 시작 지점으로 잡음
            // outside left part uses (k-1 - t) wires
            int leftWires = k - 1 - t;
            if (m - 1 >= l && leftWires > 0) reconstruct(l, m - 1, leftWires);
            else if (m - 1 >= l) reconstruct(l, m - 1, 0);
            // inside part uses t wires
            if (m + 1 <= r - 1 && t > 0) reconstruct(m + 1, r - 1, t);
            else if (m + 1 <= r - 1) reconstruct(m + 1, r - 1, 0);
        }
    }
}
