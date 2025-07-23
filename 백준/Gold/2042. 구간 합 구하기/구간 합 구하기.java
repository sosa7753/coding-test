import java.util.*;
import java.io.*;
class Main {
    static int N, M, K;
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1];
        tree = new long[N*4];
        
        for(int i=1; i<=N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        build(1, 1, N);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            
            if(t == 1) {
                change(1, 1, N, s, e);
            }else {
                sb.append(query(1, 1, N, s, (int)e)).append("\n");
            }
        }
        System.out.print(sb);
    }
    
    public static long build(int now, int s, int e) {
        if(s == e) {
            return tree[now] = arr[s];
        }
        
        int m = (s+e)/2;
        long left = build(now*2, s, m);
        long right = build(now*2+1, m+1, e);
        return tree[now] = left + right;
    }
    
    public static long change(int now, int s, int e, int idx, long num) {
        if(idx < s || e < idx) {
            return tree[now];
        }
        
        if(s == e) {
            return tree[now] = num;
        }
        
        int m = (s+e)/2;
        long left = change(now*2, s, m, idx, num);
        long right = change(now*2+1, m+1, e, idx, num);
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
        long left = query(now*2, s, m, l ,r);
        long right = query(now*2+1, m+1, e, l, r);
        return left + right;
    }
}