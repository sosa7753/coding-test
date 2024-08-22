import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
            if(x[0] == y[0]) {
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });
        
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        
        for(int i=0; i<book_time.length; i++) {
            pq.offer(new int[] {time(book_time[i][0]), time(book_time[i][1])});
        }
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(pq2.isEmpty()) {
                pq2.offer(now[1]);
                continue;
            }
            
            int pre = pq2.peek();
            if(pre + 10 <= now[0]) {
                pq2.poll();
            }
            pq2.offer(now[1]);               
        }
      
        return pq2.size();
    }
    
    public int time(String clock) {
        String[] s = clock.split(":");
        
        return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
    }
}