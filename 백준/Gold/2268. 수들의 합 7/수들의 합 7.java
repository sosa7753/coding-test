import java.util.*;
import java.io.*;
class Main {
    static int N, M;
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1];
        tree = new long[4*N];
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            if(t == 0) {
                int l = Math.min(s, e);
                int r = Math.max(s, e);
                sb.append(query(1, 1, N, l, r)).append("\n");
            }else {
                update(1, 1, N, s, (long)e);
            }
        }
                          
        System.out.print(sb);
    }
    
    public static long update(int now, int s, int e, int idx, long val) {
        if(idx < s || e < idx) {
            return tree[now];
        }
        
        if(s == e) {
            return tree[now] = val;
        }
        
        int m = (s+e)/2;
        long left = update(now*2, s, m, idx, val);
        long right = update(now*2+1, m+1, e, idx, val);
        return tree[now] = left + right;
    }
                          
    public static long query(int now, int s, int e, int l, int r) {
        if(r < s || e < l) {
            return 0;
        }
        
        if(l<=s && e<=r) {
            return tree[now];
        }
        
        int m = (s+e)/2;
        long left = query(now*2, s, m, l, r);
        long right = query(now*2+1, m+1, e, l, r);
        return left + right;
    }     
}