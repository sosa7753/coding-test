import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        // 비어있으면, 트럭 올리기
        // 트럭은 들어간 시점부터 길이 초만큼 존재
        // 무게가 초과하면, 대기해야함. 
        // 매초마다 트럭 빼기
        
        Queue<int[]> q = new LinkedList<>();
        int w = 0;
        int time = 0;
        for(int i=0; i<truck_weights.length; i++) {
            if(q.isEmpty()) { // 버이 있을 때
                q.offer(new int[]{truck_weights[i], time});
                w += truck_weights[i];
                time++;
                continue;
            }
            
            if(time - q.peek()[1] == bridge_length) { // 하나씩 빼주기
                int[] remove = q.poll();
                w -= remove[0];
            }
            
            if(w + truck_weights[i] <= weight) {
                w += truck_weights[i];
                q.offer(new int[]{truck_weights[i], time});
            }else {
                i--;        
            }
            time++;      
        }
    
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if(q.isEmpty()) {
                time += bridge_length - (time - now[1]) + 1;
            }
        }
        return time;
    }
}