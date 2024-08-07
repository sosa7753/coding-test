import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            if(stack.isEmpty()) {
                if(')' == s.charAt(i)) {
                    return false;
                }
                stack.push(s.charAt(i));
            }else {
                if(')' == s.charAt(i)) {
                    if(stack.peek() == '(') {
                        stack.pop();
                    }else {
                        return false;
                    }
                }else {
                    stack.push(s.charAt(i));
                }
            }
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        
        return answer;
    }
}