import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] C = new long[N];
        for (int i = 0; i < N; i++) {
            long A = sc.nextLong();
            long B = sc.nextLong();
            C[i] = B - A;
        }

        Arrays.sort(C);

        if (N % 2 == 1) {
            // 홀수: 중앙값 하나
            System.out.println(1);
        } else {
            long L = C[N/2 - 1];
            long R = C[N/2];
            System.out.println(R - L + 1);
        }
    }
}
