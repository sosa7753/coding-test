import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        for(int work : works) {
            pq.offer(work);
        }
        
        int start = n;
        while(!pq.isEmpty() && start > 0) {
            int now = pq.poll();
            if(now != 1) {
                pq.offer(now-1);
            }
            start--;
        }
        
        long answer = 0;
        while(!pq.isEmpty()) {
            long now = pq.poll();
            answer += now * now;
        }
        
        return answer;
    }
}