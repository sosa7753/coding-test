import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[0]-y[0])); // 반납시간, 증설개수
        int server = 0;
        for(int i=0; i<players.length; i++) {
            if(!pq.isEmpty()) {
                int[] del = pq.peek();
                if(i == del[0]) {
                   server -= del[1];
                pq.poll(); 
                }           
            }
            
            int cnt = players[i]/m; // 필요 서버수 
            
            if(server < cnt) {
                int pl = cnt - server;
                pq.offer(new int[]{i + k, pl});
                server = cnt;
                answer += pl; 
            }
        }
        
        return answer;
    }
}