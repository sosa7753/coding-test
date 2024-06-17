class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        // 원과의 거리
        for(int i=0; i<=d; i = i + k) { // x축 기준 
            double max = Math.sqrt((double)d*d - (double)i*i);
            int tmp = (int)((long)max/(long)k);
            answer += tmp + 1;            
        }
        return answer;
    }
}