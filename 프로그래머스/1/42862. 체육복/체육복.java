import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] ans = new int[n+2];
        Arrays.fill(ans, 1);

        for(int i=0; i<lost.length; i++) {
            ans[lost[i]]--;
        }
        
        for(int i=0; i<reserve.length; i++) {
            ans[reserve[i]]++;
        }
        
        for(int i=1; i<=n; i++) {
            if(ans[i] >=1) {
                answer++; 
                continue;
            }
            
            if(ans[i-1] == 2) {
                answer++;
            }else if(ans[i+1] == 2) {
                answer++;
                ans[i+1]--;
            }
        }
        return answer;
    }
}