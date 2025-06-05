import java.util.*;
import java.io.*;
class Main {
    static int N;
    static int[] queen;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        queen = new int[N];
        DFS(0);
        System.out.print(answer);     
    }
    
    public static void DFS(int cnt) {
        if(cnt == N) {
            answer++;
            return;
        }
        
        for(int i=0; i<N; i++) {
            boolean check = true;
            for(int j=0; j<cnt; j++) {
                if(Math.abs(i - queen[j]) == cnt - j || queen[j] == i) {
                    check = false;
                    break;
                }               
            }
            if(check) {
               queen[cnt] = i;        
               DFS(cnt+1);  
            }     
        }
    }
}