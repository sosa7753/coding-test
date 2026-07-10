class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    long MOD = 1_000_000_007L;
    int n,m,len,v;
    public int solution(int[][] grid, int[] d, int k) {
        n = grid.length;
        m = grid[0].length;
        len = d.length;
        v = n*m;
        
        // 이동횟수만큼 썼을 때 a에서 b까지 가는 경우의 수 
        long[][][] dp = new long[len+1][v][v];
        for(int i=0; i<v; i++) { // 정지 상태에서 만족하는 경우의 수 = 1
            dp[0][i][i] = 1; 
        }
        
        for(int cnt=1; cnt<=len; cnt++) { // 이동횟수에 대해 
            for(int b=0; b<v; b++) { // 도착칸에 대해 
                int r = b/m; int c = b%m;
                
                for(int dir=0; dir<4; dir++) { // 4방향에서 오는 값
                    int pr = r + dr[dir];
                    int pc = c + dc[dir];
                    
                    if(pr < 0 || pr > n-1 || pc < 0 || pc > m-1) continue;
                    if(grid[r][c] - grid[pr][pc] != d[cnt-1]) continue;
                    
                    // 이동횟수 i에 대해 a에서 b로 도착하는 경우의 수 
                    // = 이동횟수 i-1의 a에서 b의 4방향인 pr*m+c까지의 경우의 수를 더해주자.
                    for(int a=0; a<v; a++) { 
                        dp[cnt][a][b] = (dp[cnt][a][b] + dp[cnt-1][a][pr*m+pc])%MOD;
                    }
                }
            }
        }
        long answer = 0;
        long[][] result = matPow(dp[len], (long)k);
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                answer = (answer + result[i][j])%MOD;
            }
        }
        
        return (int)answer;
    }
    
    public long[][] matPow(long[][] M, long p) {
        long[][] r = new long[v][v];
        for(int i=0; i<v; i++) r[i][i] = 1;
        
        long[][] base = M;
        while(p > 0) { // 이진법 접근
            if((p&1) == 1) { // 최하위 비트가 1이면 M^x비트가 존재하는 것임
                r = mul(r, base);
            }
            base = mul(base, base);
            p = p>>1;
        }
        return r;
    }    
    
    public long[][] mul(long[][] o, long[][] t) { // 정사각행렬의 곱
        long[][] r = new long[v][v];
        
        for(int i=0; i<v; i++) { // 결과의 행
            for(int j=0; j<v; j++) { // 결과의 열
                for(int k=0; k<v; k++) {
                    r[i][j] = (r[i][j] + o[i][k] * t[k][j])%MOD;
                }
            }
        }
        return r;
    }
}