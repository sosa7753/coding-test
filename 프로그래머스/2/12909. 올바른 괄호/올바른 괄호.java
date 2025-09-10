import java.util.*;
class Solution {
    boolean solution(String s) {
        
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        for(char a : c) {
            if(a == '(') {
                stack.push(a);
            }else {
                if(stack.isEmpty() || stack.peek() == ')') return false;
                stack.pop();
            }
        }

        return stack.isEmpty() ? true : false;
    }
}