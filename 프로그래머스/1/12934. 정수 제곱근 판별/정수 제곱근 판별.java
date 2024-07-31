class Solution {
    public long solution(long n) {
        long answer = 0;
        
        double n1 = Math.sqrt(n);
        int n2 = (int)n1;
        
        if((double)n2 == n1) { // 정수 
            answer = (n2 + 1L) * (n2 + 1L);
        }else {
            answer = -1L;
        }
        
        return answer;
    }
}