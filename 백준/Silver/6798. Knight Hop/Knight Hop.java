import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static class Node {
        int x, y, moves;
        Node(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int sx = sc.nextInt() - 1;
        int sy = sc.nextInt() - 1;
        int ex = sc.nextInt() - 1;
        int ey = sc.nextInt() - 1;

        System.out.println(bfs(sx, sy, ex, ey));
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        boolean[][] visited = new boolean[8][8];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == ex && cur.y == ey) return cur.moves;

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, cur.moves + 1));
                }
            }
        }
        return 0; // (문제상 모든 경로는 존재함)
    }
}
