import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<int[]> stack = new Stack<>();
        
        for(int i=0; i<prices.length; i++) {
            if(stack.isEmpty()) {
                stack.push(new int[] {prices[i], i});
                continue;
            }
                
            while(!stack.isEmpty() && stack.peek()[0] > prices[i]) {
                int[] now = stack.pop();
                answer[now[1]] = i - now[1];
            }
            
            stack.push(new int[] {prices[i], i});
        }
        
        while(!stack.isEmpty()) {
            int[] now = stack.pop();
            answer[now[1]] = prices.length -1 - now[1];
        }
        
        return answer;
    }
}