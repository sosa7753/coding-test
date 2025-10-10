import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        // 시작 시간 작은순 정렬
        // 범위를 체크해서 겹치는 곳에 1개 설치
        
        Arrays.sort(routes, (x,y) -> (x[0] - y[0]));
        
        int answer = 0;
        int L = -40000;
        int R = 40000;
        for(int[] route : routes) {
            if(R < route[0]) {
                answer++;
                L = route[0];
                R = route[1];
                continue;
            }
            
            L = Math.max(L, route[0]);
            R = Math.min(R, route[1]);
        }
        
        return answer+1;
    }
}