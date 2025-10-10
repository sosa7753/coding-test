class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long max = 0;
        for(int i=0; i<times.length; i++) {
            max = Math.max(max, times[i]);
        }
        
        long l = 0;
        long r = max * n;
        while(l <= r) {
            long mid = (r-l)/2 + l;
            
            long result = 0;
            for(int i=0; i<times.length; i++) {
                result += mid/times[i];
            }
            
            if(result < (long)n) {
                l = mid + 1;
            }else {
                r = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}