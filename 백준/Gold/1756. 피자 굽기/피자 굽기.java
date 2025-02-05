import java.util.*;
import java.io.*;
class Main {
    static int D;
    static int N;
    static int[] oven;
    static int[] pizza;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        oven = new int[D+1];
        pizza = new int[N];
               
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=D; i++) {
            int cur = Integer.parseInt(st.nextToken());
            min = Math.min(cur, min);
            oven[i] = min;                   
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<pizza.length; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }
        
        // 넣기
        int idx = oven.length-1;
        for(int i=0; i<pizza.length; i++) {
            for(int j=idx; j>=1; j--) {
                idx--;
                if(oven[j] >= pizza[i]) {
                    break;
                }
            }
        }
        if(idx == 0) {
            System.out.print(0);
        }else {
            System.out.print(idx+1);
        }   
    }
}