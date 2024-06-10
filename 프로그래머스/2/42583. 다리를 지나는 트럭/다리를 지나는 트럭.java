import java.util.*;
// 큐에 모든 차를 넣고 시작
// 다리는 2 칸임.
// 큐에서 하나를 꺼내서 현재 남은 무게와 비교 10 > 7
// time++ 
// 다시 꺼냄, 6 ->  6 >3 이므로 패스 
// time++
// 
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {        
        // 건너야 하는 트럭 저장.
        Queue<Integer> truck = new LinkedList<>();
        for(int t : truck_weights) {
            truck.offer(t);
        }
        
        // 지나고 있는 다리 트럭
        Queue<int[]> bridge = new LinkedList<>();
        
        int time = 1;
        int leave = weight; // 견딜 수 있는 무게 
        while(!truck.isEmpty()) {
            // 다리에서 나갈 수 있는 트럭은 빼주기 
            if(!bridge.isEmpty()) {
                int[] out = bridge.peek();
                if(time - out[1] == bridge_length) {
                    bridge.poll();
                    leave += out[0];
                }
            }
            
            int t = truck.peek();
            
            if(bridge.isEmpty()) { // 다리가 비어있으면 넣어 주기          
                bridge.offer(new int[] {t,time});
                leave -= t;
                truck.poll();
            }else {
                if(leave >= t) { // 다음 트럭이 건널 수 있다면 
                    leave -=t;
                    bridge.offer(new int[] {t, time});
                    truck.poll();
                }
            }
            time++;
        }
        
        while(!bridge.isEmpty()) {
            int[] out = bridge.peek();
            if(time - out[1] == bridge_length) {
                bridge.poll();
            }
            time++;
        }
        
        return time - 1;
    }
}