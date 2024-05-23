import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int cnt = k;
        
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (y[1] - x[1]));
        
        // Map에 개수 저장 
        for(int i=0; i<tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
        }
        
        // 우선순위 큐에 개수가 많은 순으로 저장 
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[] {entry.getKey(), entry.getValue()});
        }
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            
            if(cnt == 0) {
                break;
            }
            
            if(now[1] >= cnt) {
                answer++;
                break;
            }else {
                cnt -= now[1];
                answer++;
            }            
        }
        return answer;
    }
}