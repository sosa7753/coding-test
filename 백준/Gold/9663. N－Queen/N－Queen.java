import java.util.*;
public class Main {
    static int N;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dfs(0, 0, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int row, int col, int ld, int rd) { // 현재 추가 행, 차지된 열, 좌하, 우하
        if (row == N) {
            answer++;
            return;
        }

        int available = ((1 << N) - 1) & ~(col | ld | rd);
    
        while (available != 0) {
            int pos = available & -available; // 가장 오른쪽 1비트 추출
            available -= pos; // 열에 추가 

            dfs(row + 1, col | pos, (ld | pos) << 1, (rd | pos) >> 1); // 초기화
        }
    }
}