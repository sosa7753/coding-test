// 기존의 스코빌 지수 중 K 이상인 것은 패스
// 힙에 최솟값을 2개 꺼내서 섞자.
import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        boolean possible = false;
                  
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<scoville.length; i++) {
            if(scoville[i] < K) {
                pq.offer(scoville[i]);
                continue;
            }
            possible = true;
        }
        
        // 사이즈가 2개 이상이면 2개를 꺼내서 섞고 넣기 
        while(pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            
            int bland = first + second * 2;
            answer++;
            if(bland < K) {
                pq.offer(bland);
                continue;
            }
            possible = true;           
        }
        
        if(!pq.isEmpty()) {
            if(!possible) {
                return -1;
            }
            answer++;
        }

        return answer;
    }
}