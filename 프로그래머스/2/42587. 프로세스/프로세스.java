import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            queue.offer(new int[] {priorities[i], i});
            pq.offer(priorities[i]);
        }
        
        while(true) {
            int[] now = queue.poll();                
            
            // 제거 조건 
            if(now[0] == pq.peek()) {
                pq.poll();
                answer++;
                if(now[1] == location) {
                    break;
                }
            }
            queue.offer(now);
        }
              
        return answer;
    }
}