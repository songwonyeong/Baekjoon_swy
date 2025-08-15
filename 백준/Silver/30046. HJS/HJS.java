import java.io.*;
import java.util.*;

public class Main {

    // A와 B가 처음으로 다른 위치의 문자 쌍을 반환 (동일하면 null)
    static char[] firstDiff(String A, String B) {
        int n = A.length();
        for (int i = 0; i < n; i++) {
            char a = A.charAt(i), b = B.charAt(i);
            if (a != b) return new char[]{a, b}; // a < b 여야 A < B
        }
        return null; // 완전히 동일
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim()); // 사용은 안 하지만 형식상 읽기
        String P = br.readLine().trim();
        String Q = br.readLine().trim();
        String R = br.readLine().trim();

        char[] pq = firstDiff(P, Q);
        char[] qr = firstDiff(Q, R);

        // 둘 중 하나라도 완전히 같으면 p<q<r 불가능
        if (pq == null || qr == null) {
            System.out.println("Hmm...");
            return;
        }

        // H, J, S의 6가지 순서(작을수록 작은 숫자에 대응)
        char[][] orders = {
            {'H','J','S'}, {'H','S','J'},
            {'J','H','S'}, {'J','S','H'},
            {'S','H','J'}, {'S','J','H'}
        };

        boolean ok = false;
        for (char[] ord : orders) {
            int[] rank = new int[256]; // 문자 -> 순위(작을수록 작은 수)
            rank[ord[0]] = 0; rank[ord[1]] = 1; rank[ord[2]] = 2;

            if (rank[pq[0]] < rank[pq[1]] && rank[qr[0]] < rank[qr[1]]) {
                ok = true;
                break;
            }
        }

        System.out.println(ok ? "HJS! HJS! HJS!" : "Hmm...");
    }
}
