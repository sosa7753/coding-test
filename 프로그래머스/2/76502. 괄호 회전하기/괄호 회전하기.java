import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
                
        for(int i=0; i<s.length(); i++) {            
            if(rotate(s, i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean rotate(String s, int idx) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=idx; i< idx + s.length(); i++) {
            char c = s.charAt(i%s.length());
            
            if(c == '[' || c == '(' || c == '{') {
                stack.push(c);
                continue;
            }
            
            if(stack.isEmpty()) {
                return false;
            }
            
            if(!save(stack.peek(), c)) {
                return false;
            }
            stack.pop();
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        return true;     
    }
    
    // 맞는 괄호 찾기 
    public boolean save(char a, char b) {
        if((a == '[' && b == ']') || (a == '(' && b == ')') || 
           (a == '{' && b == '}')) {
            
            return true;
        }
        return false;
    }
}