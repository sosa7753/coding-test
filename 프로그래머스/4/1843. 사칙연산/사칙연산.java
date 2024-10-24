class Solution {
    char[] operations;
    int[] numbers;
    int[][][] dp;
    public int solution(String arr[]) {
        int size = arr.length/2;
        
        operations = new char[size];
        numbers = new int[size+1];         
        init(arr);
        
        dp = new int[numbers.length][numbers.length][2]; // 0은 최댓값, 1은 최솟값 
        for(int i=0; i<size+1; i++) {
            for(int j=i; j<size+1; j++) {
                if(i==j) {
                    dp[i][j][0] = numbers[i];
                    dp[i][j][1] = numbers[i];
                    continue;
                }
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        
        return DP(0, size, 0);
    }
    public int DP(int start, int end, int flag) { // 괄호를 치는 범위
        if(start == end) { // 자기 자신 괄호
            return dp[start][end][flag];
        }
        
        if(visit(start, end, flag)) { // 메모이제이션
            return dp[start][end][flag];
        }
        
        if(flag == 0) { // 최댓값 구하기 
            for(int i=start; i<end; i++) {
                if(operations[i] == '-') {
                    dp[start][end][flag] = 
                        Math.max(dp[start][end][flag], DP(start, i, 0) - DP(i+1, end, 1));
                }else {
                    dp[start][end][flag] = 
                        Math.max(dp[start][end][flag], DP(start, i, 0) + DP(i+1, end, 0));
                }
            }             
        }else {
            for(int i=start; i<end; i++) {
                if(operations[i] == '-') {
                    dp[start][end][flag] = 
                        Math.min(dp[start][end][flag], DP(start, i, 1) - DP(i+1, end, 0));
                }else {
                    dp[start][end][flag] = 
                        Math.min(dp[start][end][flag], DP(start, i, 1) + DP(i+1, end, 1));
                }
            }           
        }
        
        return dp[start][end][flag];       
    }
    
    public void init(String[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(i%2 == 0) {
               numbers[i/2] = Integer.parseInt(arr[i]); 
            }else {
                operations[i/2] = arr[i].charAt(0);
            }
        }
    }
    
    public boolean visit(int start, int end, int flag) {
        return dp[start][end][flag] != Integer.MAX_VALUE && 
               dp[start][end][flag] != Integer.MIN_VALUE;
    }
}