import java.io.*;
import java.util.*;

public class Main {
    static int toSec(String mmss) {
        String[] sp = mmss.split(":");
        return Integer.parseInt(sp[0]) * 60 + Integer.parseInt(sp[1]);
    }

    static String toMMSS(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int score1 = 0, score2 = 0;     // 현재 점수
        int lead = 0;                   // 현재 리드 팀(0: 없음, 1: 팀1, 2: 팀2)
        int lastTime = 0;               // 마지막 이벤트 시각(초)
        int leadTime1 = 0, leadTime2 = 0; // 팀별 리드 누적(초)

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken());
            int t = toSec(st.nextToken());

            // 이번 득점 전까지는 이전 리드 팀이 이기고 있던 시간
            if (lead == 1) leadTime1 += t - lastTime;
            else if (lead == 2) leadTime2 += t - lastTime;

            // 득점 반영
            if (team == 1) score1++;
            else score2++;

            // 리드 팀 갱신
            if (score1 > score2) lead = 1;
            else if (score2 > score1) lead = 2;
            else lead = 0; // 동점이면 리드 없음

            lastTime = t;
        }

        // 경기 끝(48:00)까지 처리
        int end = 48 * 60;
        if (lead == 1) leadTime1 += end - lastTime;
        else if (lead == 2) leadTime2 += end - lastTime;

        System.out.println(toMMSS(leadTime1));
        System.out.println(toMMSS(leadTime2));
    }
}
