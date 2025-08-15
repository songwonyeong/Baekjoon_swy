import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Calendar cal = Calendar.getInstance();
        int count = 0;

        // 2019년 1월 ~ N년 12월
        for (int year = 2019; year <= N; year++) {
            for (int month = 0; month < 12; month++) {
                cal.set(year, month, 13); // 해당 년, 월, 13일 설정
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
