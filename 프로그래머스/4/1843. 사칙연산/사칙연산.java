class Solution {
    int[] num;
    boolean[] operator; // true는 +, false는 -;
    int[][][] dp; // start, end, 최대/최소 = 0/1
    public int solution(String arr[]) {
        num = new int[arr.length/2 + 1];
        operator = new boolean[arr.length/2];
        div(arr);
        
        dp = new int[num.length][num.length][2];
        for(int i=0; i<num.length; i++) {
            for(int j=i; j<num.length; j++) {
                if(i==j) {
                    dp[i][j][0] = num[i];
                    dp[i][j][1] = num[i];
                }else {
                    dp[i][j][0] = Integer.MIN_VALUE;
                    dp[i][j][1] = Integer.MAX_VALUE;
                }
            }
        }
        
        return DP(0, num.length-1, 0);
    }
    
    public int DP(int start, int end, int flag) {
        if(visit(start, end, flag)) {
            return dp[start][end][flag];
        }
        
        for(int i=start; i<end; i++) {
            if(flag == 0) { // 최대 = 앞에 최대 고정 뒤에는 + 면 최대, -면 최소
                if(operator[i]) { // 다음이 +다
                    dp[start][end][flag] = Math.max(dp[start][end][flag], 
                                                    DP(start, i, 0) + DP(i+1, end, 0));
                }else {
                    dp[start][end][flag] = Math.max(dp[start][end][flag],
                                                   DP(start, i, 0) - DP(i+1, end, 1));
                }              
            }else { // 최소 = 앞에 최소 고정 뒤에는 + 면 최소, -면 최대 
                if(operator[i]) { // 다음이 +다
                    dp[start][end][flag] = Math.min(dp[start][end][flag], 
                                                    DP(start, i, 1) + DP(i+1, end, 1));
                }else {
                    dp[start][end][flag] = Math.min(dp[start][end][flag],
                                                   DP(start, i, 1) - DP(i+1, end, 0));
                } 
            }
        }
        return dp[start][end][flag];
    }
    
    public void div(String[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(i%2 ==0) {
                num[i/2] = Integer.parseInt(arr[i]);
            }else {
                if("+".equals(arr[i])) {
                    operator[i/2] = true;
                }
            }
        }
    } 
    
    public boolean visit(int start, int end, int flag) {
        return dp[start][end][flag] != Integer.MAX_VALUE &&
               dp[start][end][flag] != Integer.MIN_VALUE;
    }
}