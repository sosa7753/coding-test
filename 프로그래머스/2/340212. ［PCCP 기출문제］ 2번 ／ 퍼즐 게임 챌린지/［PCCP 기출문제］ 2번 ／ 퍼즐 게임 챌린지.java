class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        
        int R = 0;
        for(int d : diffs) {
            R = Math.max(R, d);
        }
        int L = 1;
        while(L<=R) {
            int mid = L + (R - L)/2;
            if(solve(diffs, times, mid, limit)) {
                answer = Math.min(answer, mid);   
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return answer;
    }
    
    public boolean solve(int[] diffs, int[] times, int v, long limit) {
        long result = times[0];
        for(int i=1; i<diffs.length; i++) {
            if(diffs[i] <= v) {
                result += times[i];
            }else {
                result += (times[i-1] + times[i]) * (diffs[i] - v) + times[i];
            }
        }
        
        return result <= limit ? true : false;
    }
}