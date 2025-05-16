class Solution {
    public int solution(String s) {
        int answer = 1;        
        int n = s.length();
        
        boolean[][] dp = new boolean[n][n];
        for(int i=0; i<n; i++) {
            dp[i][i] = true;
        }
        
        for(int i=2; i<=n; i++) { // 문자열 길이
            for(int j=0; j<=n-i; j++) { // 시작 인덱스
                int l = j+i-1; // 끝 인덱스
                if(s.charAt(j) == s.charAt(l)) {
                    if(i==2 || dp[j+1][l-1]) {
                        dp[j][l] = true;
                        answer = Math.max(answer, i);
                    }
                }
            }
        }

        return answer;
    }
}