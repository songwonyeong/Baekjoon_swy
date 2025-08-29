import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        // 시작은 1로 고정: 상대가 무엇을 하든 곧 "3의 배수 컨트롤"을 가져올 수 있음
        System.out.println(1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) return; // 채점기 종료/에러 대비
            line = line.trim();
            if (line.isEmpty()) continue;

            int x;
            try {
                x = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                return; // 비정상 입력 시 종료
            }

            // 채점기(상대)가 99를 말했으면 정상 종료
            if (x == 99) return;

            int myMove;
            if (x == 98) {
                // 바로 99 말하고 승리
                myMove = 99;
            } else if (x % 3 == 0) {
                // 상대가 3의 배수를 말한 경우엔 +1만 해서 흐름 회수
                myMove = x + 1;
            } else {
                // 그 외에는 다음 3의 배수로 맞춤 (항상 +1 또는 +2가 됨)
                myMove = x + (3 - (x % 3));
            }

            // 안전장치: 범위 초과 방지
            if (myMove > 99) myMove = 99;

            System.out.println(myMove);

            // 내가 99를 말했으면 정상 종료
            if (myMove == 99) return;
        }
    }
}
