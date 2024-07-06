import java.util.*;
class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        int idx = 0; 
                
        // 순위 제거 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<score.length; i++) {
            pq.offer(score[i]);
            
            if(i >= k) {
                pq.poll();
            } 
            
            answer[idx++] = pq.peek();
        }
        return answer;     
    }
}