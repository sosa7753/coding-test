class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[triangle.length][];
        for(int i=0; i<dp.length; i++) {
            dp[i] = new int[triangle[i].length];
        }
        
        dp[0][0] = triangle[0][0];
        for(int i=0; i<dp.length-1; i++) { // 바텀-업
            for(int j=0; j<dp[i].length; j++) { // 열 자기자신, 자기자신+1 인덱스 업데이트
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
            }
        }
                
        for(int i=0; i<dp[dp.length-1].length; i++) {
            answer = Math.max(dp[dp.length-1][i], answer);
        }
        
        return answer;
    }
}