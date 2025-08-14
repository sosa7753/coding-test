import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        Stack<int[]> stack = new Stack<>();
        
        int answer = 0;
        for(int i=0; i<ingredient.length; i++) {
            int now = ingredient[i];
            if(stack.isEmpty()) {
                stack.push(new int[]{now, 0});
                continue;
            }
            
            if(now == 1) {
                if(stack.peek()[1] == 2) {
                   stack.pop();
                   stack.pop();
                   stack.pop();
                   answer++; 
                }else {
                    stack.push(new int[]{now, 0});
                }           
                continue;
            }
            
            if(now - stack.peek()[0] == 1) {
                stack.push(new int[]{now, stack.peek()[1]+1});
            }else {
                stack.push(new int[]{now, 0});
            }
        }
        
        return answer;
    }
}
