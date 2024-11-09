import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i=0; i<s.length; i++) {
            answer[i] = change(s[i]);
        }
        return answer;
    }
    public String change(String str) {
        if(str.length() <=2) {
            return str;
        }
        Stack<Character> stack = new Stack<>();
        
        // 모든 110 추출 
        int cnt = 0;
        for(int i=0; i<str.length(); i++) {
            if(stack.size() >= 2 && stack.peek() == '1' && str.charAt(i) == '0') {
                stack.pop();
                if(stack.peek() == '1') {
                    stack.pop();
                    cnt++;
                }else {
                    stack.push('1');
                    stack.push('0');
                }
            }else {
                stack.push(str.charAt(i));
            }
        }
        
        if(cnt == 0) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder();
        int len = stack.size();
        int one = 0;
        int idx = -1;
        for(int i=0; i<len; i++) { // 마지막 0의 인덱스를 찾자
            char c = stack.pop();
            sb.append(c);
            
            if(c == '0' && idx == -1) {
                idx = len-1-i;
            }                 
        }
        
        sb.reverse();
        String in = ("110").repeat(cnt);
        if(idx == -1) { // 0이 존재하지 않음. 따라서 맨 앞에 붙임.
            sb.insert(0, in);
        }else { // idx+1 부터 넣기 
            sb.insert(idx+1, in);
        }
        return sb.toString();
    }
}