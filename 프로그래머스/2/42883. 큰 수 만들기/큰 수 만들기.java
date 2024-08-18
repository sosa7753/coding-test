import java.util.*;
class Solution {
    public String solution(String number, int k) {
        
        int remove = k;
        Stack<Character> stack = new Stack<>();
        
        int idx = 0;
        while(idx < number.length() && k > 0) {
            char c = number.charAt(idx);
            
            if(stack.isEmpty()) {
                stack.push(c);
                idx++;
                continue;
            }
            
            char now = stack.peek();
            if(now >= c) {
                stack.push(c);
            }else {
                while(k > 0 && now < c && !stack.isEmpty()) {
                    char cc = stack.pop();
                    k--;
                    if(!stack.isEmpty()) {
                        now = stack.peek();
                    }
                }
                stack.push(c);
            }
            idx++;
        }
        
        StringBuilder sb = new StringBuilder();
        int max = stack.size();
        if(max > number.length() - remove) {
            for(int i=0; i< max - number.length() + remove; i++) {
                stack.pop();
            }
        }
        
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        return sb.reverse().toString() + number.substring(idx);
    }
}