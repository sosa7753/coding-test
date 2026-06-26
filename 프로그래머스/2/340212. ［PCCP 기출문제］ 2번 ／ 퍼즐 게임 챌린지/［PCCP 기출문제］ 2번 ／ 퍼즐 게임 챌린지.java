class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right= 1;
        for(int i=0; i<diffs.length; i++) {
            right = Math.max(diffs[i], right);
        }
        
        int answer = 0;
        while(left <= right) {
            int mid = (left + right)/2;
            
            if(isPossible(diffs, times, limit, mid)) {
                right = mid - 1;
                answer = mid;
            }else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public boolean isPossible(int[] diffs, int[] times, long limit, int level) {
        long value = 0;
        long pre = 0;
        for(int i=0; i<diffs.length; i++) {
            if(level < diffs[i]) {
                value += (diffs[i] - level) * (pre + times[i]);
            }
            value += times[i];
            pre = times[i];
        }
        
        if(limit >= value) {
            return true;
        }
        return false;
    }
}