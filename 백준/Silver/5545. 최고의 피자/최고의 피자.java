import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); // 도우 가격
        int B = Integer.parseInt(st.nextToken()); // 토핑 1개 가격

        int C = Integer.parseInt(br.readLine().trim()); // 도우 열량

        int[] toppings = new int[N];
        for (int i = 0; i < N; i++) toppings[i] = Integer.parseInt(br.readLine().trim());

        // 열량이 큰 순으로 정렬
        Arrays.sort(toppings);
        for (int i = 0; i < N / 2; i++) {
            int tmp = toppings[i];
            toppings[i] = toppings[N - 1 - i];
            toppings[N - 1 - i] = tmp;
        }

        int totalCal = C;      // 현재 총 열량
        int totalPrice = A;    // 현재 총 가격
        int best = totalCal / totalPrice; // 도우만 사용했을 때

        for (int i = 0; i < N; i++) {
            totalCal += toppings[i];
            totalPrice += B;
            best = Math.max(best, totalCal / totalPrice); // 정수 나눗셈(소수점 버림)
        }

        System.out.println(best);
    }
}
