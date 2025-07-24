import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int N = 100;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] RSP = new char[N];
        for (int i = 0; i < N; i++) RSP[i] = '2'; // 모두 가위로 초기화

        int K = Integer.parseInt(br.readLine().trim()); // 첫 이긴 판수
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            RSP[i] = '0'; // i번째만 바위
            System.out.println("? " + new String(RSP));
            System.out.flush();
            int result = Integer.parseInt(br.readLine().trim());
            
            if (result == K + 1) {
                answer.append('2'); // 머신은 가위
                K++;
            } else if (result == K) {
                RSP[i] = '5'; // 다음 비교를 위해 보로 바꿔줌
                answer.append('0'); // 머신은 바위
                K++;
            } else if (result == K - 1) {
                RSP[i] = '2'; // 다시 가위로 복구
                answer.append('5'); // 머신은 보
            }
        }
        System.out.println("! " + answer);
        System.out.flush();
    }
}
