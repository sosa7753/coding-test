import java.util.*;
class Solution {
    public int solution(int[][] routes) {   
        int answer = 0;
        Arrays.sort(routes, (x,y) -> (x[1]-y[1]));

        int last = Integer.MIN_VALUE;
        for(int[] route : routes) {
            if(last < route[0]) {
                answer++;
                last = route[1];
            }
        }
        
        return answer;
    }
}