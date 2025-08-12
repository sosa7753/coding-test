import java.util.*;
import java.io.*;
class Main {
    static int N,W;
    static int[] r, c; // 사건 좌표
    static int[][] dp;
    static int[] police; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        
        r = new int[W+1];
        c = new int[W+1];
        for(int i=1; i<=W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[W+1][W+1]; // 1번이 a, 2번이 b까지 처리했을 때, 남은 사건 최소 비용 
        for(int i=0; i<=W; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        police = new int[W+1];
        StringBuilder sb = new StringBuilder();
        sb.append(solve(0,0)).append("\n");
        
        restore(0,0);
        for(int i=1; i<=W; i++) {
            sb.append(police[i]).append("\n");
        }        
        
        System.out.print(sb); 
    }
    
    public static int solve(int a, int b) {
        int next = Math.max(a, b) + 1;
        if(next > W) {
            return 0;
        }
        
        if(dp[a][b] != -1) {
            return dp[a][b];
        } 
        
        int cost1 = dist1(a, next) + solve(next, b);
        int cost2 = dist2(b, next) + solve(a, next);
        
        return dp[a][b] = Math.min(cost1, cost2);
    }
    
    public static void restore(int a, int b) {
        int next = Math.max(a, b) + 1;
        if(next > W) {
            return;
        }
        
        int cost1 = dist1(a, next) + dp[next][b];
        int cost2 = dist2(b, next) + dp[a][next];
       
        if(cost1 <= cost2) {
            police[next] = 1;
            restore(next, b);
        }else {
            police[next] = 2;
            restore(a, next);
        }
    }
    
    public static int dist1(int a, int next) {
        int sr = (a==0) ? 1 : r[a];
        int sc = (a==0) ? 1 : c[a];
        return Math.abs(sr - r[next]) + Math.abs(sc - c[next]);
    }
    
    public static int dist2(int b, int next) {
        int sr = (b==0) ? N : r[b];
        int sc = (b==0) ? N : c[b];
        return Math.abs(sr - r[next]) + Math.abs(sc - c[next]);
    }
}