import java.util.*;

public class Main {
    static int N, K, answer = 0;
    static int[] kits;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        kits = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            kits[i] = sc.nextInt();
        }
        backtrack(0, 500); // 첫날 중량 500부터 시작
        System.out.println(answer);
    }

    // day: 며칠째인지, weight: 현재 중량
    static void backtrack(int day, int weight) {
        if (day == N) { // N일을 모두 사용한 경우
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                int nextWeight = weight - K + kits[i]; // 하루 지나며 감소 후, 키트 사용
                if (nextWeight >= 500) {
                    visited[i] = true;
                    backtrack(day + 1, nextWeight);
                    visited[i] = false; // 백트래킹
                }
            }
        }
    }
}
