import java.util.*;
import java.io.*;
class Main {
    static int N, M;
    static int[] tree;
    static int size = 1;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
        
       while(size < N) {
           size <<= 1;
       }
        
       tree = new int[size * 2];         
       for(int i=0; i<N; i++) {
           tree[size+i] = Integer.parseInt(br.readLine());
       }
        
       for(int i=size-1; i>=1; i--) {
           tree[i] = Math.min(tree[i*2], tree[i*2 + 1]);
       }
        
       StringBuilder sb = new StringBuilder();
       for(int i=0; i<M; i++) {
           st = new StringTokenizer(br.readLine());
           int s = Integer.parseInt(st.nextToken());
           int e = Integer.parseInt(st.nextToken());
           
           sb.append(query(s, e)).append("\n");
       }
       
       System.out.print(sb);
    }
    
    public static int query(int s, int e) {
        int l = s + size - 1;
        int r = e + size - 1;
        
        int result = Integer.MAX_VALUE;
        while(l <= r) {
            if(l%2 == 1) {
                result = Math.min(result, tree[l]);
                l++;
            }
            
            if(r%2 == 0) {
                result = Math.min(result, tree[r]);
                r--;
            }
            
            l >>=1;
            r >>=1;
        }
        return result;
    }
}