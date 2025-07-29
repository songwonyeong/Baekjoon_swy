import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int N = sc.nextInt();

        // 1. N이 1이 아니고 홀수일 때: ERROR
        if (N != 1 && N % 2 == 1) {
            System.out.println("ERROR");
        }
        // 2. N이 1이면서 X<0, 또는 N이 0이면서 X>0 : INFINITE
        else if ((X < 0 && N == 1) || (X > 0 && N == 0)) {
            System.out.println("INFINITE");
        }
        // 3. X>0, N!=0, N짝수일 때 수식으로 카운트 출력
        else if (X > 0 && N != 0 && N % 2 == 0) {
            int nOp = N / 2;
            // 출력되는 개수는 반복문과 동일한 결과의 수식
            int answer = (X + nOp - 1) / nOp - 1;
            System.out.println(answer);
        }
        // 4. 나머지는 0
        else {
            System.out.println(0);
        }
    }
}
