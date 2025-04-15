import java.util.*;
import java.io.*;
class Main {
    static int N, L;
    static int[] num;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        num = new int[N];
        answer = new int[N];
       
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        Deque<int[]> dq = new LinkedList<>();
        
        for(int i=0; i<N; i++) {
            while(!dq.isEmpty() && dq.getLast()[1] > num[i]) {
                dq.removeLast();
            }
            
            dq.addLast(new int[]{i, num[i]});
            
            if(dq.getFirst()[0] <= i-L) {
                dq.removeFirst();
            }
            
            sb.append(dq.getFirst()[1]).append(" ");
        }
        System.out.print(sb);      
    }
}