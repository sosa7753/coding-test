class Solution {   
    public int solution(int n, int[] money) {       
        int[][] dp = new int[money.length+1][n+1]; // 사용한 동전 종류 , n 값
        
        for(int i=1; i<=money.length; i++) { // 동전 종류 
            for(int j=0; j<=n; j++) { // n값
                if(j == 0) { // ex dp[5]를 구할때 dp[i][5-5]가 되는데 자기자신을 쓸때 1임.
                    dp[i][j] = 1; 
                    continue;
                }
                if(j < money[i-1]) { // 현재 동전 종류로 만들 수 없음.
                    dp[i][j] = dp[i-1][j];
                }else { // 현재 동전 종류로 만들 수 있음.
                    dp[i][j] = (dp[i-1][j] + dp[i][j-money[i-1]])%1000000007;
                }
            }
        }
        return dp[money.length][n];
    }
}