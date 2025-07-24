import java.util.*;
import java.io.*;
class Main {
    static class Node {
        int val;
        int idx;
        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
        public static boolean check(Node x,  Node y) { // true면 x 채택
            if(x.val < y.val) {
                return true;
            }else if(x.val > y.val) {
                return false;
            }else {
                if(x.idx < y.idx) {
                    return true;
                }
                return false; 
            }
        }
    }
    static int N, M;
    static int[] arr;
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N+1];
        tree = new Node[4 * N];
        
        st = new StringTokenizer(br.readLine());   
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        build(1, 1, N);
        
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(t == 1) {
                change(1, 1, N, a, b);
            }else {
                Node result = query(1, 1, N, a, b);
                sb.append(result.idx).append("\n");
            }
        }
        System.out.print(sb);
    }
    
    public static Node build(int now, int s, int e) {
        if(s == e) {
           return tree[now] = new Node(arr[s], s);
        }
        
        int m = (s+e)/2;
        Node left = build(now*2, s, m);
        Node right = build(now*2+1, m+1, e);
        
        return tree[now] = Node.check(left, right) ? left : right;
    }
    
    public static Node change(int now, int s, int e, int idx, int val) {
        if(idx < s || idx > e) {
            return tree[now];
        }
        
        if(s == e) {
            return tree[now] = new Node(val, idx);
        }
        
        int m = (s+e)/2;
        Node left = change(now*2, s, m, idx, val);
        Node right = change(now*2+1, m+1, e, idx, val);
        
        return tree[now] = Node.check(left, right) ? left : right;
    }
    
    public static Node query(int now, int s, int e, int l, int r) {
        if(r < s || e < l) {
            return new Node(Integer.MAX_VALUE, -1);
        }
        
        if(l<=s && e<=r) {
            return tree[now];
        }
        
        int m = (s+e)/2;
        Node left = query(now*2, s, m, l, r);
        Node right = query(now*2+1, m+1, e, l, r);
        
        return Node.check(left, right) ? left : right;
    }
}