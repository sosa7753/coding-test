import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        int answer = 0;
        
        int b = n;
        int s = k;
        
        for(int i=0; i<enemy.length; i++) {
            if(b >= enemy[i]) {
                answer++;
                pq.add(enemy[i]);
                b -= enemy[i];
                continue;
            }
            
            // 병사로 못막는 경우 
            if(s > 0) { // 무적권이 있는 경우
               pq.add(enemy[i]);
               int tmp = pq.poll();
               s--;
               answer++;
               b = b + tmp - enemy[i];
            }else {
                break;
            }
         }
        
        return answer;   
    }
}