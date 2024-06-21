import java.util.*;
class Solution {
    int[] answer;
    public int[] solution(String s) {
        answer = new int[2];
        
        binary(s, 0, 0);
        return answer;
    }
    
    public void binary(String s, int idx, int cnt) {   
        if("1".equals(s)) {
            answer[0] = idx;
            answer[1] = cnt;
            return;
        }
        // s길이에서 1개수 세기  -> s 1-개수는 cnt++
        // 1개수로 이진법 문자열 만들기 -> 반복
        
        int one = 0;
        for(int i=0; i<s.length(); i++) {
            if('1' == s.charAt(i)) {
                one++;
            }
        }
        
        // 이진법 변환하기
        int num = one;
        Stack<String> stack = new Stack<>();
        
        while(num >= 2) {
            if(num%2 == 0) {
                stack.push("0");
            }else {
                stack.push("1");
            }
            num = num/2;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        while(!stack.isEmpty()) {
            String str = stack.pop();
            sb.append(str);
        }       
        
        binary(sb.toString(), idx+1, cnt + s.length() - one);        
    }
}