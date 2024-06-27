import java.util.*;
class Solution {
    StringBuilder sb = new StringBuilder();
    // 짝 개수가 같음 -> 균형잡힌 문자열
    // 짝이 모두 맞음 -> 올바른 괄호 문자열
    public String solution(String p) {
             
        if(check(p)) {
            return p;
        }   
        
        DFS(p);
        
        return sb.toString();
    }
    
    
    public void DFS(String start) {
        if("".equals(start)) {
            return;
        }
        
        int o = 0;
        int c = 0;
        int p = 0;
        while(true) {
            char sc = start.charAt(p);
            if(sc == '(') {
                o++;
            }else {
                c++;
            }
            
            if(o == c) {
                // 올바른 괄호 문자열 
                String u = start.substring(0, p+1);
                String v = start.substring(p+1);
                if(check(u)) {
                    sb.append(u);
                    DFS(v);
                    break;
                }
                
                // 올바르지 않은 문자열
                sb.append("(");
                DFS(v);
                sb.append(")");
                sb.append(change(u));
                break;
            }
            p++;
        }
    }
    // 올바른 괄호 문자열 체크
    public boolean check(String p) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<p.length(); i++) {
            char c = p.charAt(i);
            
            // 비어 있을 때
            if(stack.isEmpty()) {
                if(c == ')') {
                    return false;
                }else {
                    stack.push(c);
                }
                continue;
            }
            
            // 비어 있지 않을 때 
            if('(' == c) {
                stack.push(c);
            }else {
                if(stack.peek() == ')') {
                    return false;
                }
                stack.pop();
            }
        }
        
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }
      
    public String change(String s) {
        if(s.length() <=2) {
            return "";
        }
    
        s = s.replace("(", "1");
        s = s.replace(")", "2");
        
        s = s.replace("1", ")");
        s = s.replace("2", "(");
                 
        return s.substring(1,s.length()-1);
    }
}