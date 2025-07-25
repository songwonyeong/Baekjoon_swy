import java.util.*;
import java.io.*;

public class Main {
    static int N, M, P;
    static Map<String, Integer> nameIdx = new HashMap<>();
    static String[] names;
    static List<Record> records = new ArrayList<>();

    static class Record {
        int prob;
        int time; // minutes since 10:00
        String name;
        String result;

        Record(int prob, int time, String name, String result) {
            this.prob = prob;
            this.time = time;
            this.name = name;
            this.result = result;
        }
    }

    static class Participant {
        int[] wrongTime;
        int[] solveTime;
        int score = 0;

        Participant() {
            wrongTime = new int[N];
            solveTime = new int[N];
            Arrays.fill(wrongTime, -1);
            Arrays.fill(solveTime, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 문제 수
        M = sc.nextInt(); // 참가자 수
        P = sc.nextInt(); // 기록 수

        names = new String[M];
        for (int i = 0; i < M; i++) {
            names[i] = sc.next();
            nameIdx.put(names[i], i);
        }

        Participant[] participants = new Participant[M];
        for (int i = 0; i < M; i++) participants[i] = new Participant();

        // 기록 파싱
        for (int i = 0; i < P; i++) {
            int prob = sc.nextInt() - 1;
            String timeStr = sc.next();
            String name = sc.next();
            String result = sc.next();

            int minute = toMinute(timeStr);
            Record r = new Record(prob, minute, name, result);
            records.add(r);
        }

        // 시간순 기록 처리
        for (Record r : records) {
            int idx = nameIdx.get(r.name);
            Participant p = participants[idx];

            if (p.solveTime[r.prob] != -1) continue; // solve 이후 기록 무시

            if (r.result.equals("wrong")) {
                if (p.wrongTime[r.prob] == -1) {
                    p.wrongTime[r.prob] = r.time;
                }
            } else if (r.result.equals("solve")) {
                p.solveTime[r.prob] = r.time;
            }
        }

        // 문제별 점수 계산
        for (int prob = 0; prob < N; prob++) {
            List<int[]> solvedList = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                int wt = participants[i].wrongTime[prob];
                int st = participants[i].solveTime[prob];

                if (wt == -1 && st == -1) {
                    participants[i].score += (M + 1);
                } else if (wt == -1 && st != -1) {
                    participants[i].score += (M + 1); // 무효
                } else if (wt != -1 && st == -1) {
                    participants[i].score += M;
                } else {
                    int diff = st - wt;
                    solvedList.add(new int[]{diff, i});
                }
            }

            // solve한 사람 정렬 (시간 → 이름)
            solvedList.sort((a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0];
                return names[a[1]].compareTo(names[b[1]]);
            });

            for (int i = 0; i < solvedList.size(); i++) {
                int idx = solvedList.get(i)[1];
                participants[idx].score += (i + 1);
            }
        }

        // 결과 정렬 및 출력
        List<String[]> resultList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            resultList.add(new String[]{names[i], String.valueOf(participants[i].score)});
        }

        resultList.sort((a, b) -> {
            int s1 = Integer.parseInt(a[1]);
            int s2 = Integer.parseInt(b[1]);
            if (s1 != s2) return s1 - s2;
            return a[0].compareTo(b[0]);
        });

        for (String[] res : resultList) {
            System.out.println(res[0]);
        }
    }

    // "HH:MM" → 분 단위
    static int toMinute(String time) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]);
        int m = Integer.parseInt(t[1]);
        return (h - 10) * 60 + m; // 대회는 10:00 시작 기준
    }
}
