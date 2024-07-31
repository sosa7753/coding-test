class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        
        if(a >= b) {
            answer = (long)a * (long)(a+1) / 2 - (long)(b-1) * (long)(b) / 2; 
        }else {
            answer = (long)b * (long)(b+1) / 2 - (long)(a-1) * (long)(a) / 2; 
        }
        
        return answer;
    }
}