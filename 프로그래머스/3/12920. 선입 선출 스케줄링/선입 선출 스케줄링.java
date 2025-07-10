class Solution {
    public int solution(int n, int[] cores) {
        long answer = 0;
        
        long L = 0;
        long R = (long)10000000000L;
        while(L <= R) {
            long mid = (L + R)/2;
            
            if(in(mid, cores) < n) {
                L = mid + 1;
            }else {
                answer = mid;
                R = mid - 1;             
            }     
        }
        
        // answer-1 초의 총 작업량
        long pre = in(answer-1, cores);
        int idx = 0;
        for(int i=0; i<cores.length; i++) {
            if(answer%cores[i] == 0) {
                pre++;
                if(pre == n) {
                    idx = i+1;
                    break;
                }
            }
        }
        return idx;
    }
    
    public long in(long time, int[] cores) {
        long result = 0;
        for(int core : cores) {
            result += time/core + 1;
        }
        return result;
    }
}