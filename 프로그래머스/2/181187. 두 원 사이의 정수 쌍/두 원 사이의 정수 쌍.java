class Solution {
    public long solution(int r1, int r2) { 
          
        int s = Math.min(r1, r2);
        int e = Math.max(r1, r2);
        
        long min = (long)Math.pow(s, 2);
        long max = (long)Math.pow(e, 2);
        
        long zero = 0;
        long answer = 0;
        for(int i=0; i<=e; i++) {
            long x = (long)(i)*i;
            
            long result = (long)Math.sqrt(max - x) - (long)Math.ceil(Math.sqrt(min - x)) + 1;
            
            answer += result;
            if(i == 0) {
                zero = result;
            }
        }
    
        return (answer - zero) * 4;
    }
}