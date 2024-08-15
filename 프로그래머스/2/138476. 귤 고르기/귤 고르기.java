import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        
        for(int value : map.values()) {
            pq.offer(value);
        }
        
        while(k > 0 && !pq.isEmpty()) {
            int now = pq.poll();
            answer++;
            
            if(k <= now) {
                break;
            }
            k -= now;
        }
        return answer;
    }
}