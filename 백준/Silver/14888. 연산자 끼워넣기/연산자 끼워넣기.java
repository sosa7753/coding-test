import java.util.*;
import java.io.*;
class Main {
    static int[] num;
    static int[] oper;
    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());          
        }
        
        oper = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈 
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
        
        DFS(num[0], 1);
        System.out.println(max);
        System.out.println(min);        
    }
    
    public static void DFS(int result, int cnt) {
        if(cnt == N) {
           max = Math.max(result, max);
           min = Math.min(result, min);
            return;
        }
        
        for(int i=0; i<4; i++) {
            if(oper[i] == 0) {
                continue;
            }
            
            oper[i]--;
            DFS(cal(result, num[cnt], i), cnt+1);
            oper[i]++;
        }       
    }
    
    public static int cal(int a, int b, int operator) {
        int result = a;
        
        if(operator == 0) {
            result +=b;
        }else if(operator==1) {
            result -=b;
        }else if(operator==2) {
            result *=b;
        }else if(operator==3) {
            result /=b;
        }
        
        return result;
    }
}