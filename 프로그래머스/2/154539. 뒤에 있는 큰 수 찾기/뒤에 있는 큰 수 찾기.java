import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<int[]> stack = new Stack<>();
        for(int i=0; i<numbers.length; i++) {
            if(stack.isEmpty()) {
                stack.push(new int[] {numbers[i], i});
                continue;
            }
                  
            while(!stack.isEmpty()) {
                int[] now = stack.peek();
                if(now[0] < numbers[i]) {
                    answer[now[1]] = numbers[i];
                    stack.pop();     
                    continue;
                }
                break;
            }
            stack.push(new int[] {numbers[i], i});
        }
        
        while(!stack.isEmpty()) {
            int[] remain = stack.pop();
            answer[remain[1]] = -1;
        }
        
        return answer;
    }
}