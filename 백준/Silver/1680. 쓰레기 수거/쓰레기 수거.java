import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 트럭 용량
            int N = Integer.parseInt(st.nextToken()); // 지점 수

            long total = 0;
            int load = 0;
            int far = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); // 거리
                int w = Integer.parseInt(st.nextToken()); // 쓰레기 양

                // 현재 지점을 무조건 방문
                far = x;

                // 만약 이번 지점 쓰레기를 싣기 전에 용량 초과 → 돌아가고 다시 시작
                if (load + w > W) {
                    total += 2L * far;   // 여기까지 온 후 왕복
                    load = 0;            // 비우고 다시 시작
                }

                // 지점 쓰레기 싣기 (항상 가능, w <= W 보장)
                load += w;

                // 꽉 차면 바로 왕복
                if (load == W) {
                    total += 2L * far;
                    load = 0;
                }
            }

            // 마지막으로 남은 적재 처리
            if (load > 0) {
                total += 2L * far;
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb);
    }
}
