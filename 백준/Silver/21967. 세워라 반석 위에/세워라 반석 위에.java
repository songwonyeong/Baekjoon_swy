import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 카운팅 배열 (수의 범위가 1~10이므로 11칸)
        int[] count = new int[11];
        int left = 0, right = 0;
        int maxLen = 0;
        int minVal = A[0], maxVal = A[0];

        while (right < N) {
            count[A[right]]++;

            // 구간의 최솟값/최댓값 계산
            minVal = getMin(count);
            maxVal = getMax(count);

            // 최댓값 - 최솟값이 2를 초과하면 왼쪽 포인터 이동
            while (maxVal - minVal > 2) {
                count[A[left]]--;
                left++;
                minVal = getMin(count);
                maxVal = getMax(count);
            }
            // 현재 윈도우의 길이로 최대값 갱신
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        System.out.println(maxLen);
    }

    // 현재 윈도우 내의 최솟값 찾기
    static int getMin(int[] count) {
        for (int i = 1; i <= 10; i++) {
            if (count[i] > 0) return i;
        }
        return 0; // 입력 조건상 항상 1~10이 있으니 사실상 안 쓰임
    }
    // 현재 윈도우 내의 최댓값 찾기
    static int getMax(int[] count) {
        for (int i = 10; i >= 1; i--) {
            if (count[i] > 0) return i;
        }
        return 0;
    }
}
