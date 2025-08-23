import java.io.*;
import java.util.*;

public class Main {
    static int R, S;
    static char[][] a;

    // 8방향 (상,하,좌,우 + 대각)
    static int[] dr8 = {-1,-1,-1, 0, 0, 1, 1, 1};
    static int[] dc8 = {-1, 0, 1,-1, 1,-1, 0, 1};

    // 중복 없이 세기 위한 4방향(오른쪽, 아래, 오른아래, 왼아래)
    static int[] dr4 = {0, 1, 1, 1};
    static int[] dc4 = {1, 0, 1,-1};

    static boolean in(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < S;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        a = new char[R][S];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < S; j++) a[i][j] = line.charAt(j);
        }

        // 1) 현재 악수 쌍 수(중복 없이)
        int base = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < S; c++) {
                if (a[r][c] != 'o') continue;
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr4[k], nc = c + dc4[k];
                    if (in(nr, nc) && a[nr][nc] == 'o') base++;
                }
            }
        }

        // 2) 상근이가 앉을 자리에서 늘어나는 악수 수의 최댓값
        int bestAdd = -1; // 빈자리 없는 경우를 구분하기 위해 -1로 시작
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < S; c++) {
                if (a[r][c] != '.') continue;
                int cnt = 0;
                for (int k = 0; k < 8; k++) {
                    int nr = r + dr8[k], nc = c + dc8[k];
                    if (in(nr, nc) && a[nr][nc] == 'o') cnt++;
                }
                bestAdd = Math.max(bestAdd, cnt);
            }
        }

        int answer = base + Math.max(0, bestAdd); // 빈자리가 없으면 0 더하기
        System.out.println(answer);
    }
}
