import java.util.*;

public class Main {
    // 문자열 뒤집기 함수
    static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String S = sc.next();

        List<String> list = new ArrayList<>();
        // 모든 이진수 만들기
        for (int i = 0; i < (1 << N); i++) {
            String bin = String.format("%" + N + "s", Integer.toBinaryString(i)).replace(' ', '0');
            list.add(bin);
        }

        // 정렬
        list.sort((a, b) -> {
            int cntA = a.replace("0", "").length();
            int cntB = b.replace("0", "").length();
            if (cntA != cntB)
                return Integer.compare(cntA, cntB);

            // 뒤집어서 비교
            String revA = reverse(a);
            String revB = reverse(b);
            return revA.compareTo(revB);
        });

        // S의 인덱스 찾기
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(S)) {
                System.out.println(i);
                return;
            }
        }
    }
}
