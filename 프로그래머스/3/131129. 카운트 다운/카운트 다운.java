class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];
        
        int[][] dp = new int[2][target+1]; // 0행은 다트 수 1행은 싱글or불 수 
        for(int i=1; i<=target; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        
        for(int i=0; i<=target; i++) {
            for(int j=1; j<=20; j++) { // 불, x1, x2, x3        
                // 불을 쏘는 경우
                if(i >= 50) {
                    if(dp[0][i] > dp[0][i-50] + 1) { // 더 적은값으로 다트를 쏨 
                        dp[0][i] = dp[0][i-50] + 1;
                        dp[1][i] = dp[1][i-50] + 1;
                    }else if(dp[0][i] == dp[0][i-50] + 1) { // 같은 값으로 다트를 쏨
                        dp[1][i] = Math.max(dp[1][i], dp[1][i-50] + 1);
                    }
                }
                
                // 싱글 
                if(i >= j) {
                    if(dp[0][i] > dp[0][i-j] + 1) {
                        dp[0][i] = dp[0][i-j] + 1;
                        dp[1][i] = dp[1][i-j] + 1;
                    }else if(dp[0][i] == dp[0][i-j] + 1) {
                        dp[1][i] = Math.max(dp[1][i], dp[1][i-j] + 1);                
                    }
                }
                
                // 더블 
                if(i >= 2 * j) {
                    if(dp[0][i] > dp[0][i-2*j] + 1) {
                        dp[0][i] = dp[0][i-2*j] + 1;
                        dp[1][i] = dp[1][i-2*j];
                    }
                }
                
                // 트리플 
                if(i >= 3*j) {
                    if(dp[0][i] > dp[0][i-3*j] + 1) {
                        dp[0][i] = dp[0][i-3*j] + 1;
                        dp[1][i] = dp[1][i-3*j];
                    }
                }
            }
        }
        
        answer[0] = dp[0][target];
        answer[1] = dp[1][target];
        
        return answer;
    }
}