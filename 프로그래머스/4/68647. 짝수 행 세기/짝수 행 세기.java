class Solution {
    long MOD = 10000019;
    long[][] comb;
    public int solution(int[][] a) {  
        comb = new long[a.length+1][a.length+1];
        
        int[] num = new int[a[0].length];
        int sum = 0;
        for(int i=0; i<a[0].length; i++) {
            for(int j=0; j<a.length; j++) {
                num[i] += a[j][i];
                sum += a[j][i];
            }
        }
        
        if(sum%2 == 1) {
            return 0;
        }
        
        long[][] dp = new long[a[0].length][a.length+1];        
        dp[0][a.length-num[0]] = combination(a.length, num[0]);
        
        for(int i=1; i<a[0].length; i++) { // 열 반복
            for(int j=0; j<=a.length; j++) { // 짝수인 행의 개수가 j
                if(dp[i-1][j] == 0) {
                    continue;
                }
                
                int odd = a.length - j;
                // 짝수 2 홀수 2 일 때, 배치해야할 1의 개수가 3
                // 2C2 * 2C1 , 2C1 * 2C2 , 2C0 * 2C3
                // 짝수4 홀수 0 배치 3
                // 4C3 * 0C0 , 4C2 
                for(int k=num[i]; k>=0; k--) {    
                    if(j < k || num[i] -k > odd) {
                        continue;
                    }
                    dp[i][j+num[i]-2*k] += (dp[i-1][j] * 
                                            combination(j, k)%MOD * combination(odd, num[i]-k)%MOD)%MOD;
                    dp[i][j+num[i]-2*k] %= MOD;
                }
            }
        }    
        return (int)(dp[a[0].length-1][a.length]%MOD);
    }
    
    public long combination(int n, int r) {
        if(comb[n][r] != 0) {
            return comb[n][r];
        }
        
        if(r == 0 || n == r) {
            return 1;
        }
        
        return comb[n][r] = (combination(n-1, r) + combination(n-1, r-1))%MOD;
    }
}