import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        if(n > s) {
            return new int[]{-1}; 
        }
               
        int[] answer = new int[n];
        int idx = 0;
        int pre = s;
        for(int i=n; i>=1; i--) {
            answer[idx++] = pre/i;
            pre -= pre/i;
        }
        
        return answer;
    }
}