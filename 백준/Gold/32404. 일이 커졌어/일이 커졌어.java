import java.util.*;
import java.io.*;
class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
      
        // N이 짝수가 몇개인가?
        int even = N/2; // 1 ~ even까지는 짝수 배치 
        
        StringBuilder sb = new StringBuilder();
        
        int idx = 1;
        int e = even;
        int o = even + 1;
        while(idx <= N) {        
            if(idx%2 == 1) {
                sb.append(o).append(" ");
                o++;
            }else if(idx%2==0) {
                sb.append(e).append(" ");
                e--;
            }
            idx++;
        }
        System.out.print(sb.toString().trim());
    }
}