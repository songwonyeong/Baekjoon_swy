import java.io.*;
import java.util.*;

public class Main {
    // 빠른 입력
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
            int c; do { c = read(); } while (c <= ' '); // skip spaces
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sign;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int Q = fs.nextInt();

        long[] a = new long[N];
        for (int i = 0; i < N; i++) a[i] = fs.nextLong();

        int head = 0; // 논리상 a1이 가리키는 물리 인덱스
        for (int q = 0; q < Q; q++) {
            int type = fs.nextInt();
            if (type == 1) {
                int i = fs.nextInt();        // 1-indexed
                long x = fs.nextLong();
                int idx = head + i - 1;
                if (idx >= N) idx -= N;      // % N (빠른 분기)
                a[idx] += x;
            } else if (type == 2) {
                int s = fs.nextInt() % N;
                head -= s;
                if (head < 0) head += N;     // (head - s) mod N
            } else { // type == 3
                int s = fs.nextInt() % N;
                head += s;
                if (head >= N) head -= N;    // (head + s) mod N
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, idx = head; i < N; i++) {
            if (idx == N) idx = 0;
            sb.append(a[idx++]);
            if (i + 1 < N) sb.append(' ');
        }
        System.out.println(sb.toString());
    }
}
