import java.util.*;
class Solution {
    public long solution(long n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        String n1 = String.valueOf(n);
        
        for(int i=0; i<n1.length(); i++) {
            pq.offer(n1.charAt(i) - '0');
        }
        
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now);
        }
        
        return Long.parseLong(sb.toString());
    }
}