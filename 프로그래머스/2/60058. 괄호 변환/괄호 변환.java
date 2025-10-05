import java.util.*;
class Solution {
    StringBuilder sb = new StringBuilder();
    public String solution(String p) {
        DFS(p);
        return sb.toString();
    }
    
    public void DFS(String now) {
        if(check(now)) {
            sb.append(now);
            return;
        }
        
        int idx = div(now);
        
        String u = now.substring(0, idx+1);
        String v = now.substring(idx+1);
        
        if(check(u)) {
            sb.append(u);
            DFS(v);
        }else {
            sb.append("(");
            DFS(v);
            sb.append(")");
            sb.append(change(u));
        }             
    }
    
    public int div(String s) {
        char[] c = s.toCharArray();
        
        int l = 0;
        int r = 0;
        for(int i=0; i<c.length; i++) {
            if(c[i] == '(') {
                l++;
            }else {
                r++;
            }
            
            if(l == r) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean check(String s) {
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        for(char cc : c) {
            if(cc == '(') {
                stack.push(cc);
            }else {
                if(stack.isEmpty()) {
                    return false;
                }else if(stack.peek() == '(') {
                    stack.pop();
                }else {
                    stack.push(cc);
                }
            }
        }
        
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }
    
    public String change(String s) {
        s = s.substring(1, s.length()-1);
        
        s = s.replace("(", "1");
        s = s.replace(")", "2");
        
        s = s.replace("1", ")");
        s = s.replace("2", "(");
        
        return s;
    }
}