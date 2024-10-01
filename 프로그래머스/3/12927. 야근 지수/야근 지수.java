import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        for(int i=0; i<works.length; i++) {
            pq.offer(works[i]);
        } 
        
        int start = n;
        while(start > 0 && !pq.isEmpty()) {
            int now = pq.poll();
            if(now != 1) {
                pq.offer(now-1);                
            }
            start--;
        }
        
        long answer = 0;
        if(pq.isEmpty()) {
            return answer;
        }
        
        while(!pq.isEmpty()) {
            int now = pq.poll();
            answer += (long)now * (long)now;
        }
        
        return answer;
    }
}