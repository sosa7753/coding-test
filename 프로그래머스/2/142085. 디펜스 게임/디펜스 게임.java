import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        int answer = 0;     
        int start = n;
        for(int i=0; i<enemy.length; i++) {        
            pq.offer(enemy[i]);
            
            if(start >= enemy[i]) {
                start -= enemy[i];
                answer++;
                continue;
            }

            while(start < enemy[i] && k > 0 && !pq.isEmpty()) {
                start += pq.poll();
                k--;
            }
            
            if(start < enemy[i]) {
                return answer;
            }else {
                start -= enemy[i];
                answer++;
            }
        }
        
        return answer;
    }
}