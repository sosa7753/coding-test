import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        Deque<Node> dq = new LinkedList<>();        
        for(int i=0; i<stones.length; i++) {
            while(!dq.isEmpty() && dq.getLast().value < stones[i]) {
                dq.removeLast();
            }
            dq.addLast(new Node(stones[i], i));
            
            if(dq.peek().idx <= i-k) { // dq를 비우지 않았을 때 4(3) 
                dq.poll();
            }
            
            if(dq.getLast().idx >= k-1) {
                answer = Math.min(answer, dq.peek().value);
            }        
        }
        return answer;
    }
}

class Node {
    int value;
    int idx;
    Node(int value, int idx) {
        this.idx = idx;
        this.value = value;
    }
}