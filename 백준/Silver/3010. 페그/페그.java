import java.io.*;
import java.util.*;

public class Main {
    static final int N = 7;
    static char[][] board = new char[N][N];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 초기화: 보드 밖은 공백(' ')으로 채워 둠
        for (int i = 0; i < N; i++) Arrays.fill(board[i], ' ');

        // 입력 파싱
        for (int r = 0; r < N; r++) {
            String s = br.readLine();

            // 앞 두 칸이 공백인 줄(윗줄 2개/아랫줄 2개): 실제 보드 칸은 가운데 3칸
            if (s.length() >= 2 && s.charAt(0) == ' ' && s.charAt(1) == ' ') {
                int c = 2; // 가운데 3칸: 열 2,3,4
                for (int i = 0; i < s.length(); i++) {
                    char ch = s.charAt(i);
                    if (ch == 'o' || ch == '.') {
                        if (c < 5) board[r][c++] = ch;
                    }
                }
            } else { // 가운데 3줄: 7칸 전부가 보드
                int c = 0;
                for (int i = 0; i < s.length() && c < 7; i++) {
                    char ch = s.charAt(i);
                    if (ch == 'o' || ch == '.') board[r][c++] = ch;
                }
            }
        }

        // 가능한 이동 수 세기
        int ans = 0;
        int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dc = {0, 0, -1, 1};

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] != 'o') continue;
                for (int d = 0; d < 4; d++) {
                    int r1 = r + dr[d], c1 = c + dc[d];         // 인접 칸
                    int r2 = r + 2 * dr[d], c2 = c + 2 * dc[d]; // 그 다음 칸
                    if (in(r1, c1) && in(r2, c2)
                            && board[r1][c1] == 'o'
                            && board[r2][c2] == '.') {
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static boolean in(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N && board[r][c] != ' ';
    }
}
