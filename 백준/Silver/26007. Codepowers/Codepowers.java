import java.io.*;          // 입출력 관련 클래스 (BufferedReader 등)
import java.util.*;        // 유틸리티 클래스 (StringTokenizer, StringBuilder 등)

public class Main {
    public static void main(String[] args) throws IOException {
        // 📌 [1] 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // ✅ 입력 형식: N(라운드 수), M(쿼리 수), K(기준 레이팅), X(초기 레이팅)
        // ex: 10 6 1019 1000
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 라운드 수
        int M = Integer.parseInt(st.nextToken());       // 쿼리 수
        long K = Long.parseLong(st.nextToken());        // 기준 레이팅 (최대 10^9 → long 사용)
        long X = Long.parseLong(st.nextToken());        // 초기 레이팅 (변화량 누적 시 long 필요)

        // 📌 [2] 배열 초기화
        long[] A = new long[N];      // 각 라운드의 레이팅 변화량 저장 배열
        int[] isLower = new int[N];  // 각 라운드가 기준 K보다 낮은 경우 → 1, 아니면 0
        int[] prefixSum = new int[N + 1]; // 구간 합(누적 낮은 점수 횟수)을 위한 누적합 배열 (1-based index)

        // ✅ 두 번째 줄: N개의 레이팅 변화량 A 입력받기
        // ex: 7 -5 5 8 1 3 6 -7 7 10
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());  // long 타입으로 받기 (누적 시 범위 초과 방지)
        }

        // 📌 [3] 각 라운드의 레이팅 계산 및 isLower 배열 설정
        long currentRating = X;  // 현재 레이팅은 X에서 시작
        for (int i = 0; i < N; i++) {
            currentRating += A[i];                 // 변화량 누적
            if (currentRating < K) {
                isLower[i] = 1;                     // 기준 레이팅보다 낮으면 1
            } else {
                isLower[i] = 0;                     // 기준 이상이면 0
            }
        }

        // 📌 [4] 누적합(prefixSum) 계산
        // prefixSum[i]는 1번부터 i번까지 낮은 점수 횟수의 누적합
        prefixSum[0] = 0; // 0번째는 기본 0
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + isLower[i - 1];
            // 주의: prefixSum은 1-based, isLower는 0-based
        }

        // 📌 [5] 쿼리 처리 및 출력 저장
        StringBuilder sb = new StringBuilder(); // 많은 출력에 대해 성능 향상

        // ✅ 이후 M줄에는 쿼리 (l, r)가 주어짐: [l, r) 구간에서 낮은 점수 횟수 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());  // 시작 라운드 (1-based)
            int r = Integer.parseInt(st.nextToken());  // 끝 라운드 (exclusive)

            // ✅ 정답 계산: prefixSum[r - 1] - prefixSum[l - 1]
            // [l, r-1] 구간 내 낮은 점수 개수
            int result = prefixSum[r - 1] - prefixSum[l - 1];
            sb.append(result).append('\n');
        }

        // 📌 [6] 결과 한번에 출력
        System.out.print(sb);
    }
}
