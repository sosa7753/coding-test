import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        int now = 1;
        for(int i=0; i<order.length; i++) {
            if(now == order[i]) {
                answer++;
                now++; 
            }else if(now > order[i]) {
                if(stack.peek() != order[i]) {
                    break;
                }else {
                    stack.pop();
                    answer++;
                }
            }else {
                while(now < order[i]) {
                    stack.push(now);
                    now++;
                }
                now++;
                answer++;
            }
        }
        return answer;
    }
}