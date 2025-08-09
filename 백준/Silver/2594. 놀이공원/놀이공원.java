import java.io.*;
import java.util.*;

/**
 * BOJ 2594 - 놀이공원
 * 아이디어:
 * 1) 입력 구간을 [start-10, end+10]으로 확장하고 하루 범위 [600, 1320]으로 클리핑
 * 2) 시작 시간 기준 정렬 후 겹치는 구간 병합
 * 3) 병합된 바쁜 구간 사이의 공백(쉬는 시간) 최댓값 계산
 */
public class Main {

    static class Interval implements Comparable<Interval> {
        int s, e; // minutes since 00:00
        Interval(int s, int e) { this.s = s; this.e = e; }
        @Override public int compareTo(Interval o) { return Integer.compare(this.s, o.s); }
    }

    static int toMinutes(String hhmm) {
        // "1030" -> 10*60 + 30
        int t = Integer.parseInt(hhmm);
        int h = t / 100;
        int m = t % 100;
        return h * 60 + m;
    }

    public static void main(String[] args) throws Exception {
        final int DAY_START = 10 * 60;  // 600 (10:00)
        final int DAY_END   = 22 * 60;  // 1320 (22:00)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = toMinutes(st.nextToken()) - 10; // 시작 10분 전부터 못 쉼
            int e = toMinutes(st.nextToken()) + 10; // 종료 10분 후까지 못 쉼
            // 하루 범위로 클리핑
            s = Math.max(s, DAY_START);
            e = Math.min(e, DAY_END);
            if (s < e) list.add(new Interval(s, e));
        }

        // 바쁜 시간대가 하나도 없으면 하루 전체가 휴식 가능
        if (list.isEmpty()) {
            System.out.println(DAY_END - DAY_START);
            return;
        }

        Collections.sort(list);

        // 겹치는 구간 병합
        List<Interval> merged = new ArrayList<>();
        int curS = list.get(0).s, curE = list.get(0).e;
        for (int i = 1; i < list.size(); i++) {
            Interval in = list.get(i);
            if (in.s <= curE) {                 // 겹치거나 이어지면 확장
                curE = Math.max(curE, in.e);
            } else {                             // 끊기면 확정
                merged.add(new Interval(curS, curE));
                curS = in.s; curE = in.e;
            }
        }
        merged.add(new Interval(curS, curE));

        // 빈 구간(쉬는 시간) 최댓값 계산
        int maxRest = 0;
        int prev = DAY_START;
        for (Interval in : merged) {
            maxRest = Math.max(maxRest, in.s - prev); // 이전 끝 ~ 현재 시작 사이
            prev = Math.max(prev, in.e);
        }
        maxRest = Math.max(maxRest, DAY_END - prev);  // 마지막 이후

        System.out.println(maxRest);
    }
}
