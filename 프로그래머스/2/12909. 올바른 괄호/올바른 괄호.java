import java.util.*;
class Solution {
    public boolean solution(String s) {
        boolean answer =  true;
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(stack.isEmpty()) {
                if(')' == c) {
                    return false;
                }
                stack.push(s.charAt(i));
                continue;
            }
            
            if('(' == c) {
                stack.push(c);
            }else {
                if(stack.peek() == '(') {
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}