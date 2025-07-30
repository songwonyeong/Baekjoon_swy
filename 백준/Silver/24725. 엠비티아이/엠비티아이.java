import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    // MBTI 16가지 유형 Set
    static Set<String> mbtiSet = new HashSet<>(Arrays.asList(
            "ISTJ", "ISFJ", "INFJ", "INTJ",
            "ISTP", "ISFP", "INFP", "INTP",
            "ESTP", "ESFP", "ENFP", "ENTP",
            "ESTJ", "ESFJ", "ENFJ", "ENTJ"
    ));

    // 8방향: 오른쪽, 왼쪽, 아래, 위, 대각선 4방향
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int count = 0;
        // 모든 칸에서 8방향으로 탐색
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (int dir = 0; dir < 8; dir++) {
                    int nx = x;
                    int ny = y;
                    StringBuilder sb = new StringBuilder();
                    boolean valid = true;
                    for (int step = 0; step < 4; step++) {
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            valid = false;
                            break;
                        }
                        sb.append(board[nx][ny]);
                        nx += dx[dir];
                        ny += dy[dir];
                    }
                    if (valid && mbtiSet.contains(sb.toString())) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
