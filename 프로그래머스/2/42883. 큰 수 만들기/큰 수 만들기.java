import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack();
        int len = number.length() - k;
       
        for(int i=0; i<number.length(); i++) {
            char value = number.charAt(i);
            while(!stack.isEmpty() && k > 0 && stack.peek() < value) {
                stack.pop();
                k--;
            }
            stack.push(value);
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().substring(0,len);   
    }
}