class Solution {
    int[][][] dp;
    int[] num;
    char[] oper;
    public int solution(String arr[]) {    
        // dp로 s ~ e까지의 최댓값 최솟값 구하기 
        // 메모이제이션 활용하기
        init(arr);
        int len = num.length;
        dp = new int[len][len][2]; // 0은 최댓값, 1은 최솟값
        
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(i == j) {
                    dp[i][j][0] = num[i];
                    dp[i][j][1] = num[i];
                    continue;
                }
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
           
        return DFS(0, len-1, 0);
    }
    
    public int DFS(int s, int e, int flag) {
        if(dp[s][e][flag] != Integer.MIN_VALUE && 
           dp[s][e][flag] != Integer.MAX_VALUE) {
            return dp[s][e][flag];
        }
        
        if(s == e) {
            return dp[s][e][flag];
        }
        
        for(int i=s; i<e; i++) {
            if(flag == 0) {
                if(oper[i] == '+') { // 최댓값 구하기
                    dp[s][e][flag] = Math.max(dp[s][e][flag],
                                              DFS(s, i, 0) + DFS(i+1, e, 0));
                }else {
                    dp[s][e][flag] = Math.max(dp[s][e][flag], 
                                              DFS(s, i, 0) - DFS(i+1, e, 1));
                }
            }else {
                if(oper[i] == '+') { // 최솟값
                    dp[s][e][flag] = Math.min(dp[s][e][flag],
                                              DFS(s, i, 1) + DFS(i+1, e, 1));
                }else {
                    dp[s][e][flag] = Math.min(dp[s][e][flag], 
                                              DFS(s, i, 1) - DFS(i+1, e, 0));
                }
            }
        }
        return dp[s][e][flag];
    }
    
    public void init(String[] arr) {
        num = new int[arr.length/2 + 1];
        oper = new char[arr.length/2];

        for(int i=0; i<arr.length; i++) {
            if(i%2 == 0) {
                num[i/2] = Integer.parseInt(arr[i]);
            }else {
                oper[i/2] = arr[i].charAt(0);
            }
        }
    }
}