import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[0] - y[0]));
        
        for(int i=0; i<routes.length; i++) {
            pq.offer(routes[i]);
        }
        
        int preStart = -40000;
        int preEnd = -39999;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            // 해당 범위에 Start가 포함되는지 
            if(preStart <= now[0] && now[0] <= preEnd) {
                preStart = now[0];
                preEnd = Math.min(preEnd, now[1]);
                continue;
            }
            
            // 포함이 안된다면, 
            preStart = now[0];
            preEnd = now[1];
            answer++;
        }
                 
        return answer;
    }
}