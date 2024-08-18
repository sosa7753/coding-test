import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {        
        List<Integer> list = new ArrayList<>();
        
        Stack<Integer> stack = new Stack<>();
        
        int cnt = 0;
        for(int i=0; i<speeds.length; i++) {
            int remain = (100 - progresses[i] + speeds[i] -1)/speeds[i]; // 배포일 
            
            if(stack.isEmpty()) {
                stack.push(remain);
                cnt++;
                continue;
            }
            // 작거나 같다면 cnt++;
            
            if(stack.peek() >= remain) {
                cnt++;
            }else {
                list.add(cnt);
                stack.pop();
                stack.push(remain);
                cnt = 1;
            }           
        }
        
        list.add(cnt);
        
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}