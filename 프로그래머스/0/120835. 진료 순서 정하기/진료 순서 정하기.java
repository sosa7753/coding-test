import java.util.*;
class Solution {
    public int[] solution(int[] emergency) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) ->(y[1] - x[1]));
        
        for(int i=0; i<emergency.length; i++) {
            pq.offer(new int[] {i+1, emergency[i]});
        }
        
        int[] answer = new int[pq.size()];
        int score = 1;
        while(!pq.isEmpty()) {
           int[] now = pq.poll();
           answer[now[0] - 1] = score;
           score++;
        }
        return answer;
    }
}