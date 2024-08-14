class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int i=0; i<=d; i = i + k) {
            double max = Math.sqrt((long)d * (long)d - (long)i * (long)i);
            
            answer += (long)max/k + 1L;
        }
        return answer;
    }
}