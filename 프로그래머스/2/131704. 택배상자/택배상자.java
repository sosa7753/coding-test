import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack(); // 보조 컨테이너 
        
        boolean[] save = new boolean[order.length+1];
        
        int top = 1;
        int now = 0; // 현재 넣은 번호 
        // 현재 번호를 넣는 방법 구하기 
        for(int i=0; i<order.length; i++) {
            
            if(now < order[i]) {
                save[order[i]] = true;
                answer++;
                now = order[i];
                
                while(top < order[i]) {
                    if(!save[top]) {
                        save[top] = true;
                        stack.push(top);                                     
                    }
                    top++; 
                    
                }
                continue;
            }
            
            if(stack.peek() == order[i]) {
                int tmp = stack.pop();
                save[tmp] = true;
                answer++;
                if(stack.isEmpty()) {
                    top = 1;
                }else {
                    top--;
                }
            }else {
                break;
            }
        }
        return answer;
    }
}