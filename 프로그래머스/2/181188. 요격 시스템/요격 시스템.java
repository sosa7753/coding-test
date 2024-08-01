import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 개구간이 작은순 -> 같다면 폐구간이 작은 순 
        Arrays.sort(targets, (x,y) -> {
            if(x[0] == y[0]) {
                return x[1] - y[1];
            }else {
                return x[0] - y[0];
            }         
        });
        
        // 범위가 나타날 때마다 범위 줄이기.
        int min = -1;
        int max = -1;
        for(int i=0; i<targets.length; i++) {           
            // 시작 값이 max이상 이면 answer++; 범위 초기화
            // 시작 값이 범위 내에 있다면 시작은 새로운 시작 범위, 
            // 끝 값은 두 미사일 중 작은 값으로 업데이트
            
            if(max <= targets[i][0]) {
                answer++;
                min = targets[i][0];
                max = targets[i][1];
                continue;
            }
            
            if(min <= targets[i][0]) {
                min = targets[i][0];
                max = Math.min(max, targets[i][1]);
            }            
        }
       
        return answer;
    }
}