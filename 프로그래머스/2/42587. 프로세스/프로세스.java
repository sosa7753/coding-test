import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        
        // 현재 우선순위가 가장 높은 값 
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y) -> (y-x));
        
        // 실행 프로세스를 순서대로 넣어주기
        Queue<Node> queue = new LinkedList<>();
        
        // 우선순위 큐 및 큐 초기화 
        for(int i=0; i<priorities.length; i++) {
            pq.offer(priorities[i]);
            queue.offer(new Node(i, priorities[i]));
        }
    
        // 우선순위가 가장 높은 값이 아니면 다시 큐에 넣기 
        // 우선순위가 가장 높은 값이면 큐에서 빼기
        // idx == location인 프로세스의 실행 순서를 알고 싶다.
        
        int cnt = 0;
        while(true) {
            Node now = queue.poll(); // 현재 프로세스
            if(now.priority < pq.peek()) {
                queue.offer(now);
                continue;
            }
            
            if(location == now.idx) {
                cnt++;
                break;
            }
            
            pq.poll();      
            cnt++;
        }
        
        return cnt;
        
    }
}

class Node {
    int idx;
    int priority;
    Node(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }
}