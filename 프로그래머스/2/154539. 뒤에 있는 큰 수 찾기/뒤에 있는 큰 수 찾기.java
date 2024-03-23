import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
    
        Stack<int[]> stack = new Stack<>();
    
        for(int i=0; i<n; i++) {
            if(stack.isEmpty()) {
                stack.push(new int[] {numbers[i], i});
                continue;
            }
            
            while(!stack.isEmpty()) {
                int[] number = stack.peek();
                
                if(number[0] < numbers[i]) { // 뒷큰수 성립
                    answer[number[1]] = numbers[i];
                    stack.pop();
                    continue;
                }else {
                    break;
                }            
            }
            
            // 뒷큰수 성립 X
            stack.push(new int[] {numbers[i], i});
        }
        return answer;
    }
}