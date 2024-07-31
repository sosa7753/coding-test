class Solution {
    public int solution(int num) {
        int answer = 1;
        
        if(num == 1) {
            return 0;
        }
        
        long n = (long)num;
        while(answer <= 500) {
            if(n%2 == 0) { // 짝수면
                n = n/2;
            }else {
                n = n * 3 + 1L;
            }
            
            if(n == 1L) {
                return answer;
            }
            answer++;           
        }
        return -1;
    }
}