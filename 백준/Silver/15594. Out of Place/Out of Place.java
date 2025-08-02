import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int[] sorted = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            sorted[i] = arr[i];
        }

        Arrays.sort(sorted);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] != sorted[i]) {
                cnt++;
            }
        }
        // 한 번은 Bessie가 옮겨간 자리라 -1 (최소 0보장)
        System.out.println(Math.max(0, cnt - 1));
    }
}
