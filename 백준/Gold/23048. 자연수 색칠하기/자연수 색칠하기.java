import java.util.*;
import java.io.*;
class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        Set<Integer> set = new HashSet<>();
        int[] num = new int[N+1];
        set.add(1);
        num[1] = 1;
        
        int next = 2;
        for(int i=2; i<=N; i++) {
            if(num[i] !=0) continue;
            set.add(i);
            
            for(int j=i; j<=N; j=j+i) {
                if(num[j] == 0) {
                    num[j] = next;
                }    
            }
            next++;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(set.size()).append("\n");
        for(int i=1; i<=N; i++) {
            sb.append(num[i]);
            if(i != N) {
                sb.append(" ");
            }
        }
        System.out.print(sb);
    }
}