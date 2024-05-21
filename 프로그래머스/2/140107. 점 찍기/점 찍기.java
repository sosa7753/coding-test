class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int i=0; i<=d; i = i + k) {  // x축 기준          
            double max = Math.sqrt((long)d * d - (long)i * i); // 최대 y 좌표 
            
            double num = max/k;
            
            answer += (long)num+1;
        }
        return answer;
    }
}