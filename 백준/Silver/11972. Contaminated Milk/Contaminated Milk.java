import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 우유 종류 수
        int D = Integer.parseInt(st.nextToken()); // 기록 수(누가 무엇을 언제 마셨는지)
        int S = Integer.parseInt(st.nextToken()); // 아픈 기록 수

        final int INF = 1_000_000_000;

        // drank[p][m] : p가 m번 우유를 한 번이라도 마셨는가
        boolean[][] drank = new boolean[N + 1][M + 1];
        // firstTime[p][m] : p가 m번 우유를 처음(가장 이른)으로 마신 시각
        int[][] firstTime = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(firstTime[i], INF);

        // 마신 기록 입력
        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            drank[p][m] = true;
            if (t < firstTime[p][m]) firstTime[p][m] = t;
        }

        // sickTime[p] : p가 아픈 시각(없으면 INF)
        int[] sickTime = new int[N + 1];
        Arrays.fill(sickTime, INF);
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            sickTime[p] = t;
        }

        int answer = 0;

        // 각 우유가 오염일 수 있는지 확인
        for (int m = 1; m <= M; m++) {
            boolean possible = true;

            // 모든 아픈 사람은 그 우유를 '아프기 전'에 마셨아야 함
            for (int p = 1; p <= N; p++) {
                if (sickTime[p] != INF) { // 아픈 사람만 체크
                    if (!(drank[p][m] && firstTime[p][m] < sickTime[p])) {
                        possible = false;
                        break;
                    }
                }
            }

            if (!possible) continue;

            // 후보 우유면, 그 우유를 한 번이라도 마신 사람 수(파티 후에 아플 수도 있으므로 전원 카운트)
            int cnt = 0;
            for (int p = 1; p <= N; p++) {
                if (drank[p][m]) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
