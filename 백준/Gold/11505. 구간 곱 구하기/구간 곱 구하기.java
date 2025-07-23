import java.util.*;
import java.io.*;
class Main {
    static final long INF = 1_000_000_007L;
    static int N, M, K;
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1];
        tree = new long[N*4];
        
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        build(1, 1, N);
             
        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            
            if(t == 1) {
                change(1, 1, N, s, e);
            }else {
                bw.write(String.valueOf(query(1, 1, N, s, (int)e)));
                bw.newLine();
            }
        }
   
        bw.flush();
        bw.close();   
    }
    
    public static long build(int now, int start, int end) {
        if(start == end) {
            return tree[now] = arr[start];
        }
        
        int mid = (start + end)/2;
        long left = build(now * 2, start, mid);
        long right = build(now * 2 + 1, mid+1, end);
        
        return tree[now] = (left * right)%INF;   
    }
    
    public static long change(int now, int start, int end, int idx, long num) {
        if(idx < start || idx > end) { // 현재 확인할 구간이 idx와 겹치지 않음
            return tree[now];
        }
        
        if(start == end) { 
            return tree[now] = num;
        }
        
        int mid = (start + end)/2;
        long left = change(now * 2, start, mid, idx, num);
        long right = change(now * 2 + 1, mid+1, end, idx, num);
        return tree[now] = (left * right)%INF;
    }
    
    public static long query(int now, int start, int end, int l, int r) {
        if(r < start || end < l) {
            return 1;
        }
        
        if(l <= start && end <= r) {
            return tree[now];
        }
        
        int mid = (start + end)/2;
        long left = query(now*2, start, mid, l, r);
        long right = query(now*2+1, mid+1, end, l, r);
        return (left * right)%INF;
    }
}