import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] A = new long[N];
        long[] B = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) B[i] = Long.parseLong(st.nextToken());

        long sumA = 0, sumB = 0;
        for (int i = 0; i < N; i++) { sumA += A[i]; sumB += B[i]; }

        // 뒤에서부터 i개를 제거했을 때의 합 = 접미합
        long[] sufA = new long[N + 1]; // sufA[i] = A의 뒤에서 i개의 합
        long[] sufB = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            sufA[i] = sufA[i - 1] + A[N - i];
            sufB[i] = sufB[i - 1] + B[N - i];
        }

        long answer = Long.MAX_VALUE;
        int maxI = Math.min(N, K);
        for (int i = 0; i <= maxI; i++) {
            long remainA = sumA - sufA[i];
            int jMax = Math.min(N, K - i);
            long bestRemainB = sumB - sufB[jMax];  // B를 최대한 제거했을 때 남는 무게

            long candidate = Math.max(remainA, bestRemainB);
            // 만약 bestRemainB <= remainA 면 결과는 remainA와 같음(더 줄일 수 없음)
            // bestRemainB > remainA 면 가능한 최솟값은 bestRemainB (j를 더 늘릴 수 없으니)

            answer = Math.min(answer, candidate);
        }

        System.out.println(answer);
    }
}
