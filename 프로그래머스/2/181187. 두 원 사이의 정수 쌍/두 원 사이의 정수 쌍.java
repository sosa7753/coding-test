class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(int i=0; i<=r2; i++) {
            double end =  Math.sqrt((double)r2*r2 - (double)i*i);
            double start = Math.sqrt((double)r1*r1 - (double)i*i);
            
            // start <= t <= end
            long start1 = (long)start;
          
            long s = 0;
            long e = (long)end;
            if(start != (double)start1) {
                s = start1 + 1;
            }else {
                s = start1;
            }
            
            if(s==0) {
                s = 1;
            }
            
            answer += e - (s - 1);            
        }
        return answer * 4;
    }
}