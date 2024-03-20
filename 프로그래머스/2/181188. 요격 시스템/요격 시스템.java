import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        
        int answer = 0;
        
        // 미사일 시작 기준으로 오름차순 정렬
        Arrays.sort(targets, (x,y) -> (x[0] - y[0]));
        
        // 이전 요격 구간 
        int preStart = targets[0][0]; 
        int preEnd = targets[0][1];
        
        for(int[] target : targets) {          
            if(answer == 0) {
                answer++;
                continue;
            }
            
            // 현재 미사일 
            int curStart = target[0];
            int curEnd = target[1];
            
            // 겹치는 요격 범위
            if(preStart <= curStart && curStart < preEnd) {
                preStart = curStart;
                preEnd = Math.min(curEnd, preEnd);
            }else {
                answer++;
                preStart = curStart;
                preEnd = curEnd;
            }
        }
        
        return answer;
    }    
}