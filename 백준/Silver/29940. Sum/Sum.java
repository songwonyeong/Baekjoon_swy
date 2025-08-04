import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 0, right = N - 1, count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == S) {
                count++;
                left++;
                right--;
            } else if (sum < S) {
                left++;
            } else { // sum > S
                right--;
            }
        }

        System.out.println(count);
    }
}
