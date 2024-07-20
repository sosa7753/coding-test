class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int idx = 0;
        int pre = 0;
        int value = 0;
        while(idx < dartResult.length()) {           
            char c = dartResult.charAt(idx);
            
            if(c - '0' == 1 && dartResult.charAt(idx+1) == '0') {
                value = 10;
                idx++;
            }else {
                value = c - '0';
            }
            
            if(dartResult.charAt(idx+1) == 'D') {
                value = (int)Math.pow(value, 2);
            }else if(dartResult.charAt(idx+1) == 'T') {
                value = (int)Math.pow(value, 3);
            }
            
            int right = idx + 2;
            if(right < dartResult.length()) {
                if(dartResult.charAt(right) == '*') {
                    if(pre != 0) {
                        pre *=2;
                    }
                    value *=2;
                    idx = right + 1;
                }else if(dartResult.charAt(right) == '#') {
                    value *=-1;
                    idx = right + 1;
                }else {
                    idx = right;
                }           
            }else {
                idx = right;
            }
            
            answer += pre;
            pre = value;
        }
        
        answer += value;
        
        return answer;
    }
}