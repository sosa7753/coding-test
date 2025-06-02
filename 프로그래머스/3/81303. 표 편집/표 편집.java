import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();
        
        int len = n;
        int idx = k;
        for(int i=0; i<cmd.length; i++) {
            char c = cmd[i].charAt(0);
            if(c == 'U') {
                idx -= Integer.parseInt(cmd[i].substring(2));
            }else if(c == 'D') {
                idx += Integer.parseInt(cmd[i].substring(2));
            }else if(c == 'C') {
                len--;
                stack.push(idx);
                if(idx == len) {
                    idx--;
                }
            }else if(c == 'Z') {
                if(stack.pop() <= idx) {
                    idx++;
                }
                len++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++) {
            sb.append("O");
        }
        
        while(!stack.isEmpty()) {
            sb.insert((int)stack.pop(), "X");
        }
        return sb.toString();
    }
}